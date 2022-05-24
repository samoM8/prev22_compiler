// Generated from /home/samom8/Desktop/faks/3.Letnik/2.semester/Prevajalniki/prev22/src/prev/phase/synan/PrevParser.g4 by ANTLR 4.9.2


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.data.ast.tree.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.type.*;
	import prev.phase.lexan.*;
	

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PrevParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PrevParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PrevParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(PrevParser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrevParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(PrevParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrevParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PrevParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrevParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PrevParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrevParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(PrevParser.StmtContext ctx);
}