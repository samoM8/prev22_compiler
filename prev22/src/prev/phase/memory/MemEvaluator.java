package prev.phase.memory;

import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.typ.*;
import prev.data.mem.*;
import prev.phase.seman.*;

import java.util.HashMap;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, MemEvaluator.Context> {

    /**
     * The context {@link MemEvaluator} uses while computing function frames and
     * variable accesses.
     */
    protected abstract class Context {
    }

    // Don't need this because we get the sizes from SemType classes
    // private static final int MEM_ALIGNMENT = 8;

    private class FunctionContext extends Context {
        int depth = 0;
        long locsSize = 0;
        long argsSize = 0;
        long parsSize = 8; // We count parameters only for accessing them in memory
        // Value is 8 because of Static Link
    }

    @Override
    public Object visit(AstFunDecl funDecl, Context ctx) {
        FunctionContext funCtx = new FunctionContext();
        // always make new context, if ctx is not null it means we are in
        // a function so the depth is one larger
        if (ctx != null)
            funCtx.depth = ((FunctionContext) ctx).depth + 1;
        else
            funCtx.depth = 1;

        // In pars, I will count the number of parameters.
        // In expr, I will count number of local vars etc.
        if (funDecl.pars != null)
            funDecl.pars.accept(this, funCtx);
        if (funDecl.type != null)
            funDecl.type.accept(this, funCtx);
        if (funDecl.expr != null)
            funDecl.expr.accept(this, funCtx);

        MemFrame memFrame;
        if (ctx == null) {
            // Must have its own name because it's in global scope
            memFrame = new MemFrame(new MemLabel(funDecl.name), funCtx.depth - 1, funCtx.locsSize, funCtx.argsSize);
        } else {
            // We just assign it a generic name that MemLabel returns
            memFrame = new MemFrame(new MemLabel(), funCtx.depth - 1, funCtx.locsSize, funCtx.argsSize);
        }

        Memory.frames.put(funDecl, memFrame);

        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Context ctx) {
        SemType semType = SemAn.isType.get(varDecl.type); //get type of variable

        if (ctx == null) {
            // Global scope - variables are accessed through absolute address
            MemAbsAccess memAbsAccess = new MemAbsAccess(semType.size(), new MemLabel(varDecl.name));
            Memory.accesses.put(varDecl, memAbsAccess);
        } else {
            //inside a function
            // we add size first because our stack pointers points at last occupied spot
            ((FunctionContext) ctx).locsSize += semType.size();
            MemRelAccess memRelAccess = new MemRelAccess(semType.size(), -((FunctionContext) ctx).locsSize, ((FunctionContext) ctx).depth);
            Memory.accesses.put(varDecl, memRelAccess);
        }

        varDecl.type.accept(this, ctx);
        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Context ctx) {
        SemType semType = SemAn.isType.get(parDecl.type);

        // Context shouldn't be null since we can only enter from fun decl
        MemRelAccess memRelAccess = new MemRelAccess(semType.size(), ((FunctionContext) ctx).parsSize, ((FunctionContext) ctx).depth);
        ((FunctionContext) ctx).parsSize += semType.size();
        Memory.accesses.put(parDecl, memRelAccess);

        parDecl.type.accept(this, ctx);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Context ctx) {
        if (atomExpr.type == AstAtomExpr.Type.STRING) {
            String string = atomExpr.value; //.replaceAll("\\\\\"", "\"");
            //we don't need to substring quotes because professor does it in interpreter
            //maybe wi will have to fix this later
            //string = string.substring(1, string.length()-1);

            // The number is 8 because it is the size of Char
            MemAbsAccess memAbsAccess = new MemAbsAccess((string.length() - 1) * 8L, new MemLabel(), string);
            Memory.strings.put(atomExpr, memAbsAccess);
        }

        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, Context ctx) {
        // Context can't be null because we can't have a function call in global scope
        // Args can't be null because function without parameters is NameExpr
        callExpr.args.accept(this, ctx);

        long argsSize = 8; // Static Link
        for (AstExpr arg : callExpr.args) {
            SemType semType = SemAn.ofType.get(arg);
            argsSize += semType.size();
        }

        // We must do max if we, because if there are more than 1 function call we have
        // to reserve so much space that we can put all arguments in memory from a
        // function call with the most arguments
        ((FunctionContext) ctx).argsSize = Math.max(((FunctionContext) ctx).argsSize, argsSize);

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Context ctx) {
        // function with no parameters

        AstDecl astDecl = SemAn.declaredAt.get(nameExpr);
        if (astDecl instanceof AstFunDecl)
            ((FunctionContext) ctx).argsSize = Math.max(((FunctionContext) ctx).argsSize, 8L); //Only needs static link

        return null;
    }

    private int compDepth = -1;
    private final HashMap<Integer, Long> compDepthSizes = new HashMap<>();

    @Override
    public Object visit(AstCompDecl compDecl, Context ctx) {
        compDecl.type.accept(this, ctx);

        SemType semType = SemAn.isType.get(compDecl.type);
        //depth is 0 for record components
        MemRelAccess memRelAccess = new MemRelAccess(semType.size(), compDepthSizes.get(compDepth), 0);
        Memory.accesses.put(compDecl, memRelAccess);

        compDepthSizes.put(compDepth, compDepthSizes.get(compDepth) + semType.size());

        return null;
    }

    @Override
    public Object visit(AstRecType recType, Context ctx) {
        compDepth++;
        compDepthSizes.put(compDepth, 0L);

        recType.comps.accept(this, ctx);

        compDepthSizes.remove(compDepth);
        compDepth--;

        return null;
    }
}
