package prev.phase.asmgen;

import java.util.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.*;
import prev.data.asm.*;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {
    public Vector<AsmInstr> visit(ImcCJUMP cjump, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        MemTemp cond = cjump.cond.accept(new ExprGenerator(), asmInstrs);
        Vector<MemTemp> uses = new Vector<>();
        uses.add(cond);
        Vector<MemLabel> jumpsPos = new Vector<>();
        jumpsPos.add(cjump.posLabel);
        jumpsPos.add(cjump.negLabel);
        // Vector<MemLabel> jumpsNeg = new Vector<>();
        // jumpsNeg.add(cjump.negLabel);

        // Branch if positive - if condition is true
        asmInstrs.add(new AsmOPER(String.format("BP `s0,%s", cjump.posLabel.name), uses, null, jumpsPos));
        // If condition is false we do not have to jump anywhere.
        // Branch if zero - if condition is false
        // asmInstrs.add(new AsmOPER(String.format("BZ `s0,%s", cjump.negLabel), uses, null, jumpsNeg));

        return asmInstrs;
    }

    public Vector<AsmInstr> visit(ImcESTMT eStmt, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        eStmt.expr.accept(new ExprGenerator(), asmInstrs);

        return asmInstrs;
    }

    public Vector<AsmInstr> visit(ImcJUMP jump, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        //Jumps
        Vector<MemLabel> jumps = new Vector<>();
        jumps.add(jump.label);
        //There are no uses and no defs
        AsmOPER asmOPER = new AsmOPER(String.format("JMP %s", jump.label.name), null, null, jumps);
        asmInstrs.add(asmOPER);
        return asmInstrs;
    }

    public Vector<AsmInstr> visit(ImcLABEL label, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        asmInstrs.add(new AsmLABEL(label.label));
        return asmInstrs;
    }

    public Vector<AsmInstr> visit(ImcMOVE move, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();

        if (move.dst instanceof ImcMEM dstMem && move.src instanceof ImcMEM srcMem) {
            MemTemp srcTemp = srcMem.addr.accept(new ExprGenerator(), asmInstrs);
            MemTemp dstTemp = dstMem.addr.accept(new ExprGenerator(), asmInstrs);

            MemTemp loadTmp = new MemTemp();
            uses.add(srcTemp);
            defs.add(loadTmp);
            // loads from memory into register
            asmInstrs.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));

            uses.add(dstTemp);
            // stores register into memory
            asmInstrs.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
        } else if (move.dst instanceof ImcMEM dstMem) {
            MemTemp srcTemp = move.src.accept(new ExprGenerator(), asmInstrs);
            MemTemp dstTemp = dstMem.addr.accept(new ExprGenerator(), asmInstrs);

            uses.add(srcTemp);
            uses.add(dstTemp);
            // stores register into memory
            asmInstrs.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
        } else if (move.src instanceof ImcMEM srcMem) {
            MemTemp srcTemp = srcMem.addr.accept(new ExprGenerator(), asmInstrs);
            MemTemp dstTemp = move.dst.accept(new ExprGenerator(), asmInstrs);

            uses.add(srcTemp);
            defs.add(dstTemp);
            // load from memory to register
            asmInstrs.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));
        } else {
            MemTemp srcTemp = move.src.accept(new ExprGenerator(), asmInstrs);
            MemTemp dstTemp = move.dst.accept(new ExprGenerator(), asmInstrs);

            uses.add(srcTemp);
            defs.add(dstTemp);
            // move from register to another register
            asmInstrs.add(new AsmMOVE("SET `d0,`s0", uses, defs));
        }

        return asmInstrs;
    }

    public Vector<AsmInstr> visit(ImcSTMTS stmts, Object obj) {
        Vector<AsmInstr> asmInstrs = new Vector<>();
        for (ImcStmt stmt : stmts.stmts)
            asmInstrs.addAll(stmt.accept(this, obj));
        return asmInstrs;
    }
}
