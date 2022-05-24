package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.AstDecl;
import prev.data.ast.tree.decl.AstParDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.visitor.*;
import prev.data.typ.SemPtr;
import prev.data.typ.SemType;

/**
 * Address resolver.
 * 
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Object, Object> {
    @Override
    public Boolean visit(AstNameExpr nameExpr, Object obj) {
        AstDecl decl = SemAn.declaredAt.get(nameExpr);
        if (decl instanceof AstParDecl) {
            SemAn.isAddr.put(nameExpr, true);
            return true;
        }
        if (decl instanceof AstVarDecl) {
            SemAn.isAddr.put(nameExpr, true);
            return true;
        }

        return false;
    }

    @Override
    public Boolean visit(AstSfxExpr sfxExpr, Object obj) {
        if (sfxExpr.expr != null) {
            sfxExpr.expr.accept(this, obj);

            SemType semType = SemAn.ofType.get(sfxExpr.expr);
            if (semType != null && semType.actualType() instanceof SemPtr) {
                SemAn.isAddr.put(sfxExpr, true);
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean visit(AstArrExpr arrExpr, Object obj) {
        if (arrExpr.arr != null)
            arrExpr.arr.accept(this, obj);
        else
            return false;
        if (arrExpr.idx != null)
            arrExpr.idx.accept(this, obj);

        Boolean isAddress = SemAn.isAddr.get(arrExpr.arr);
        if (isAddress != null && isAddress) {
            SemAn.isAddr.put(arrExpr, true);
            return true;
        }

        return false;
    }

    @Override
    public Boolean visit(AstAssignStmt assignStmt, Object obj) {
        if (assignStmt.dst != null)
            assignStmt.dst.accept(this, obj);
        else
            return false;
        if (assignStmt.src != null)
            assignStmt.src.accept(this, obj);

        Boolean lAddress = SemAn.isAddr.get(assignStmt.dst);
        if (lAddress == null || !lAddress)
            throw new Report.Error(assignStmt, "Cannot assign this expression.");

        return false;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Object obj) {
        if (recExpr.rec != null)
            recExpr.rec.accept(this, obj);
        else
            return false;
        if (recExpr.comp != null)
            recExpr.comp.accept(this, obj);

        Boolean isAddress = SemAn.isAddr.get(recExpr.rec);
        if (isAddress != null && isAddress) {
            SemAn.isAddr.put(recExpr, true);
        }

        return false;
    }
}
