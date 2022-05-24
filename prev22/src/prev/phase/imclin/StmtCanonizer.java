package prev.phase.imclin;

import java.util.*;

import prev.common.report.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;

/**
 * Statement canonizer.
 */
public class StmtCanonizer implements ImcVisitor<Vector<ImcStmt>, Object> {
    public Vector<ImcStmt> visit(ImcCJUMP cjump, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();
        ImcExpr imcExpr = cjump.cond.accept(new ExprCanonizer(), vec);
        vec.add(new ImcCJUMP(imcExpr, cjump.posLabel, cjump.negLabel));
        return vec;
    }

    public Vector<ImcStmt> visit(ImcESTMT eStmt, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();

        if (eStmt.expr instanceof ImcCALL call) {
            Vector<ImcExpr> argExprs = new Vector<>();
            for (ImcExpr arg : call.args) {
                ImcExpr argExpr = arg.accept(new ExprCanonizer(), vec);
                argExprs.add(argExpr);
            }
            vec.add(new ImcESTMT(new ImcCALL(call.label, call.offs, argExprs)));
        } else {
            ImcExpr imcExpr = eStmt.expr.accept(new ExprCanonizer(), vec);
            vec.add(new ImcESTMT(imcExpr));
        }

        return vec;
    }

    public Vector<ImcStmt> visit(ImcJUMP jump, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();
        vec.add(new ImcJUMP(jump.label));
        return vec;
    }

    public Vector<ImcStmt> visit(ImcLABEL label, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();
        vec.add(new ImcLABEL(label.label));
        return vec;
    }

    public Vector<ImcStmt> visit(ImcMOVE move, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();
        if (move.dst instanceof ImcMEM dstMEM) {
            ImcExpr dst = dstMEM.addr.accept(new ExprCanonizer(), vec);
            MemTemp dstTmp = new MemTemp();
            vec.add(new ImcMOVE(new ImcTEMP(dstTmp), dst));
            ImcExpr src = move.src.accept(new ExprCanonizer(), vec);
            MemTemp srcTmp = new MemTemp();
            vec.add(new ImcMOVE(new ImcTEMP(srcTmp), src));
            vec.add(new ImcMOVE(new ImcMEM(new ImcTEMP(dstTmp)), new ImcTEMP(srcTmp)));
            return vec;
        } else if (move.dst instanceof ImcTEMP dstTEMP) {
            MemTemp dstTmp = dstTEMP.temp;
            ImcExpr src = move.src.accept(new ExprCanonizer(), vec);
            MemTemp srcTmp = new MemTemp();
            vec.add(new ImcMOVE(new ImcTEMP(srcTmp), src));
            vec.add(new ImcMOVE(new ImcTEMP(dstTmp), new ImcTEMP(srcTmp)));
            return vec;
        }
        throw new Report.InternalError();
    }

    public Vector<ImcStmt> visit(ImcSTMTS stmts, Object obj) {
        Vector<ImcStmt> vec = new Vector<>();
        for (ImcStmt stmt : stmts.stmts)
            vec.addAll(stmt.accept(this, null));
        return vec;
    }
}
