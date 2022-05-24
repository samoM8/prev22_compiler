package prev.phase.seman;

import java.util.*;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;

/**
 * Type resolver.
 * <p>
 * Type resolver computes the values of {@link SemAn#declaresType},
 * {@link SemAn#isType}, and {@link SemAn#ofType}.
 */
public class TypeResolver extends AstFullVisitor<SemType, TypeResolver.Mode> {

    public enum Mode {
        DECLARE_TYPES, TYPE_DECLS, ARR_REC_TYPES, VAR_DECLS, DECLARE_FUN, FUN_DECLS
    }

    private final HashMap<SemRec, SymbTable> recordComps = new HashMap<>();

    // GENERAL PURPOSE
    @Override
    public SemType visit(AstTrees<? extends AstTree> trees, Mode mode) {
        // Declare types
        // Go through all type declarations and store them into SemAn.declaresType with their SemName
        for (AstTree t : trees)
            if (t instanceof AstTypeDecl)
                t.accept(this, Mode.DECLARE_TYPES);

        // Types
        // Go through all type declarations, get them from SemAn.declaresType and check for type
        // of declaration. To SemName define found SemType
        for (AstTree t : trees)
            if (t instanceof AstTypeDecl)
                t.accept(this, Mode.TYPE_DECLS);

        // For arrays and records we need another pass
        for (AstTree t : trees)
            if (t instanceof AstTypeDecl)
                t.accept(this, Mode.ARR_REC_TYPES);

        // Var Decls
        for (AstTree t : trees)
            if (t instanceof AstVarDecl)
                t.accept(this, Mode.VAR_DECLS);

        // Fun Decls
        // First we have to go through only decls so functions are defined
        // Then we check for expr in function because it can contain function calls
        for (AstTree t : trees)
            if (t instanceof AstFunDecl)
                t.accept(this, Mode.DECLARE_FUN);

        for (AstTree t : trees)
            if (t instanceof AstFunDecl)
                t.accept(this, Mode.FUN_DECLS);

        return null;
    }

    // DECLARATIONS

    /* I don't need this because I handle it in AstRec
    @Override
    public Result visit(AstCompDecl compDecl, Arg arg) {
        if (compDecl.type != null)
            compDecl.type.accept(this, arg);
        return null;
    }
     */

    @Override
    public SemType visit(AstFunDecl funDecl, Mode mode) {
        if (mode == Mode.DECLARE_FUN) {
            SemType funType = funDecl.type.accept(this, mode);

            if (!(funType.actualType() instanceof SemVoid) && !(funType.actualType() instanceof SemBool) &&
                    !(funType.actualType() instanceof SemChar) && !(funType.actualType() instanceof SemInt) &&
                    !(funType.actualType() instanceof SemPtr))
                throw new Report.Error(funDecl, "Return type of a function must be void, bool, char, int or pointer.");

            if (funDecl.pars != null)
                for (AstParDecl par : funDecl.pars)
                    par.accept(this, mode);
        }

        if (mode == Mode.FUN_DECLS) {
            // Get function expected return type
            SemType returnType = SemAn.isType.get(funDecl.type);
            // Check if expression exists and then get actual return type
            // and compare if the types are equal
            if (funDecl.expr != null) {
                SemType exprType = funDecl.expr.accept(this, mode);

                if (!(exprType.actualType().getClass().equals(returnType.actualType().getClass())))
                    throw new Report.Error(funDecl, "Expected and expression return type are not equal.");
            }
        }

        return null;
    }

    @Override
    public SemType visit(AstParDecl parDecl, Mode mode) {
        SemType semType = parDecl.type.accept(this, mode);

        if (!(semType.actualType() instanceof SemBool) && !(semType.actualType() instanceof SemChar) &&
                !(semType.actualType() instanceof SemInt) && !(semType.actualType() instanceof SemPtr))
            throw new Report.Error(parDecl, "Parameter type must be bool, char, int or pointer.");

        return semType;
    }

    @Override
    public SemType visit(AstTypeDecl typeDecl, Mode mode) {
        // Store type declaration in SemAm.declaresType with their SemName
        if (mode == Mode.DECLARE_TYPES) {
            SemAn.declaresType.put(typeDecl, new SemName(typeDecl.name));
        }

        // Define SemType for SemName
        if (mode == Mode.TYPE_DECLS) {
            if (!(typeDecl.type instanceof AstRecType) && !(typeDecl.type instanceof AstArrType)) {
                SemName semName = SemAn.declaresType.get(typeDecl);
                SemType semType = typeDecl.type.accept(this, mode);
                //SemType semType = SemAn.isType.get(typeDecl.type);

                if (semType == null) {
                    throw new Report.Error(typeDecl, "No type found.");
                }

                semName.define(semType);
            }
        }

        if (mode == Mode.ARR_REC_TYPES) {
            if (typeDecl.type instanceof AstRecType || typeDecl.type instanceof AstArrType) {
                SemType semType = typeDecl.type.accept(this, mode);
                SemName semName = SemAn.declaresType.get(typeDecl);
                semName.define(semType);
            }
        }

        return null;
    }

    @Override
    public SemType visit(AstVarDecl varDecl, Mode mode) {
        if (mode == Mode.VAR_DECLS) {
            SemType semType = varDecl.type.accept(this, mode);

            if (semType.actualType() instanceof SemVoid)
                throw new Report.Error(varDecl, "Var of type void is not allowed.");
        }

        return null;
    }

    // EXPRESSIONS

    @Override
    public SemType visit(AstArrExpr arrExpr, Mode mode) {
        SemType arrType = arrExpr.arr.accept(this, mode);
        if (!(arrType.actualType() instanceof SemArr))
            throw new Report.Error(arrExpr, "Symbol must be an array.");

        SemType idxType = arrExpr.idx.accept(this, mode);
        if (!(idxType.actualType() instanceof SemInt))
            throw new Report.Error(arrExpr, "Index must be an integer.");

        // do i need actual type here??
        SemAn.ofType.put(arrExpr, ((SemArr) arrType.actualType()).elemType);
        return ((SemArr) arrType.actualType()).elemType;
    }

    @Override
    public SemType visit(AstAtomExpr atomExpr, Mode mode) {
        SemType semType;
        switch (atomExpr.type) {
            case VOID -> semType = new SemVoid();
            case CHAR -> semType = new SemChar();
            case INT -> semType = new SemInt();
            case BOOL -> semType = new SemBool();
            case POINTER -> semType = new SemPtr(new SemVoid());
            case STRING -> semType = new SemPtr(new SemChar());
            default -> throw new Report.Error(atomExpr.location(), "Non-existent Type.");
        }

        SemAn.ofType.put(atomExpr, semType);
        return semType;
    }

    @Override
    public SemType visit(AstBinExpr binExpr, Mode mode) {
        SemType fstExpr = binExpr.fstExpr.accept(this, mode);
        SemType sndExpr = binExpr.sndExpr.accept(this, mode);

        switch (binExpr.oper) {
            case MUL, DIV, MOD, ADD, SUB -> {
                if (!(fstExpr.actualType() instanceof SemInt && sndExpr.actualType() instanceof SemInt))
                    throw new Report.Error(binExpr, "First and second expression must be type int.");

                SemType exprType = new SemInt();
                SemAn.ofType.put(binExpr, exprType);
                return exprType;
            }
            case EQU, NEQ -> {
                if (!(fstExpr.actualType() instanceof SemBool && sndExpr.actualType() instanceof SemBool)
                        && !(fstExpr.actualType() instanceof SemChar && sndExpr.actualType() instanceof SemChar)
                        && !(fstExpr.actualType() instanceof SemInt && sndExpr.actualType() instanceof SemInt)
                        && !(fstExpr.actualType() instanceof SemPtr && sndExpr.actualType() instanceof SemPtr))
                    throw new Report.Error(binExpr, "First and second expression must be type bool, char, int or pointer.");
                if (!(fstExpr.actualType().getClass().equals(sndExpr.actualType().getClass())))
                    throw new Report.Error(binExpr, "First and second expressions must be same type.");

                SemType exprType = new SemBool();
                SemAn.ofType.put(binExpr, exprType);
                return exprType;
            }
            case LEQ, GEQ, LTH, GTH -> {
                if (!(fstExpr.actualType() instanceof SemChar && sndExpr.actualType() instanceof SemChar)
                        && !(fstExpr.actualType() instanceof SemInt && sndExpr.actualType() instanceof SemInt)
                        && !(fstExpr.actualType() instanceof SemPtr && sndExpr.actualType() instanceof SemPtr))
                    throw new Report.Error(binExpr, "First and second expression must be type char, int or pointer.");
                if (!(fstExpr.actualType().getClass().equals(sndExpr.actualType().getClass())))
                    throw new Report.Error(binExpr, "First and second expressions must be same type.");

                SemType exprType = new SemBool();
                SemAn.ofType.put(binExpr, exprType);
                return exprType;
            }
            case OR, AND -> {
                if (!(fstExpr.actualType() instanceof SemBool && sndExpr.actualType() instanceof SemBool))
                    throw new Report.Error(binExpr, "First and second expression must be type bool.");

                SemType exprType = new SemBool();
                SemAn.ofType.put(binExpr, exprType);
                return exprType;
            }
        }

        return null;
    }

    @Override
    public SemType visit(AstCallExpr callExpr, Mode mode) {
        AstFunDecl astFunDecl = (AstFunDecl) SemAn.declaredAt.get(callExpr);
        // don't need to check the return type since I already did in AstFunDecl
        SemType semType = SemAn.isType.get(astFunDecl.type);

        if (astFunDecl.pars.size() != callExpr.args.size())
            throw new Report.Error(callExpr, "Function call does not have the same number of arguments as function declaration.");

        Iterator<AstParDecl> pars = astFunDecl.pars.iterator();
        Iterator<AstExpr> args = callExpr.args.iterator();

        // Check if argument and parameter types match
        while (pars.hasNext() && args.hasNext()) {
            SemType parType = SemAn.isType.get(pars.next().type);
            SemType argType = args.next().accept(this, mode);
            if (!(argType.actualType() instanceof SemBool) && !(argType.actualType() instanceof SemChar)
                    && !(argType.actualType() instanceof SemInt) && !(argType.actualType() instanceof SemPtr))
                throw new Report.Error(callExpr, "Arguments must be type bool, char, int or pointer.");

            if (!(parType.actualType().getClass().equals(argType.actualType().getClass())))
                throw new Report.Error(callExpr, "Argument types does not match with function parameters types.");
        }

        SemAn.ofType.put(callExpr, semType);
        return semType;
    }

    @Override
    public SemType visit(AstCastExpr castExpr, Mode mode) {
        SemType exprType = castExpr.expr.accept(this, mode);
        if (!(exprType.actualType() instanceof SemChar) &&
                !(exprType.actualType() instanceof SemInt) &&
                !(exprType.actualType() instanceof SemPtr))
            throw new Report.Error(castExpr, "Expression must be type char, int or pointer.");

        SemType typeType = castExpr.type.accept(this, mode);
        if (!(typeType.actualType() instanceof SemChar) &&
                !(typeType.actualType() instanceof SemInt) &&
                !(typeType.actualType() instanceof SemPtr))
            throw new Report.Error(castExpr, "Type must be type char, int or pointer.");

        SemAn.ofType.put(castExpr, typeType);
        return typeType;
    }

    @Override
    public SemType visit(AstNameExpr nameExpr, Mode mode) {
        AstDecl decl = SemAn.declaredAt.get(nameExpr);
        // Var name, function name with no parameters, parameter name
        if (decl instanceof AstParDecl parDecl) {
            SemType semType = SemAn.isType.get(parDecl.type);
            SemAn.ofType.put(nameExpr, semType);
            return semType;
        } else if (decl instanceof AstVarDecl varDecl) {
            SemType semType = SemAn.isType.get(varDecl.type);
            SemAn.ofType.put(nameExpr, semType);
            return semType;
        } else if (decl instanceof AstFunDecl funDecl) {
            if (funDecl.pars != null && funDecl.pars.size() > 0)
                throw new Report.Error(nameExpr, "Number of function parameters is not 0.");

            SemType semType = SemAn.isType.get(funDecl.type);
            SemAn.ofType.put(nameExpr, semType);
            return semType;
        } else if (decl instanceof AstCompDecl compDecl) {
            SemType semType = SemAn.isType.get(compDecl.type);
            SemAn.ofType.put(nameExpr, semType);
            return semType;
        } else {
            throw new Report.Error(nameExpr, "Name expression declaration must be parameter, variable or function");
        }
    }

    @Override
    public SemType visit(AstPfxExpr pfxExpr, Mode mode) {
        SemType semType = pfxExpr.expr.accept(this, mode);
        switch (pfxExpr.oper) {
            case NOT -> {
                if (semType.actualType() instanceof SemBool) {
                    SemAn.ofType.put(pfxExpr, semType);
                    return semType;
                } else {
                    throw new Report.Error(pfxExpr, "Expression must be type bool.");
                }
            }
            case ADD, SUB -> {
                if (semType.actualType() instanceof SemInt) {
                    SemAn.ofType.put(pfxExpr, semType);
                    return semType;
                } else {
                    throw new Report.Error(pfxExpr, "Expression must be type int.");
                }
            }
            case NEW -> {
                if (semType.actualType() instanceof SemInt) {
                    SemType type = new SemPtr(new SemVoid());
                    SemAn.ofType.put(pfxExpr, type);
                    return type;
                } else {
                    throw new Report.Error(pfxExpr, "Expression must be type int.");
                }
            }
            case DEL -> {
                if (semType.actualType() instanceof SemPtr) {
                    SemType type = new SemVoid();
                    SemAn.ofType.put(pfxExpr, type);
                    return type;
                } else {
                    throw new Report.Error(pfxExpr, "Expression must be type pointer.");
                }
            }
            case PTR -> {
                if (semType == null)
                    throw new Report.Error(pfxExpr, "Expression must have a type.");
                SemType type = new SemPtr(semType);
                SemAn.ofType.put(pfxExpr, type);
                return type;
            }
        }

        return null;
    }

    @Override
    public SemType visit(AstRecExpr recExpr, Mode mode) {
        SemType recType = recExpr.rec.accept(this, mode);

        if (!(recType.actualType() instanceof SemRec))
            throw new Report.Error(recExpr, "Record expression must be type record.");

        SymbTable symbTable = recordComps.get(recType.actualType());

        try {
            AstDecl decl = symbTable.fnd(recExpr.comp.name);
            if (decl instanceof AstCompDecl) {
                SemType semType = SemAn.isType.get(((AstCompDecl) decl).type);
                SemAn.ofType.put(recExpr, semType);

                SemAn.declaredAt.put(recExpr.comp, decl);
                recExpr.comp.accept(this, mode);
                return semType;
            } else {
                throw new Report.Error(recExpr, "Not a component name: " + recExpr.comp.name);
            }
        } catch (SymbTable.CannotFndNameException e) {
            throw new Report.Error(recExpr, "Component name does not exists in this record: " + recExpr.comp.name);
        }
    }

    @Override
    public SemType visit(AstSfxExpr sfxExpr, Mode mode) {
        SemType semType = sfxExpr.expr.accept(this, mode);

        if (semType.actualType() instanceof SemPtr) {
            SemType exprType = sfxExpr.expr.accept(this, mode);
            SemAn.ofType.put(sfxExpr, ((SemPtr) exprType.actualType()).baseType);
            return ((SemPtr) exprType.actualType()).baseType;
        } else {
            throw new Report.Error(sfxExpr, "Expression must be pointer.");
        }
    }

    @Override
    public SemType visit(AstStmtExpr stmtExpr, Mode mode) {
        SemType semType = null;
        for (AstStmt stmt : stmtExpr.stmts) {
            semType = stmt.accept(this, mode);
        }

        SemAn.ofType.put(stmtExpr, semType);
        return semType;
    }

    @Override
    public SemType visit(AstWhereExpr whereExpr, Mode mode) {
        if (whereExpr.decls != null)
            whereExpr.decls.accept(this, mode);

        SemType exprType = whereExpr.expr.accept(this, mode);

        SemAn.ofType.put(whereExpr, exprType);
        return exprType;
    }

    // STATEMENTS

    @Override
    public SemType visit(AstAssignStmt assignStmt, Mode mode) {
        SemType expr1Type = assignStmt.dst.accept(this, mode);
        if (!(expr1Type.actualType() instanceof SemBool) && !(expr1Type.actualType() instanceof SemChar) &&
                !(expr1Type.actualType() instanceof SemInt) && !(expr1Type.actualType() instanceof SemPtr))
            throw new Report.Error(assignStmt.dst, "Expression must be type bool, char, int or pointer");

        SemType expr2Type = assignStmt.src.accept(this, mode);
        if (!(expr2Type.actualType() instanceof SemBool) && !(expr2Type.actualType() instanceof SemChar) &&
                !(expr2Type.actualType() instanceof SemInt) && !(expr2Type.actualType() instanceof SemPtr))
            throw new Report.Error(assignStmt.src, "Expression must be type bool, char, int or pointer");

        if (!(expr1Type.actualType().getClass().equals(expr2Type.actualType().getClass())))
            throw new Report.Error(assignStmt, "Destination and source must be same type.");

        SemType assignType = new SemVoid();
        SemAn.ofType.put(assignStmt, assignType);
        return assignType;
    }

    @Override
    public SemType visit(AstExprStmt exprStmt, Mode mode) {
        SemType exprType = exprStmt.expr.accept(this, mode);
        SemAn.ofType.put(exprStmt, exprType);
        return exprType;
    }

    @Override
    public SemType visit(AstIfStmt ifStmt, Mode mode) {
        SemType condType = ifStmt.cond.accept(this, mode);
        if (!(condType.actualType() instanceof SemBool))
            throw new Report.Error(ifStmt.cond, "Condition must be of type bool.");

        SemType thenType = ifStmt.thenStmt.accept(this, mode);
        if (thenType == null)
            throw new Report.Error(ifStmt.thenStmt, "If statement with empty then statement.");

        SemType elseType = ifStmt.elseStmt.accept(this, mode);
        if (elseType == null)
            throw new Report.Error(ifStmt.elseStmt, "If statement with empty else statement.");

        SemType ifType = new SemVoid();
        SemAn.ofType.put(ifStmt, ifType);
        return ifType;
    }

    @Override
    public SemType visit(AstWhileStmt whileStmt, Mode mode) {
        SemType condType = whileStmt.cond.accept(this, mode);
        if (!(condType.actualType() instanceof SemBool))
            throw new Report.Error(whileStmt.cond, "Condition must be of type bool.");

        SemType bodyStmt = whileStmt.bodyStmt.accept(this, mode);
        if (bodyStmt == null)
            throw new Report.Error(whileStmt.bodyStmt, "While statement with no body.");

        SemType whileType = new SemVoid();
        SemAn.ofType.put(whileStmt, whileType);
        return whileType;
    }

    // TYPES

    @Override
    public SemType visit(AstArrType arrType, Mode mode) {
        //todo nevem a je lahko se pfx torej [+42]. Pol morem se to dodat.
        /*
        if (arrType.numElems instanceof  AstPfxExpr &&
                ((AstPfxExpr) arrType.numElems).oper == AstPfxExpr.Oper.ADD) {

        }
         */
        // Check if expression is atom
        if (!(arrType.numElems instanceof AstAtomExpr arrExpr))
            throw new Report.Error(arrType.numElems, "Expression in array declarations should be AtomExpression.");

        //I don't need this because intellij introduced pattern variable in if above
        //AstAtomExpr arrExpr = (AstAtomExpr) arrType.numElems;
        // Check if expression is integer
        if (arrExpr.type != AstAtomExpr.Type.INT)
            throw new Report.Error(arrType.numElems, "Expression in array declaration should be an integer.");

        arrType.numElems.accept(this, mode);
        // Check the size of integer
        long val;
        try {
            val = Long.parseLong(arrExpr.value);
        } catch (NumberFormatException e) {
            throw new Report.Error(arrType.numElems, "Parsing error: " + e.getMessage());
        }
        if (val < 0)
            throw new Report.Error(arrType.numElems, "Value should be between 0 and 2^63-1.");

        // Get type of array
        SemType semType = arrType.elemType.accept(this, mode);
        if (semType.actualType() instanceof SemVoid)
            throw new Report.Error(arrType, "Array of type void is not allowed.");

        SemArr semArr = new SemArr(semType, val);
        SemAn.isType.put(arrType, semArr);

        return semArr;
    }

    @Override
    public SemType visit(AstAtomType atomType, Mode mode) {
        SemType semType;
        switch (atomType.type) {
            case VOID -> semType = new SemVoid();
            case CHAR -> semType = new SemChar();
            case INT -> semType = new SemInt();
            case BOOL -> semType = new SemBool();
            default -> throw new Report.Error(atomType.location(), "Non-existent Type.");
        }

        SemAn.isType.put(atomType, semType);
        return semType;

    }

    @Override
    public SemType visit(AstNameType nameType, Mode mode) {
        // Get declaration for this name type
        AstDecl nameDecl = SemAn.declaredAt.get(nameType);

        // The type have to be instanceof TypeDecl or the AstNameType is not a type and could be something else
        if (!(nameDecl instanceof AstTypeDecl))
            throw new Report.Error(nameType, "No type declared for this name: " + nameType.name);

        // Get SemName from SemAn.declaresType
        SemType semType = SemAn.declaresType.get((AstTypeDecl) nameDecl);

        // Store this SemName to SemAn.isType
        SemAn.isType.put(nameType, semType);

        return semType;
    }

    @Override
    public SemType visit(AstPtrType ptrType, Mode mode) {
        // Accept the base type which will return SemType
        SemType semType = ptrType.baseType.accept(this, mode);
        SemType pointer = new SemPtr(semType);
        SemAn.isType.put(ptrType, pointer);

        return pointer;
    }

    @Override
    public SemType visit(AstRecType recType, Mode mode) {
        SymbTable symbTable = new SymbTable();
        List<SemType> semTypes = new LinkedList<>();
        for (AstCompDecl comp : recType.comps) {
            SemType semType = comp.type.accept(this, mode);
            //SemType cannot be type void in RecType
            if (semType.actualType() instanceof SemVoid)
                throw new Report.Error(comp.location(), "Void type in record expression is not allowed.");
            semTypes.add(semType);

            try {
                symbTable.ins(comp.name, comp);
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error(recType, "Cannot use components with same name: " + comp.name);
            }
        }
        SemRec semRec = new SemRec(semTypes);
        SemAn.isType.put(recType, semRec);

        recordComps.put(semRec, symbTable); //add component to hash map

        return semRec;
    }
}
