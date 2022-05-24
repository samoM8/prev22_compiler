package prev.phase.seman;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;

/**
 * Name resolver.
 * <p>
 * Name resolver connects each node of an abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link prev.phase.seman.SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, NameResolver.Mode> {

    public enum Mode {
        HEAD, BODY
    }

    private SymbTable symbTable = new SymbTable();

    // GENERAL PURPOSE

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Mode mode) {
        //go through all trees, first with HEAD mode than with BODY mode
        for (AstTree t : trees)
            if (t != null)
                t.accept(this, Mode.HEAD);

        for (AstTree t : trees)
            if (t != null)
                t.accept(this, Mode.BODY);

        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstFunDecl funDecl, Mode mode) {
        switch (mode) {
            case HEAD -> {
                try {
                    symbTable.ins(funDecl.name, funDecl);
                } catch (SymbTable.CannotInsNameException e) {
                    throw new Report.Error(funDecl, "Function with name '" + funDecl.name + "' is already declared.");
                }
            }
            case BODY -> {
                if (funDecl.pars != null) {
                    for (AstParDecl parDecl : funDecl.pars) {
                        //goes through types of parameters
                        parDecl.accept(this, Mode.BODY);
                    }
                }

                if (funDecl.type != null) {
                    funDecl.type.accept(this, Mode.BODY);
                }

                symbTable.newScope();

                if (funDecl.pars != null) {
                    for (AstParDecl parDecl : funDecl.pars) {
                        //goes through names of parameters
                        parDecl.accept(this, Mode.HEAD);
                    }
                }

                if (funDecl.expr != null) {
                    funDecl.expr.accept(this, mode);
                }

                symbTable.oldScope();
            }
        }

        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Mode mode) {
        if (parDecl.type != null) {
            switch (mode) {
                case HEAD -> {
                    try {
                        symbTable.ins(parDecl.name, parDecl);
                    } catch (SymbTable.CannotInsNameException e) {
                        throw new Report.Error(parDecl, "Parameter with name '" + parDecl.name + "' is already declared");
                    }
                }
                case BODY -> parDecl.type.accept(this, mode);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstTypeDecl typeDecl, Mode mode) {
        if (typeDecl.type != null) {
            switch (mode) {
                case HEAD -> {
                    try {
                        symbTable.ins(typeDecl.name, typeDecl);
                    } catch (SymbTable.CannotInsNameException e) {
                        throw new Report.Error(typeDecl, "Type with name '" + typeDecl.name + "' is already declared.");
                    }
                }
                case BODY -> typeDecl.type.accept(this, mode);
            }
        }

        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Mode mode) {
        if (varDecl.type != null) {
            switch (mode) {
                case HEAD -> {
                    try {
                        symbTable.ins(varDecl.name, varDecl);
                    } catch (SymbTable.CannotInsNameException e) {
                        throw new Report.Error(varDecl, "Variable with name '" + varDecl.name + "' is already declared");
                    }
                }
                case BODY -> varDecl.type.accept(this, mode);
            }
        }

        return null;
    }

    // EXPRESSIONS

    @Override
    public Object visit(AstCallExpr callExpr, Mode mode) {
        if (callExpr.args != null && mode == Mode.BODY) {
            try {
                AstDecl d = symbTable.fnd(callExpr.name);
                if (d instanceof AstFunDecl)
                    SemAn.declaredAt.put(callExpr, d);
                else
                    throw new Report.Error(callExpr, "Not a name of a function: " + callExpr.name);
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(callExpr, "Cannot find symbol: " + callExpr.name);
            }

            callExpr.args.accept(this, null); //args is trees
        }

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Mode mode) {
        if (mode == Mode.BODY) {
            try {
                AstDecl d = symbTable.fnd(nameExpr.name);
                
                if (d instanceof AstMemDecl || d instanceof AstFunDecl)
                    SemAn.declaredAt.put(nameExpr, d);
                else
                    throw new Report.Error(nameExpr, "Not a name of a symbol: " + nameExpr.name);
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(nameExpr, "Cannot find symbol: " + nameExpr.name);
            }
        }
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Mode mode) {
        if (recExpr.rec != null && mode == Mode.BODY)
            recExpr.rec.accept(this, mode);
        return null;
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, Mode mode) {
        if (mode == Mode.BODY) {
            symbTable.newScope();

            if (whereExpr.decls != null) {
                whereExpr.decls.accept(this, null); //decls is tree
            }

            if (whereExpr.expr != null) {
                whereExpr.expr.accept(this, mode);
            }

            symbTable.oldScope();
        }
        return null;
    }

    // TYPES

    @Override
    public Object visit(AstNameType nameType, Mode mode) {
        if (mode == Mode.BODY) {
            try {
                AstDecl d = symbTable.fnd(nameType.name);
                if (d instanceof AstTypeDecl)
                    SemAn.declaredAt.put(nameType, d);
                else
                    throw new Report.Error(nameType, "Not a name type: " + nameType.name);
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(nameType, "Cannot find symbol: " + nameType.name);
            }
        }
        return null;
    }

}
