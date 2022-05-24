parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.data.ast.tree.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.type.*;
	import prev.phase.lexan.*;
	
}

@members {

	private Location loc(Token     tok) { return new Location((prev.data.sym.Token)tok); }
	private Location loc(Locatable loc) { return new Location(loc                 ); }
	private Location loc(Token     tok1, Token     tok2) { return new Location((prev.data.sym.Token)tok1, (prev.data.sym.Token)tok2); }
	private Location loc(Token     tok1, Locatable loc2) { return new Location((prev.data.sym.Token)tok1, loc2); }
	private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1,                      (prev.data.sym.Token)tok2); }
	private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1,                      loc2); }

}

options{
    tokenVocab=PrevLexer;
}

source
    returns [AstTrees<AstDecl> ast]
    : { List<AstDecl> decls = new LinkedList<AstDecl>(); }
      ( decl { decls.add($decl.ast); } )+
      { $ast = new AstTrees<AstDecl>(decls); }
      EOF
    ;

//declarations
decl
    returns [AstDecl ast]
    : d_typ=TYP name=ID ASSIGN type
        {
        $ast = new AstTypeDecl(
            loc($d_typ, $type.ast),
            $name.getText(),
            $type.ast);
        }
    | d_var=VAR name=ID COLON type
        {
        $ast = new AstVarDecl(
            loc($d_var, $type.ast),
            $name.getText(),
            $type.ast);
        }
    | d_fun=FUN nameI=ID LPAREN
        { List<AstParDecl> pars = new LinkedList<AstParDecl>(); }
        (nameII=ID COLON typeII=type
        { pars.add(new AstParDecl(loc($nameII, $typeII.ast), $nameII.getText(), $typeII.ast)); }
        (COMMA nameIII=ID COLON typeIII=type
        { pars.add(new AstParDecl(loc($nameIII, $typeIII.ast), $nameIII.getText(), $typeIII.ast)); } )* )?
        //don't create AstTrees if pars is empty so it does not count one ID
        { AstTrees<AstParDecl> parsTree = pars.isEmpty() ? null : new AstTrees<AstParDecl>(pars); }
        RPAREN COLON typeI=type
        { AstExpr expr = null; }
        (ASSIGN exprI=expr { expr = $exprI.ast; })?
        {
        $ast = new AstFunDecl(
            loc($d_fun, expr == null ? $typeI.ast : expr),
            $nameI.getText(),
            parsTree,
            $typeI.ast,
            expr
        );
        }
    ;

//Types
type
    returns [AstType ast]
    : VOID
        {
        $ast = new AstAtomType(
            loc($VOID),
            AstAtomType.Type.VOID);
        }
    | CHAR
        {
        $ast = new AstAtomType(
            loc($CHAR),
            AstAtomType.Type.CHAR);
        }
    | INT
        {
        $ast = new AstAtomType(
            loc($INT),
            AstAtomType.Type.INT);
        }
    | BOOL
        {
        $ast = new AstAtomType(
            loc($BOOL),
            AstAtomType.Type.BOOL);
        }
    | name=ID
        {
        $ast = new AstNameType(
            loc($name),
            $name.getText());
        }
    | LBRACK expr RBRACK type
        {
        $ast = new AstArrType(
            loc($LBRACK, $type.ast),
            $type.ast,
            $expr.ast);
        }
    | POWER type
        {
        $ast = new AstPtrType(
            loc($POWER, $type.ast),
            $type.ast);
        }
    | LBRACE nameI=ID COLON type
        { List<AstCompDecl> comps = new LinkedList<AstCompDecl>();
          comps.add(new AstCompDecl(loc($nameI, $type.ast), $nameI.getText(), $type.ast)); }
        (COMMA nameII=ID COLON type
        { comps.add(new AstCompDecl(loc($nameII, $type.ast), $nameII.getText(), $type.ast)); }
        )* RBRACE
        {
        $ast = new AstRecType(
            loc($LBRACE, $RBRACE),
            new AstTrees<AstCompDecl>(comps)
        );
        }
    | LPAREN type RPAREN
        {
        $type.ast.relocate(loc($LPAREN, $RPAREN));
        $ast = $type.ast;
        }
    ;

//Expressions
expr
    returns [AstExpr ast]
    : NONE
    {
    $ast = new AstAtomExpr(
        loc($NONE),
        AstAtomExpr.Type.VOID,
        $NONE.getText());
    }
    | BOOLLIT
    {
    $ast = new AstAtomExpr(
        loc($BOOLLIT),
        AstAtomExpr.Type.BOOL,
        $BOOLLIT.getText());
    }
    | NUM
    {
    $ast = new AstAtomExpr(
        loc($NUM),
        AstAtomExpr.Type.INT,
        $NUM.getText());
    }
    | CHARLIT
    {
    $ast = new AstAtomExpr(
        loc($CHARLIT),
        AstAtomExpr.Type.CHAR,
        $CHARLIT.getText());
    }
    | STRINGLIT
    {
    $ast = new AstAtomExpr(
        loc($STRINGLIT),
        AstAtomExpr.Type.STRING,
        $STRINGLIT.getText());
    }
    | NIL
    {
    $ast = new AstAtomExpr(
        loc($NIL),
        AstAtomExpr.Type.POINTER,
        $NIL.getText());
    }
    //postfix
    | exprI=expr LBRACK exprII=expr RBRACK
    {
    $ast = new AstArrExpr(
        loc($exprI.ast, $RBRACK),
        $exprI.ast,
        $exprII.ast);
    }
    | exprPtr=expr POWER
    {
    $ast = new AstSfxExpr(
        loc($exprPtr.ast, $POWER),
        AstSfxExpr.Oper.PTR,
        $exprPtr.ast);
    }
    | exprId=expr DOT name=ID
    {
    $ast = new AstRecExpr(
        loc($exprId.ast, $name),
        $exprId.ast,
        new AstNameExpr(loc($name), $name.getText()));
    }
    //prefix
    | BANG expr
    {
    $ast = new AstPfxExpr(
        loc($BANG, $expr.ast),
        AstPfxExpr.Oper.NOT,
        $expr.ast);
    }
    | PLUS expr
    {
    $ast = new AstPfxExpr(
        loc($PLUS, $expr.ast),
        AstPfxExpr.Oper.ADD,
        $expr.ast);
    }
    | MINUS expr
    {
    $ast = new AstPfxExpr(
        loc($MINUS, $expr.ast),
        AstPfxExpr.Oper.SUB,
        $expr.ast);
    }
    | POWER expr
    {
    $ast = new AstPfxExpr(
        loc($POWER, $expr.ast),
        AstPfxExpr.Oper.PTR,
        $expr.ast);
    }
    | NEW expr
    {
    $ast = new AstPfxExpr(
        loc($NEW, $expr.ast),
        AstPfxExpr.Oper.NEW,
        $expr.ast);
    }
    | DEL expr
    {
    $ast = new AstPfxExpr(
        loc($DEL, $expr.ast),
        AstPfxExpr.Oper.DEL,
        $expr.ast);
    }
    //infix
    | exprI=expr MUL exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.MUL,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr DIV exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.DIV,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr MOD exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.MOD,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr PLUS exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.ADD,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr MINUS exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.SUB,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr EQUAL exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.EQU,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr NOTEQ exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.NEQ,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr LT exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.LTH,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr GT exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.GTH,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr LE exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.LEQ,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr GE exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.GEQ,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr BITAND exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.AND,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr BITOR exprII=expr
    {
    $ast = new AstBinExpr(
        loc($exprI.ast, $exprII.ast),
        AstBinExpr.Oper.OR,
        $exprI.ast,
        $exprII.ast);
    }
    | exprI=expr WHERE LBRACE
        { List<AstDecl> decls = new LinkedList<AstDecl>(); }
        ( decl
        { decls.add($decl.ast); }
        )+ RBRACE
        {
        $ast = new AstWhereExpr(
            loc($exprI.ast, $RBRACE),
            $exprI.ast,
            new AstTrees<AstDecl>(decls));
        }
    | ID
    {
    $ast = new AstNameExpr(
        loc($ID),
        $ID.getText()
    );
    }
    | name=ID LPAREN
        { List<AstExpr> args = new LinkedList<AstExpr>(); }
        (exprI=expr
        { args.add($exprI.ast); }
        (COMMA exprII=expr
        { args.add($exprII.ast); } )*)? RPAREN
        //if function is parameterless, use AstNameExpr, else use AstCallExpr
        {
        if (args.isEmpty())
            $ast = new AstNameExpr(loc($name, $RPAREN), $name.getText());
        else
            $ast = new AstCallExpr(
                loc($name, $RPAREN),
                $name.getText(),
                args.isEmpty() ? null : new AstTrees<AstExpr>(args));
        } //don't create AstTrees if args is empty else it add one ID
    | LBRACE
        { List<AstStmt> stmts = new LinkedList<AstStmt>(); }
        stmtI=stmt SECOL
        { stmts.add($stmtI.ast); }
        (stmtII=stmt SECOL
        { stmts.add($stmtII.ast); } )* RBRACE
        {
        $ast = new AstStmtExpr(
            loc($LBRACE, $RBRACE),
            new AstTrees<AstStmt>(stmts));
        }
    | LPAREN expr COLON type RPAREN
    {
    $ast = new AstCastExpr(
        loc($LPAREN, $RPAREN),
        $expr.ast,
        $type.ast);
    }
    | LPAREN expr RPAREN
    {
    $expr.ast.relocate(loc($LPAREN, $RPAREN));
    $ast = $expr.ast;
    }
    ;

//Statements
stmt
    returns [AstStmt ast]
    : expr
    {
    $ast = new AstExprStmt(
        loc($expr.ast),
        $expr.ast);
    }
    | dst=expr ASSIGN src=expr
    {
    $ast = new AstAssignStmt(
        loc($dst.ast, $src.ast),
        $dst.ast,
        $src.ast);
    }
    | IF expr THEN thenStmt=stmt ELSE elseStmt=stmt
    {
    $ast = new AstIfStmt(
        loc($IF, $elseStmt.ast),
        $expr.ast,
        $thenStmt.ast,
        $elseStmt.ast);
    }
    | WHILE expr DO stmt
    {
    $ast = new AstWhileStmt(
        loc($WHILE, $stmt.ast),
        $expr.ast,
        $stmt.ast);
    }
    ;
