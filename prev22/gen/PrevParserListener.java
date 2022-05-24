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
	

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrevParser}.
 */
public interface PrevParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrevParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(PrevParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrevParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(PrevParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrevParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(PrevParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrevParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(PrevParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrevParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PrevParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrevParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PrevParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrevParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PrevParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrevParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PrevParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrevParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(PrevParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrevParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(PrevParser.StmtContext ctx);
}