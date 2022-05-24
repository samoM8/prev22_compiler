package prev.phase.imcgen;

import java.util.*;

import prev.common.report.Report;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.mem.*;
import prev.data.typ.SemChar;
import prev.data.typ.SemType;
import prev.phase.memory.*;
import prev.phase.seman.SemAn;

public class CodeGenerator extends AstNullVisitor<Object, Stack<MemFrame>> {

    // GENERAL PURPOSE

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Stack<MemFrame> memFrames) {
        if (memFrames == null)
            memFrames = new Stack<>();
        for (AstTree t : trees) {
            if (t instanceof AstFunDecl)
                t.accept(this, memFrames);
        }
        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstFunDecl funDecl, Stack<MemFrame> memFrames) {
        MemFrame funFrame = Memory.frames.get(funDecl);
        memFrames.push(funFrame);
        if (funDecl.expr != null)
            funDecl.expr.accept(this, memFrames);
        memFrames.pop();
        return null;
    }

    // EXPRESSIONS

    @Override
    public Object visit(AstRecExpr recExpr, Stack<MemFrame> memFrames) {
        recExpr.rec.accept(this, memFrames);
        recExpr.comp.accept(this, memFrames);

        ImcExpr record = ImcGen.exprImc.get(recExpr.rec);
        if (record instanceof ImcMEM) {
            AstDecl decl = SemAn.declaredAt.get(recExpr.comp);
            MemAccess memAccess = Memory.accesses.get((AstMemDecl) decl);
            if (memAccess instanceof MemRelAccess memRelAccess) {
                ImcExpr imcExpr = new ImcMEM(new ImcBINOP(ImcBINOP.Oper.ADD, ((ImcMEM) record).addr, new ImcCONST(memRelAccess.offset)));
                ImcGen.exprImc.put(recExpr, imcExpr);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Stack<MemFrame> memFrames) {
        AstDecl decl = SemAn.declaredAt.get(nameExpr);

        if (decl instanceof AstFunDecl funDecl) {
            // function without parameters
            MemFrame currentFrame = memFrames.peek();
            MemFrame calledFunFrame = Memory.frames.get(funDecl);

            ImcExpr SL = new ImcTEMP(currentFrame.FP);
            for (int i = calledFunFrame.depth; i < currentFrame.depth; i++)
                SL = new ImcMEM(SL);

            Vector<ImcExpr> argsExpr = new Vector<>();
            Vector<Long> offsets = new Vector<>();

            argsExpr.add(SL);
            offsets.add(0L);

            ImcCALL imcCALL = new ImcCALL(calledFunFrame.label, offsets, argsExpr);
            ImcGen.exprImc.put(nameExpr, imcCALL);
            return null;
        } else if (decl instanceof AstVarDecl || decl instanceof  AstParDecl) {
            MemAccess memAccess = Memory.accesses.get((AstMemDecl) decl);
            if (memAccess instanceof MemAbsAccess memAbsAccess) {
                // absolute address
                ImcMEM imcMEM = new ImcMEM(new ImcNAME(memAbsAccess.label));
                ImcGen.exprImc.put(nameExpr, imcMEM);
            } else if (memAccess instanceof  MemRelAccess memRelAccess) {
                // relative address
                MemFrame currentFrame = memFrames.peek();
                ImcCONST offset = new ImcCONST(memRelAccess.offset);

                ImcExpr address = new ImcTEMP(currentFrame.FP);
                for (int i = memRelAccess.depth; i <= currentFrame.depth; i++)
                    address = new ImcMEM(address);

                ImcMEM imcMEM = new ImcMEM(new ImcBINOP(ImcBINOP.Oper.ADD, address, offset));
                ImcGen.exprImc.put(nameExpr, imcMEM);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, Stack<MemFrame> memFrames) {
        Vector<ImcExpr> argsExpr = new Vector<>();
        Vector<Long> offsets = new Vector<>();
        long offset = 0;

        MemFrame currentFrame = memFrames.peek(); // calling function frame
        MemFrame calledFunFrame = Memory.frames.get((AstFunDecl) SemAn.declaredAt.get(callExpr));

        ImcExpr SL = new ImcTEMP(currentFrame.FP);
        long depthDiff = currentFrame.depth - calledFunFrame.depth + 1;
        while (depthDiff > 0) {
            SL = new ImcMEM(SL);
            depthDiff--;
        }

        //for (int i = calledFunFrame.depth; i < currentFrame.depth; i++)
        //    SL = new ImcMEM(SL);

        argsExpr.add(SL);
        offsets.add(0L);
        offset += 8L;

        for (AstExpr arg : callExpr.args) {
            arg.accept(this, memFrames);
            ImcExpr imcExpr = ImcGen.exprImc.get(arg);
            SemType type = SemAn.ofType.get(arg);

            argsExpr.add(imcExpr);
            offsets.add(offset);

            offset += type.size();
        }

        ImcCALL imcCALL = new ImcCALL(calledFunFrame.label, offsets, argsExpr);
        ImcGen.exprImc.put(callExpr, imcCALL);
        return null;
    }

    @Override
    public Object visit(AstCastExpr castExpr, Stack<MemFrame> memFrames) {
        castExpr.expr.accept(this, memFrames);
        castExpr.type.accept(this, memFrames);
        ImcExpr imcExpr = ImcGen.exprImc.get(castExpr.expr);

        SemType type = SemAn.isType.get(castExpr.type);
        if (type instanceof SemChar) {
            ImcGen.exprImc.put(castExpr, new ImcBINOP(ImcBINOP.Oper.MOD, imcExpr, new ImcCONST(256)));
        } else {
            ImcGen.exprImc.put(castExpr, imcExpr);
        }

        return null;
    }

    @Override
    public Object visit(AstStmtExpr stmtExpr, Stack<MemFrame> memFrames) {
        Vector<ImcStmt> stmtList = new Vector<>();

        for (AstStmt stmt : stmtExpr.stmts) {
            stmt.accept(this, memFrames);
            ImcStmt imcStmt = ImcGen.stmtImc.get(stmt);
            stmtList.add(imcStmt);
        }

        ImcStmt last = stmtList.lastElement();
        if (last instanceof ImcESTMT lastEl) {
            stmtList.remove(lastEl);
            ImcGen.exprImc.put(stmtExpr, new ImcSEXPR(new ImcSTMTS(stmtList), lastEl.expr));
            return null;
        }

        ImcGen.exprImc.put(stmtExpr, new ImcSEXPR(new ImcSTMTS(stmtList), new ImcCONST(0)));
        return null;
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Stack<MemFrame> memFrames) {
        arrExpr.arr.accept(this, memFrames);
        arrExpr.idx.accept(this, memFrames);
        ImcExpr arr = ImcGen.exprImc.get(arrExpr.arr);
        ImcExpr arrAddr;
        if (arr instanceof ImcMEM)
            arrAddr = ((ImcMEM) arr).addr;
        else
            throw new Report.Error(arrExpr,"Array must be type mem.");
        ImcExpr idx = ImcGen.exprImc.get(arrExpr.idx);

        ImcBINOP imcBINOP = new ImcBINOP(
                ImcBINOP.Oper.ADD,
                arrAddr,
                new ImcBINOP(ImcBINOP.Oper.MUL, idx, new ImcCONST(8)));
        ImcGen.exprImc.put(arrExpr, new ImcMEM(imcBINOP));
        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, Stack<MemFrame> memFrames) {
        sfxExpr.expr.accept(this, memFrames);
        ImcExpr imcExpr = ImcGen.exprImc.get(sfxExpr.expr);
        ImcMEM imcMEM = new ImcMEM(imcExpr);
        ImcGen.exprImc.put(sfxExpr, imcMEM);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Stack<MemFrame> memFrames) {
        switch (atomExpr.type) {
            case VOID, POINTER -> {
                ImcCONST imcCONST = new ImcCONST(0);
                ImcGen.exprImc.put(atomExpr, imcCONST);
            }
            case CHAR -> {
                ImcCONST imcCONST = new ImcCONST(atomExpr.value.charAt(1));
                ImcGen.exprImc.put(atomExpr, imcCONST);
            }
            case INT -> {
                ImcCONST imcCONST = new ImcCONST(Long.parseLong(atomExpr.value));
                ImcGen.exprImc.put(atomExpr, imcCONST);
            }
            case BOOL -> {
                ImcCONST imcCONST;
                if (atomExpr.value.equals("true")){
                    imcCONST = new ImcCONST(1);
                } else {
                    imcCONST = new ImcCONST(0);

                }
                ImcGen.exprImc.put(atomExpr, imcCONST);
            }
            case STRING -> {
                ImcNAME imcNAME = new ImcNAME(Memory.strings.get(atomExpr).label);
                ImcGen.exprImc.put(atomExpr, imcNAME);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstPfxExpr pfxExpr, Stack<MemFrame> memFrames) {
        pfxExpr.expr.accept(this, memFrames);

        switch (pfxExpr.oper) {
            case NOT -> {
                ImcUNOP imcUNOP = new ImcUNOP(ImcUNOP.Oper.NOT, ImcGen.exprImc.get(pfxExpr.expr));
                ImcGen.exprImc.put(pfxExpr, imcUNOP);
            }
            case ADD -> {
                ImcGen.exprImc.put(pfxExpr, ImcGen.exprImc.get(pfxExpr.expr));
            }
            case SUB -> {
                ImcUNOP imcUNOP = new ImcUNOP(ImcUNOP.Oper.NEG, ImcGen.exprImc.get(pfxExpr.expr));
                ImcGen.exprImc.put(pfxExpr, imcUNOP);
            }
            case PTR -> {
                ImcExpr address = ((ImcMEM)ImcGen.exprImc.get(pfxExpr.expr)).addr;
                ImcGen.exprImc.put(pfxExpr, address);
            }
            case NEW -> {
                Vector<ImcExpr> args = new Vector<>();
                Vector<Long> offsets = new Vector<>();
                //Todo maybe i should also add static link (same in DEL)
                args.add(ImcGen.exprImc.get(pfxExpr.expr));
                offsets.add(8L);
                ImcExpr imcExpr = new ImcCALL(new MemLabel("new"), offsets, args);
                ImcGen.exprImc.put(pfxExpr, imcExpr);
            }
            case DEL -> {
                Vector<ImcExpr> args = new Vector<>();
                Vector<Long> offsets = new Vector<>();
                args.add(ImcGen.exprImc.get(pfxExpr.expr));
                offsets.add(8L);
                ImcExpr imcExpr = new ImcCALL(new MemLabel("del"), offsets, args);
                ImcGen.exprImc.put(pfxExpr, imcExpr);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstBinExpr binExpr, Stack<MemFrame> memFrames) {
        binExpr.fstExpr.accept(this, memFrames);
        binExpr.sndExpr.accept(this, memFrames);

        ImcBINOP.Oper oper;
        switch (binExpr.oper) {
            case OR -> oper = ImcBINOP.Oper.OR;
            case AND -> oper = ImcBINOP.Oper.AND;
            case EQU -> oper = ImcBINOP.Oper.EQU;
            case NEQ -> oper = ImcBINOP.Oper.NEQ;
            case LTH -> oper = ImcBINOP.Oper.LTH;
            case GTH -> oper = ImcBINOP.Oper.GTH;
            case LEQ -> oper = ImcBINOP.Oper.LEQ;
            case GEQ -> oper = ImcBINOP.Oper.GEQ;
            case ADD -> oper = ImcBINOP.Oper.ADD;
            case SUB -> oper = ImcBINOP.Oper.SUB;
            case MUL -> oper = ImcBINOP.Oper.MUL;
            case DIV -> oper = ImcBINOP.Oper.DIV;
            case MOD -> oper = ImcBINOP.Oper.MOD;
            default -> oper = null;
        }

        ImcExpr fstExpr = ImcGen.exprImc.get(binExpr.fstExpr);
        ImcExpr sndExpr = ImcGen.exprImc.get(binExpr.sndExpr);
        ImcBINOP imcBINOP = new ImcBINOP(oper, fstExpr, sndExpr);
        ImcGen.exprImc.put(binExpr, imcBINOP);

        return null;
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, Stack<MemFrame> memFrames) {
        whereExpr.expr.accept(this, memFrames);
        whereExpr.decls.accept(this, memFrames);

        ImcExpr imcExpr = ImcGen.exprImc.get(whereExpr.expr);
        ImcGen.exprImc.put(whereExpr, imcExpr);
        return null;
    }

    // STATEMENTS

    @Override
    public Object visit(AstAssignStmt assignStmt, Stack<MemFrame> memFrames) {
        assignStmt.dst.accept(this, memFrames);
        assignStmt.src.accept(this, memFrames);
        ImcExpr dstExpr = ImcGen.exprImc.get(assignStmt.dst);
        ImcExpr srcExpr = ImcGen.exprImc.get(assignStmt.src);
        ImcMOVE imcMOVE = new ImcMOVE(dstExpr, srcExpr);
        ImcGen.stmtImc.put(assignStmt, imcMOVE);
        return null;
    }

    @Override
    public Object visit(AstExprStmt exprStmt, Stack<MemFrame> memFrames) {
        exprStmt.expr.accept(this, memFrames);
        ImcExpr imcExpr = ImcGen.exprImc.get(exprStmt.expr);
        ImcESTMT imcESTMT = new ImcESTMT(imcExpr);
        ImcGen.stmtImc.put(exprStmt, imcESTMT);
        return null;
    }

    @Override
    public Object visit(AstIfStmt ifStmt, Stack<MemFrame> memFrames) {
        ifStmt.cond.accept(this, memFrames);
        ifStmt.thenStmt.accept(this, memFrames);
        ifStmt.elseStmt.accept(this, memFrames);

        ImcExpr condExpr = ImcGen.exprImc.get(ifStmt.cond);
        ImcStmt thenStmt = ImcGen.stmtImc.get(ifStmt.thenStmt);
        ImcStmt elseStmt = ImcGen.stmtImc.get(ifStmt.elseStmt);

        MemLabel ifTrue = new MemLabel();
        MemLabel ifFalse = new MemLabel();
        MemLabel endIf = new MemLabel();

        Vector<ImcStmt> stmts = new Vector<>();
        stmts.add(new ImcCJUMP(condExpr, ifTrue, ifFalse));
        stmts.add(new ImcLABEL(ifTrue));
        stmts.add(thenStmt);
        stmts.add(new ImcJUMP(endIf));
        stmts.add(new ImcLABEL(ifFalse));
        stmts.add(elseStmt);
        stmts.add(new ImcLABEL(endIf));

        ImcSTMTS imcSTMTS = new ImcSTMTS(stmts);
        ImcGen.stmtImc.put(ifStmt, imcSTMTS);
        return null;
    }

    @Override
    public Object visit(AstWhileStmt whileStmt, Stack<MemFrame> memFrames) {
        whileStmt.cond.accept(this, memFrames);
        whileStmt.bodyStmt.accept(this, memFrames);

        ImcExpr condExpr = ImcGen.exprImc.get(whileStmt.cond);
        ImcStmt bodyStmt = ImcGen.stmtImc.get(whileStmt.bodyStmt);

        MemLabel checkCondition = new MemLabel();
        MemLabel whileTrue = new MemLabel();
        MemLabel whileFalse = new MemLabel();

        Vector<ImcStmt> stmts = new Vector<>();
        stmts.add(new ImcLABEL(checkCondition));
        stmts.add(new ImcCJUMP(condExpr, whileTrue, whileFalse));
        stmts.add(new ImcLABEL(whileTrue));
        stmts.add(bodyStmt);
        stmts.add(new ImcJUMP(checkCondition));
        stmts.add(new ImcLABEL(whileFalse));

        ImcSTMTS imcSTMTS = new ImcSTMTS(stmts);
        ImcGen.stmtImc.put(whileStmt, imcSTMTS);
        return null;
    }
}
