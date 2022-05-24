package prev.phase.asmgen;

import java.util.*;

import prev.Compiler;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {
    private final int numOfRegs = Integer.parseInt(Compiler.cmdLineArgValue("--nregs"));

    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> asmInstrs) {
        MemTemp dest = new MemTemp();
        MemTemp fstExpr = binOp.fstExpr.accept(this, asmInstrs);
        MemTemp sndExpr = binOp.sndExpr.accept(this, asmInstrs);

        Vector<MemTemp> uses = new Vector<>();
        uses.add(fstExpr);
        uses.add(sndExpr);
        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        switch (binOp.oper) {
            case ADD -> asmInstrs.add(new AsmOPER("ADD `d0,`s0,`s1", uses, defs, null));
            case SUB -> asmInstrs.add(new AsmOPER("SUB `d0,`s0,`s1", uses, defs, null));
            case MUL -> asmInstrs.add(new AsmOPER("MUL `d0,`s0,`s1", uses, defs, null));
            case DIV -> asmInstrs.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));
            case MOD -> {
                // The reminder of DIV operation is stored in rR
                asmInstrs.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));
                asmInstrs.add(new AsmOPER("GET `d0,rR", null, defs, null));
            }
            case OR -> asmInstrs.add(new AsmOPER("OR `d0,`s0,`s1", uses, defs, null));
            case AND -> asmInstrs.add(new AsmOPER("AND `d0,`s0,`s1", uses, defs, null));
            case EQU -> {
                // CMP set register d0 to -1(less than), 0(equal) or 1(greater than)
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if zero
                asmInstrs.add(new AsmOPER("ZSZ `d0,`s0,1", defs, defs, null));
            }
            case NEQ -> {
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if non zero
                asmInstrs.add(new AsmOPER("ZSNZ `d0,`s0,1", defs, defs, null));
            }
            case LTH -> {
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if negative
                asmInstrs.add(new AsmOPER("ZSN `d0,`s0,1", defs, defs, null));
            }
            case GTH -> {
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if positive
                asmInstrs.add(new AsmOPER("ZSP `d0,`s0,1", defs, defs, null));
            }
            case LEQ -> {
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if non-positive
                asmInstrs.add(new AsmOPER("ZSNP `d0,`s0,1", defs, defs, null));
            }
            case GEQ -> {
                asmInstrs.add(new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null));
                // Zero or set if non-negative
                asmInstrs.add(new AsmOPER("ZSNN `d0,`s0,1", defs, defs, null));
            }
        }
        return dest;
    }

    public MemTemp visit(ImcCALL call, Vector<AsmInstr> asmInstrs) {
        Iterator<ImcExpr> argsIter = call.args.iterator();
        Iterator<Long> offsetIter = call.offs.iterator();
        while (argsIter.hasNext() && offsetIter.hasNext()) {
            ImcExpr arg = argsIter.next();
            Long offset = offsetIter.next();

            MemTemp exprTmp = arg.accept(this, asmInstrs);
            Vector<MemTemp> uses = new Vector<>();
            uses.add(exprTmp);

            // STO $X, $Y, $Z
            // Register $X is stored to address $Y + $Z
            asmInstrs.add(new AsmOPER(String.format("STO `s0,$254,%d", offset), uses, null, null));
        }

        Vector<MemLabel> jumps = new Vector<>();
        jumps.add(call.label);
        asmInstrs.add(new AsmOPER(String.format("PUSHJ $%d,%s",numOfRegs, call.label.name), null, null, jumps));

        MemTemp dest = new MemTemp();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        asmInstrs.add(new AsmOPER("LDO `d0,$254,0", null, defs, null));

        return dest;
    }

    public MemTemp visit(ImcCONST constant, Vector<AsmInstr> asmInstrs) {
        MemTemp dest = new MemTemp();
        Vector<MemTemp> uses = new Vector<>();
        uses.add(dest);
        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        boolean negativeConstant = false;
        long value = constant.value;
        if (value < 0) {
            negativeConstant = true;
            value = -value;
        }
        int low = (int) (value & 0xFFFF);
        int mediumLow = (int) ((value >> 16) & 0xFFFF);
        int mediumHigh = (int) ((value >> 32) & 0xFFFF);
        int high = (int) ((value >> 48) & 0xFFFF);

        asmInstrs.add(new AsmOPER(String.format("SETL `d0,%d", low), null, defs, null));
        if (mediumLow > 0)
            asmInstrs.add(new AsmOPER(String.format("INCML `d0,%d", mediumLow), uses, defs, null));
        if (mediumHigh > 0)
            asmInstrs.add(new AsmOPER(String.format("INCMH `d0,%d", mediumHigh), uses, defs, null));
        if (high > 0)
            asmInstrs.add(new AsmOPER(String.format("INCH `d0,%d", high), uses, defs, null));

        if (negativeConstant)
            asmInstrs.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));

        return dest;
    }

    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> asmInstrs) {
        MemTemp dest = new MemTemp();
        MemTemp addrTmp = mem.addr.accept(this, asmInstrs);
        Vector<MemTemp> uses = new Vector<>();
        uses.add(addrTmp);
        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        asmInstrs.add(new AsmMOVE("LDO `d0,`s0,0", uses, defs));
        return dest;
    }

    public MemTemp visit(ImcNAME name, Vector<AsmInstr> asmInstrs) {
        MemTemp dest = new MemTemp();

        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        asmInstrs.add(new AsmOPER(String.format("LDA `d0,%s", name.label.name), null, defs, null));

        return dest;
    }

    public MemTemp visit(ImcSEXPR sExpr, Vector<AsmInstr> asmInstrs) {
        Vector<AsmInstr> stmts = sExpr.stmt.accept(new StmtGenerator(), null);
        asmInstrs.addAll(stmts);

        return sExpr.expr.accept(this, asmInstrs);
    }

    public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> asmInstrs) {
        return temp.temp;
    }

    public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> asmInstrs) {
        MemTemp dest = new MemTemp();
        MemTemp expr = unOp.subExpr.accept(this, asmInstrs);

        Vector<MemTemp> uses = new Vector<>();
        uses.add(expr);
        Vector<MemTemp> defs = new Vector<>();
        defs.add(dest);

        switch (unOp.oper) {
            // XOR with 1 flips all bits of input
            case NOT -> asmInstrs.add(new AsmOPER("XOR `d0,`s0,1", uses, defs, null));
            case NEG -> asmInstrs.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));
        }

        return dest;
    }
}
