package prev.phase.imclin;

import java.util.*;

import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.MemTemp;

/**
 * Expression canonizer.
 */
public class ExprCanonizer implements ImcVisitor<ImcExpr, Vector<ImcStmt>> {
    public ImcExpr visit(ImcBINOP binOp, Vector<ImcStmt> stmtVector) {
        ImcExpr fstExpr = binOp.fstExpr.accept(this, stmtVector);
        MemTemp tmp1 = new MemTemp();
        stmtVector.add(new ImcMOVE(new ImcTEMP(tmp1), fstExpr));

        ImcExpr sndExpr = binOp.sndExpr.accept(this, stmtVector);
        MemTemp tmp2 = new MemTemp();
        stmtVector.add(new ImcMOVE(new ImcTEMP(tmp2), sndExpr));

        return new ImcBINOP(binOp.oper, new ImcTEMP(tmp1), new ImcTEMP(tmp2));
    }

    public ImcExpr visit(ImcCALL call, Vector<ImcStmt> stmtVector) {
        Vector<ImcExpr> linArgs = new Vector<>();
        for (ImcExpr arg : call.args) {
            ImcExpr argExpr = arg.accept(this, stmtVector);
            MemTemp tmp = new MemTemp();
            stmtVector.add(new ImcMOVE(new ImcTEMP(tmp), argExpr));
            linArgs.add(new ImcTEMP(tmp));
        }
        MemTemp tmp = new MemTemp();
        stmtVector.add(new ImcMOVE(new ImcTEMP(tmp), new ImcCALL(call.label, call.offs, linArgs)));
        return new ImcTEMP(tmp);
    }

    public ImcExpr visit(ImcCONST constant, Vector<ImcStmt> stmtVector) {
        return new ImcCONST(constant.value);
    }

    public ImcExpr visit(ImcMEM mem, Vector<ImcStmt> stmtVector) {
        ImcExpr imcExpr = mem.addr.accept(this, stmtVector);
        return new ImcMEM(imcExpr);
    }

    public ImcExpr visit(ImcNAME name, Vector<ImcStmt> stmtVector) {
        return new ImcNAME(name.label);
    }

    public ImcExpr visit(ImcSEXPR sExpr, Vector<ImcStmt> stmtVector) {
        Vector<ImcStmt> stmts = sExpr.stmt.accept(new StmtCanonizer(), null);
        stmtVector.addAll(stmts);
        return sExpr.expr.accept(this, stmtVector);
    }

    public ImcExpr visit(ImcTEMP temp, Vector<ImcStmt> stmtVector) {
        return new ImcTEMP(temp.temp);
    }

    public ImcExpr visit(ImcUNOP unOp, Vector<ImcStmt> stmtVector) {
        ImcExpr subExpr = unOp.subExpr.accept(this, stmtVector);
        return new ImcUNOP(unOp.oper, subExpr);
    }
}
