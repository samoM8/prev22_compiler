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
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOOL=1, CHAR=2, DEL=3, DO=4, ELSE=5, FUN=6, IF=7, INT=8, NEW=9, THEN=10, 
		TYP=11, VAR=12, VOID=13, WHERE=14, WHILE=15, LPAREN=16, RPAREN=17, LBRACE=18, 
		RBRACE=19, LBRACK=20, RBRACK=21, DOT=22, COMMA=23, COLON=24, SECOL=25, 
		BITAND=26, BITOR=27, BANG=28, EQUAL=29, NOTEQ=30, LT=31, GT=32, LE=33, 
		GE=34, MUL=35, DIV=36, MOD=37, PLUS=38, MINUS=39, POWER=40, ASSIGN=41, 
		NONE=42, BOOLLIT=43, ZERO_PADDED_ERROR=44, NUM=45, CHARLIT=46, STRINGLIT=47, 
		NIL=48, ID=49, WS=50, TAB=51, COMMENT=52, UNTERMINATED_STRING=53, UNTERMINATED_CHAR=54, 
		UNESCAPED_CHARACTER=55, UNRECOGNIZED=56;
	public static final int
		RULE_source = 0, RULE_decl = 1, RULE_type = 2, RULE_expr = 3, RULE_stmt = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "type", "expr", "stmt"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'bool'", "'char'", "'del'", "'do'", "'else'", "'fun'", "'if'", 
			"'int'", "'new'", "'then'", "'typ'", "'var'", "'void'", "'where'", "'while'", 
			"'('", "')'", "'{'", "'}'", "'['", "']'", "'.'", "','", "':'", "';'", 
			"'&'", "'|'", "'!'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", 
			"'/'", "'%'", "'+'", "'-'", "'^'", "'='", "'none'", null, null, null, 
			null, null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BOOL", "CHAR", "DEL", "DO", "ELSE", "FUN", "IF", "INT", "NEW", 
			"THEN", "TYP", "VAR", "VOID", "WHERE", "WHILE", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "LBRACK", "RBRACK", "DOT", "COMMA", "COLON", "SECOL", "BITAND", 
			"BITOR", "BANG", "EQUAL", "NOTEQ", "LT", "GT", "LE", "GE", "MUL", "DIV", 
			"MOD", "PLUS", "MINUS", "POWER", "ASSIGN", "NONE", "BOOLLIT", "ZERO_PADDED_ERROR", 
			"NUM", "CHARLIT", "STRINGLIT", "NIL", "ID", "WS", "TAB", "COMMENT", "UNTERMINATED_STRING", 
			"UNTERMINATED_CHAR", "UNESCAPED_CHARACTER", "UNRECOGNIZED"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



		private Location loc(Token     tok) { return new Location((prev.data.sym.Token)tok); }
		private Location loc(Locatable loc) { return new Location(loc                 ); }
		private Location loc(Token     tok1, Token     tok2) { return new Location((prev.data.sym.Token)tok1, (prev.data.sym.Token)tok2); }
		private Location loc(Token     tok1, Locatable loc2) { return new Location((prev.data.sym.Token)tok1, loc2); }
		private Location loc(Locatable loc1, Token     tok2) { return new Location(loc1,                      (prev.data.sym.Token)tok2); }
		private Location loc(Locatable loc1, Locatable loc2) { return new Location(loc1,                      loc2); }


	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourceContext extends ParserRuleContext {
		public AstTrees<AstDecl> ast;
		public DeclContext decl;
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).enterSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).exitSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrevParserVisitor ) return ((PrevParserVisitor<? extends T>)visitor).visitSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 List<AstDecl> decls = new LinkedList<AstDecl>(); 
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(11);
				((SourceContext)_localctx).decl = decl();
				 decls.add(((SourceContext)_localctx).decl.ast); 
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
			 ((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(decls); 
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public AstDecl ast;
		public Token d_typ;
		public Token name;
		public TypeContext type;
		public Token d_var;
		public Token d_fun;
		public Token nameI;
		public Token nameII;
		public TypeContext typeII;
		public Token nameIII;
		public TypeContext typeIII;
		public TypeContext typeI;
		public ExprContext exprI;
		public TerminalNode ASSIGN() { return getToken(PrevParser.ASSIGN, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode TYP() { return getToken(PrevParser.TYP, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode VAR() { return getToken(PrevParser.VAR, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public TerminalNode FUN() { return getToken(PrevParser.FUN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrevParserVisitor ) return ((PrevParserVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				((DeclContext)_localctx).d_typ = match(TYP);
				setState(22);
				((DeclContext)_localctx).name = match(ID);
				setState(23);
				match(ASSIGN);
				setState(24);
				((DeclContext)_localctx).type = type();

				        ((DeclContext)_localctx).ast =  new AstTypeDecl(
				            loc(((DeclContext)_localctx).d_typ, ((DeclContext)_localctx).type.ast),
				            ((DeclContext)_localctx).name.getText(),
				            ((DeclContext)_localctx).type.ast);
				        
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((DeclContext)_localctx).d_var = match(VAR);
				setState(28);
				((DeclContext)_localctx).name = match(ID);
				setState(29);
				match(COLON);
				setState(30);
				((DeclContext)_localctx).type = type();

				        ((DeclContext)_localctx).ast =  new AstVarDecl(
				            loc(((DeclContext)_localctx).d_var, ((DeclContext)_localctx).type.ast),
				            ((DeclContext)_localctx).name.getText(),
				            ((DeclContext)_localctx).type.ast);
				        
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				((DeclContext)_localctx).d_fun = match(FUN);
				setState(34);
				((DeclContext)_localctx).nameI = match(ID);
				setState(35);
				match(LPAREN);
				 List<AstParDecl> pars = new LinkedList<AstParDecl>(); 
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(37);
					((DeclContext)_localctx).nameII = match(ID);
					setState(38);
					match(COLON);
					setState(39);
					((DeclContext)_localctx).typeII = type();
					 pars.add(new AstParDecl(loc(((DeclContext)_localctx).nameII, ((DeclContext)_localctx).typeII.ast), ((DeclContext)_localctx).nameII.getText(), ((DeclContext)_localctx).typeII.ast)); 
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(41);
						match(COMMA);
						setState(42);
						((DeclContext)_localctx).nameIII = match(ID);
						setState(43);
						match(COLON);
						setState(44);
						((DeclContext)_localctx).typeIII = type();
						 pars.add(new AstParDecl(loc(((DeclContext)_localctx).nameIII, ((DeclContext)_localctx).typeIII.ast), ((DeclContext)_localctx).nameIII.getText(), ((DeclContext)_localctx).typeIII.ast)); 
						}
						}
						setState(51);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				 AstTrees<AstParDecl> parsTree = pars.isEmpty() ? null : new AstTrees<AstParDecl>(pars); 
				setState(55);
				match(RPAREN);
				setState(56);
				match(COLON);
				setState(57);
				((DeclContext)_localctx).typeI = type();
				 AstExpr expr = null; 
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(59);
					match(ASSIGN);
					setState(60);
					((DeclContext)_localctx).exprI = expr(0);
					 expr = ((DeclContext)_localctx).exprI.ast; 
					}
				}


				        ((DeclContext)_localctx).ast =  new AstFunDecl(
				            loc(((DeclContext)_localctx).d_fun, expr == null ? ((DeclContext)_localctx).typeI.ast : expr),
				            ((DeclContext)_localctx).nameI.getText(),
				            parsTree,
				            ((DeclContext)_localctx).typeI.ast,
				            expr
				        );
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public AstType ast;
		public Token VOID;
		public Token CHAR;
		public Token INT;
		public Token BOOL;
		public Token name;
		public Token LBRACK;
		public ExprContext expr;
		public TypeContext type;
		public Token POWER;
		public Token LBRACE;
		public Token nameI;
		public Token nameII;
		public Token RBRACE;
		public Token LPAREN;
		public Token RPAREN;
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(PrevParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(PrevParser.BOOL, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public TerminalNode LBRACK() { return getToken(PrevParser.LBRACK, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(PrevParser.RBRACK, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode POWER() { return getToken(PrevParser.POWER, 0); }
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrevParserVisitor ) return ((PrevParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((TypeContext)_localctx).VOID = match(VOID);

				        ((TypeContext)_localctx).ast =  new AstAtomType(
				            loc(((TypeContext)_localctx).VOID),
				            AstAtomType.Type.VOID);
				        
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				((TypeContext)_localctx).CHAR = match(CHAR);

				        ((TypeContext)_localctx).ast =  new AstAtomType(
				            loc(((TypeContext)_localctx).CHAR),
				            AstAtomType.Type.CHAR);
				        
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				((TypeContext)_localctx).INT = match(INT);

				        ((TypeContext)_localctx).ast =  new AstAtomType(
				            loc(((TypeContext)_localctx).INT),
				            AstAtomType.Type.INT);
				        
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				((TypeContext)_localctx).BOOL = match(BOOL);

				        ((TypeContext)_localctx).ast =  new AstAtomType(
				            loc(((TypeContext)_localctx).BOOL),
				            AstAtomType.Type.BOOL);
				        
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				((TypeContext)_localctx).name = match(ID);

				        ((TypeContext)_localctx).ast =  new AstNameType(
				            loc(((TypeContext)_localctx).name),
				            ((TypeContext)_localctx).name.getText());
				        
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				((TypeContext)_localctx).LBRACK = match(LBRACK);
				setState(80);
				((TypeContext)_localctx).expr = expr(0);
				setState(81);
				match(RBRACK);
				setState(82);
				((TypeContext)_localctx).type = type();

				        ((TypeContext)_localctx).ast =  new AstArrType(
				            loc(((TypeContext)_localctx).LBRACK, ((TypeContext)_localctx).type.ast),
				            ((TypeContext)_localctx).type.ast,
				            ((TypeContext)_localctx).expr.ast);
				        
				}
				break;
			case POWER:
				enterOuterAlt(_localctx, 7);
				{
				setState(85);
				((TypeContext)_localctx).POWER = match(POWER);
				setState(86);
				((TypeContext)_localctx).type = type();

				        ((TypeContext)_localctx).ast =  new AstPtrType(
				            loc(((TypeContext)_localctx).POWER, ((TypeContext)_localctx).type.ast),
				            ((TypeContext)_localctx).type.ast);
				        
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 8);
				{
				setState(89);
				((TypeContext)_localctx).LBRACE = match(LBRACE);
				setState(90);
				((TypeContext)_localctx).nameI = match(ID);
				setState(91);
				match(COLON);
				setState(92);
				((TypeContext)_localctx).type = type();
				 List<AstCompDecl> comps = new LinkedList<AstCompDecl>();
				          comps.add(new AstCompDecl(loc(((TypeContext)_localctx).nameI, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).nameI.getText(), ((TypeContext)_localctx).type.ast)); 
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(94);
					match(COMMA);
					setState(95);
					((TypeContext)_localctx).nameII = match(ID);
					setState(96);
					match(COLON);
					setState(97);
					((TypeContext)_localctx).type = type();
					 comps.add(new AstCompDecl(loc(((TypeContext)_localctx).nameII, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).nameII.getText(), ((TypeContext)_localctx).type.ast)); 
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(105);
				((TypeContext)_localctx).RBRACE = match(RBRACE);

				        ((TypeContext)_localctx).ast =  new AstRecType(
				            loc(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE),
				            new AstTrees<AstCompDecl>(comps)
				        );
				        
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 9);
				{
				setState(108);
				((TypeContext)_localctx).LPAREN = match(LPAREN);
				setState(109);
				((TypeContext)_localctx).type = type();
				setState(110);
				((TypeContext)_localctx).RPAREN = match(RPAREN);

				        ((TypeContext)_localctx).type.ast.relocate(loc(((TypeContext)_localctx).LPAREN, ((TypeContext)_localctx).RPAREN));
				        ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).type.ast;
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AstExpr ast;
		public ExprContext exprI;
		public ExprContext exprPtr;
		public ExprContext exprId;
		public Token NONE;
		public Token BOOLLIT;
		public Token NUM;
		public Token CHARLIT;
		public Token STRINGLIT;
		public Token NIL;
		public Token BANG;
		public ExprContext expr;
		public Token PLUS;
		public Token MINUS;
		public Token POWER;
		public Token NEW;
		public Token DEL;
		public Token ID;
		public Token name;
		public Token LPAREN;
		public ExprContext exprII;
		public Token RPAREN;
		public Token LBRACE;
		public StmtContext stmtI;
		public StmtContext stmtII;
		public Token RBRACE;
		public TypeContext type;
		public Token RBRACK;
		public DeclContext decl;
		public TerminalNode NONE() { return getToken(PrevParser.NONE, 0); }
		public TerminalNode BOOLLIT() { return getToken(PrevParser.BOOLLIT, 0); }
		public TerminalNode NUM() { return getToken(PrevParser.NUM, 0); }
		public TerminalNode CHARLIT() { return getToken(PrevParser.CHARLIT, 0); }
		public TerminalNode STRINGLIT() { return getToken(PrevParser.STRINGLIT, 0); }
		public TerminalNode NIL() { return getToken(PrevParser.NIL, 0); }
		public TerminalNode BANG() { return getToken(PrevParser.BANG, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(PrevParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PrevParser.MINUS, 0); }
		public TerminalNode POWER() { return getToken(PrevParser.POWER, 0); }
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode ID() { return getToken(PrevParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> SECOL() { return getTokens(PrevParser.SECOL); }
		public TerminalNode SECOL(int i) {
			return getToken(PrevParser.SECOL, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode MUL() { return getToken(PrevParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public TerminalNode EQUAL() { return getToken(PrevParser.EQUAL, 0); }
		public TerminalNode NOTEQ() { return getToken(PrevParser.NOTEQ, 0); }
		public TerminalNode LT() { return getToken(PrevParser.LT, 0); }
		public TerminalNode GT() { return getToken(PrevParser.GT, 0); }
		public TerminalNode LE() { return getToken(PrevParser.LE, 0); }
		public TerminalNode GE() { return getToken(PrevParser.GE, 0); }
		public TerminalNode BITAND() { return getToken(PrevParser.BITAND, 0); }
		public TerminalNode BITOR() { return getToken(PrevParser.BITOR, 0); }
		public TerminalNode LBRACK() { return getToken(PrevParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PrevParser.RBRACK, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public TerminalNode WHERE() { return getToken(PrevParser.WHERE, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrevParserVisitor ) return ((PrevParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(116);
				((ExprContext)_localctx).NONE = match(NONE);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).NONE),
				        AstAtomExpr.Type.VOID,
				        ((ExprContext)_localctx).NONE.getText());
				    
				}
				break;
			case 2:
				{
				setState(118);
				((ExprContext)_localctx).BOOLLIT = match(BOOLLIT);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).BOOLLIT),
				        AstAtomExpr.Type.BOOL,
				        ((ExprContext)_localctx).BOOLLIT.getText());
				    
				}
				break;
			case 3:
				{
				setState(120);
				((ExprContext)_localctx).NUM = match(NUM);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).NUM),
				        AstAtomExpr.Type.INT,
				        ((ExprContext)_localctx).NUM.getText());
				    
				}
				break;
			case 4:
				{
				setState(122);
				((ExprContext)_localctx).CHARLIT = match(CHARLIT);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).CHARLIT),
				        AstAtomExpr.Type.CHAR,
				        ((ExprContext)_localctx).CHARLIT.getText());
				    
				}
				break;
			case 5:
				{
				setState(124);
				((ExprContext)_localctx).STRINGLIT = match(STRINGLIT);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).STRINGLIT),
				        AstAtomExpr.Type.STRING,
				        ((ExprContext)_localctx).STRINGLIT.getText());
				    
				}
				break;
			case 6:
				{
				setState(126);
				((ExprContext)_localctx).NIL = match(NIL);

				    ((ExprContext)_localctx).ast =  new AstAtomExpr(
				        loc(((ExprContext)_localctx).NIL),
				        AstAtomExpr.Type.POINTER,
				        ((ExprContext)_localctx).NIL.getText());
				    
				}
				break;
			case 7:
				{
				setState(128);
				((ExprContext)_localctx).BANG = match(BANG);
				setState(129);
				((ExprContext)_localctx).expr = expr(25);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).BANG, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.NOT,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 8:
				{
				setState(132);
				((ExprContext)_localctx).PLUS = match(PLUS);
				setState(133);
				((ExprContext)_localctx).expr = expr(24);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).PLUS, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.ADD,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 9:
				{
				setState(136);
				((ExprContext)_localctx).MINUS = match(MINUS);
				setState(137);
				((ExprContext)_localctx).expr = expr(23);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).MINUS, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.SUB,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 10:
				{
				setState(140);
				((ExprContext)_localctx).POWER = match(POWER);
				setState(141);
				((ExprContext)_localctx).expr = expr(22);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).POWER, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.PTR,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 11:
				{
				setState(144);
				((ExprContext)_localctx).NEW = match(NEW);
				setState(145);
				((ExprContext)_localctx).expr = expr(21);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).NEW, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.NEW,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 12:
				{
				setState(148);
				((ExprContext)_localctx).DEL = match(DEL);
				setState(149);
				((ExprContext)_localctx).expr = expr(20);

				    ((ExprContext)_localctx).ast =  new AstPfxExpr(
				        loc(((ExprContext)_localctx).DEL, ((ExprContext)_localctx).expr.ast),
				        AstPfxExpr.Oper.DEL,
				        ((ExprContext)_localctx).expr.ast);
				    
				}
				break;
			case 13:
				{
				setState(152);
				((ExprContext)_localctx).ID = match(ID);

				    ((ExprContext)_localctx).ast =  new AstNameExpr(
				        loc(((ExprContext)_localctx).ID),
				        ((ExprContext)_localctx).ID.getText()
				    );
				    
				}
				break;
			case 14:
				{
				setState(154);
				((ExprContext)_localctx).name = match(ID);
				setState(155);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				 List<AstExpr> args = new LinkedList<AstExpr>(); 
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEL) | (1L << NEW) | (1L << LPAREN) | (1L << LBRACE) | (1L << BANG) | (1L << PLUS) | (1L << MINUS) | (1L << POWER) | (1L << NONE) | (1L << BOOLLIT) | (1L << NUM) | (1L << CHARLIT) | (1L << STRINGLIT) | (1L << NIL) | (1L << ID))) != 0)) {
					{
					setState(157);
					((ExprContext)_localctx).exprI = ((ExprContext)_localctx).expr = expr(0);
					 args.add(((ExprContext)_localctx).exprI.ast); 
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(159);
						match(COMMA);
						setState(160);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(0);
						 args.add(((ExprContext)_localctx).exprII.ast); 
						}
						}
						setState(167);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(170);
				((ExprContext)_localctx).RPAREN = match(RPAREN);

				        if (args.isEmpty())
				            ((ExprContext)_localctx).ast =  new AstNameExpr(loc(((ExprContext)_localctx).name, ((ExprContext)_localctx).RPAREN), ((ExprContext)_localctx).name.getText());
				        else
				            ((ExprContext)_localctx).ast =  new AstCallExpr(
				                loc(((ExprContext)_localctx).name, ((ExprContext)_localctx).RPAREN),
				                ((ExprContext)_localctx).name.getText(),
				                args.isEmpty() ? null : new AstTrees<AstExpr>(args));
				        
				}
				break;
			case 15:
				{
				setState(172);
				((ExprContext)_localctx).LBRACE = match(LBRACE);
				 List<AstStmt> stmts = new LinkedList<AstStmt>(); 
				setState(174);
				((ExprContext)_localctx).stmtI = stmt();
				setState(175);
				match(SECOL);
				 stmts.add(((ExprContext)_localctx).stmtI.ast); 
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << LPAREN) | (1L << LBRACE) | (1L << BANG) | (1L << PLUS) | (1L << MINUS) | (1L << POWER) | (1L << NONE) | (1L << BOOLLIT) | (1L << NUM) | (1L << CHARLIT) | (1L << STRINGLIT) | (1L << NIL) | (1L << ID))) != 0)) {
					{
					{
					setState(177);
					((ExprContext)_localctx).stmtII = stmt();
					setState(178);
					match(SECOL);
					 stmts.add(((ExprContext)_localctx).stmtII.ast); 
					}
					}
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(186);
				((ExprContext)_localctx).RBRACE = match(RBRACE);

				        ((ExprContext)_localctx).ast =  new AstStmtExpr(
				            loc(((ExprContext)_localctx).LBRACE, ((ExprContext)_localctx).RBRACE),
				            new AstTrees<AstStmt>(stmts));
				        
				}
				break;
			case 16:
				{
				setState(189);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(190);
				((ExprContext)_localctx).expr = expr(0);
				setState(191);
				match(COLON);
				setState(192);
				((ExprContext)_localctx).type = type();
				setState(193);
				((ExprContext)_localctx).RPAREN = match(RPAREN);

				    ((ExprContext)_localctx).ast =  new AstCastExpr(
				        loc(((ExprContext)_localctx).LPAREN, ((ExprContext)_localctx).RPAREN),
				        ((ExprContext)_localctx).expr.ast,
				        ((ExprContext)_localctx).type.ast);
				    
				}
				break;
			case 17:
				{
				setState(196);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(197);
				((ExprContext)_localctx).expr = expr(0);
				setState(198);
				((ExprContext)_localctx).RPAREN = match(RPAREN);

				    ((ExprContext)_localctx).expr.ast.relocate(loc(((ExprContext)_localctx).LPAREN, ((ExprContext)_localctx).RPAREN));
				    ((ExprContext)_localctx).ast =  ((ExprContext)_localctx).expr.ast;
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(295);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(203);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(204);
						match(MUL);
						setState(205);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(20);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.MUL,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(208);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(209);
						match(DIV);
						setState(210);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(19);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.DIV,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(214);
						match(MOD);
						setState(215);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(18);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.MOD,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(219);
						((ExprContext)_localctx).PLUS = match(PLUS);
						setState(220);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(17);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.ADD,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(223);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(224);
						((ExprContext)_localctx).MINUS = match(MINUS);
						setState(225);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(16);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.SUB,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(229);
						match(EQUAL);
						setState(230);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(15);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.EQU,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(233);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(234);
						match(NOTEQ);
						setState(235);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(14);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.NEQ,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(238);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(239);
						match(LT);
						setState(240);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(13);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.LTH,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(244);
						match(GT);
						setState(245);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(12);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.GTH,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(248);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(249);
						match(LE);
						setState(250);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(11);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.LEQ,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(253);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(254);
						match(GE);
						setState(255);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(10);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.GEQ,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(259);
						match(BITAND);
						setState(260);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(9);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.AND,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(263);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(264);
						match(BITOR);
						setState(265);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(8);

						              ((ExprContext)_localctx).ast =  new AstBinExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).exprII.ast),
						                  AstBinExpr.Oper.OR,
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(268);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(269);
						match(LBRACK);
						setState(270);
						((ExprContext)_localctx).exprII = ((ExprContext)_localctx).expr = expr(0);
						setState(271);
						((ExprContext)_localctx).RBRACK = match(RBRACK);

						              ((ExprContext)_localctx).ast =  new AstArrExpr(
						                  loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).RBRACK),
						                  ((ExprContext)_localctx).exprI.ast,
						                  ((ExprContext)_localctx).exprII.ast);
						              
						}
						break;
					case 15:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprPtr = _prevctx;
						_localctx.exprPtr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(274);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(275);
						((ExprContext)_localctx).POWER = match(POWER);

						              ((ExprContext)_localctx).ast =  new AstSfxExpr(
						                  loc(((ExprContext)_localctx).exprPtr.ast, ((ExprContext)_localctx).POWER),
						                  AstSfxExpr.Oper.PTR,
						                  ((ExprContext)_localctx).exprPtr.ast);
						              
						}
						break;
					case 16:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprId = _prevctx;
						_localctx.exprId = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(277);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(278);
						match(DOT);
						setState(279);
						((ExprContext)_localctx).name = match(ID);

						              ((ExprContext)_localctx).ast =  new AstRecExpr(
						                  loc(((ExprContext)_localctx).exprId.ast, ((ExprContext)_localctx).name),
						                  ((ExprContext)_localctx).exprId.ast,
						                  new AstNameExpr(loc(((ExprContext)_localctx).name), ((ExprContext)_localctx).name.getText()));
						              
						}
						break;
					case 17:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.exprI = _prevctx;
						_localctx.exprI = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(282);
						match(WHERE);
						setState(283);
						((ExprContext)_localctx).LBRACE = match(LBRACE);
						 List<AstDecl> decls = new LinkedList<AstDecl>(); 
						setState(288); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(285);
							((ExprContext)_localctx).decl = decl();
							 decls.add(((ExprContext)_localctx).decl.ast); 
							}
							}
							setState(290); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
						setState(292);
						((ExprContext)_localctx).RBRACE = match(RBRACE);

						                  ((ExprContext)_localctx).ast =  new AstWhereExpr(
						                      loc(((ExprContext)_localctx).exprI.ast, ((ExprContext)_localctx).RBRACE),
						                      ((ExprContext)_localctx).exprI.ast,
						                      new AstTrees<AstDecl>(decls));
						                  
						}
						break;
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public AstStmt ast;
		public ExprContext expr;
		public ExprContext dst;
		public ExprContext src;
		public Token IF;
		public StmtContext thenStmt;
		public StmtContext elseStmt;
		public Token WHILE;
		public StmtContext stmt;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(PrevParser.ASSIGN, 0); }
		public TerminalNode IF() { return getToken(PrevParser.IF, 0); }
		public TerminalNode THEN() { return getToken(PrevParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(PrevParser.ELSE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode WHILE() { return getToken(PrevParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(PrevParser.DO, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrevParserListener ) ((PrevParserListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrevParserVisitor ) return ((PrevParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stmt);
		try {
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				((StmtContext)_localctx).expr = expr(0);

				    ((StmtContext)_localctx).ast =  new AstExprStmt(
				        loc(((StmtContext)_localctx).expr.ast),
				        ((StmtContext)_localctx).expr.ast);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				((StmtContext)_localctx).dst = expr(0);
				setState(304);
				match(ASSIGN);
				setState(305);
				((StmtContext)_localctx).src = expr(0);

				    ((StmtContext)_localctx).ast =  new AstAssignStmt(
				        loc(((StmtContext)_localctx).dst.ast, ((StmtContext)_localctx).src.ast),
				        ((StmtContext)_localctx).dst.ast,
				        ((StmtContext)_localctx).src.ast);
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				((StmtContext)_localctx).IF = match(IF);
				setState(309);
				((StmtContext)_localctx).expr = expr(0);
				setState(310);
				match(THEN);
				setState(311);
				((StmtContext)_localctx).thenStmt = stmt();
				setState(312);
				match(ELSE);
				setState(313);
				((StmtContext)_localctx).elseStmt = stmt();

				    ((StmtContext)_localctx).ast =  new AstIfStmt(
				        loc(((StmtContext)_localctx).IF, ((StmtContext)_localctx).elseStmt.ast),
				        ((StmtContext)_localctx).expr.ast,
				        ((StmtContext)_localctx).thenStmt.ast,
				        ((StmtContext)_localctx).elseStmt.ast);
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(316);
				((StmtContext)_localctx).WHILE = match(WHILE);
				setState(317);
				((StmtContext)_localctx).expr = expr(0);
				setState(318);
				match(DO);
				setState(319);
				((StmtContext)_localctx).stmt = stmt();

				    ((StmtContext)_localctx).ast =  new AstWhileStmt(
				        loc(((StmtContext)_localctx).WHILE, ((StmtContext)_localctx).stmt.ast),
				        ((StmtContext)_localctx).expr.ast,
				        ((StmtContext)_localctx).stmt.ast);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 10);
		case 10:
			return precpred(_ctx, 9);
		case 11:
			return precpred(_ctx, 8);
		case 12:
			return precpred(_ctx, 7);
		case 13:
			return precpred(_ctx, 28);
		case 14:
			return precpred(_ctx, 27);
		case 15:
			return precpred(_ctx, 26);
		case 16:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0147\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\6\2\21\n\2\r\2\16\2\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16\3\65"+
		"\13\3\5\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3B\n\3\3\3\3\3"+
		"\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4g"+
		"\n\4\f\4\16\4j\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4t\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a6\n\5\f\5\16\5\u00a9"+
		"\13\5\5\5\u00ab\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b8"+
		"\n\5\f\5\16\5\u00bb\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5\u00cc\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\6\5\u0123\n\5\r\5\16\5\u0124\3\5\3\5\3\5\7\5\u012a"+
		"\n\5\f\5\16\5\u012d\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0145\n\6\3\6\2\3\b\7\2"+
		"\4\6\b\n\2\2\2\u0178\2\f\3\2\2\2\4E\3\2\2\2\6s\3\2\2\2\b\u00cb\3\2\2\2"+
		"\n\u0144\3\2\2\2\f\20\b\2\1\2\r\16\5\4\3\2\16\17\b\2\1\2\17\21\3\2\2\2"+
		"\20\r\3\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\24\3\2\2\2"+
		"\24\25\b\2\1\2\25\26\7\2\2\3\26\3\3\2\2\2\27\30\7\r\2\2\30\31\7\63\2\2"+
		"\31\32\7+\2\2\32\33\5\6\4\2\33\34\b\3\1\2\34F\3\2\2\2\35\36\7\16\2\2\36"+
		"\37\7\63\2\2\37 \7\32\2\2 !\5\6\4\2!\"\b\3\1\2\"F\3\2\2\2#$\7\b\2\2$%"+
		"\7\63\2\2%&\7\22\2\2&\66\b\3\1\2\'(\7\63\2\2()\7\32\2\2)*\5\6\4\2*\63"+
		"\b\3\1\2+,\7\31\2\2,-\7\63\2\2-.\7\32\2\2./\5\6\4\2/\60\b\3\1\2\60\62"+
		"\3\2\2\2\61+\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3"+
		"\2\2\2\65\63\3\2\2\2\66\'\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\b\3\1\2"+
		"9:\7\23\2\2:;\7\32\2\2;<\5\6\4\2<A\b\3\1\2=>\7+\2\2>?\5\b\5\2?@\b\3\1"+
		"\2@B\3\2\2\2A=\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\b\3\1\2DF\3\2\2\2E\27\3\2"+
		"\2\2E\35\3\2\2\2E#\3\2\2\2F\5\3\2\2\2GH\7\17\2\2Ht\b\4\1\2IJ\7\4\2\2J"+
		"t\b\4\1\2KL\7\n\2\2Lt\b\4\1\2MN\7\3\2\2Nt\b\4\1\2OP\7\63\2\2Pt\b\4\1\2"+
		"QR\7\26\2\2RS\5\b\5\2ST\7\27\2\2TU\5\6\4\2UV\b\4\1\2Vt\3\2\2\2WX\7*\2"+
		"\2XY\5\6\4\2YZ\b\4\1\2Zt\3\2\2\2[\\\7\24\2\2\\]\7\63\2\2]^\7\32\2\2^_"+
		"\5\6\4\2_h\b\4\1\2`a\7\31\2\2ab\7\63\2\2bc\7\32\2\2cd\5\6\4\2de\b\4\1"+
		"\2eg\3\2\2\2f`\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2"+
		"\2kl\7\25\2\2lm\b\4\1\2mt\3\2\2\2no\7\22\2\2op\5\6\4\2pq\7\23\2\2qr\b"+
		"\4\1\2rt\3\2\2\2sG\3\2\2\2sI\3\2\2\2sK\3\2\2\2sM\3\2\2\2sO\3\2\2\2sQ\3"+
		"\2\2\2sW\3\2\2\2s[\3\2\2\2sn\3\2\2\2t\7\3\2\2\2uv\b\5\1\2vw\7,\2\2w\u00cc"+
		"\b\5\1\2xy\7-\2\2y\u00cc\b\5\1\2z{\7/\2\2{\u00cc\b\5\1\2|}\7\60\2\2}\u00cc"+
		"\b\5\1\2~\177\7\61\2\2\177\u00cc\b\5\1\2\u0080\u0081\7\62\2\2\u0081\u00cc"+
		"\b\5\1\2\u0082\u0083\7\36\2\2\u0083\u0084\5\b\5\33\u0084\u0085\b\5\1\2"+
		"\u0085\u00cc\3\2\2\2\u0086\u0087\7(\2\2\u0087\u0088\5\b\5\32\u0088\u0089"+
		"\b\5\1\2\u0089\u00cc\3\2\2\2\u008a\u008b\7)\2\2\u008b\u008c\5\b\5\31\u008c"+
		"\u008d\b\5\1\2\u008d\u00cc\3\2\2\2\u008e\u008f\7*\2\2\u008f\u0090\5\b"+
		"\5\30\u0090\u0091\b\5\1\2\u0091\u00cc\3\2\2\2\u0092\u0093\7\13\2\2\u0093"+
		"\u0094\5\b\5\27\u0094\u0095\b\5\1\2\u0095\u00cc\3\2\2\2\u0096\u0097\7"+
		"\5\2\2\u0097\u0098\5\b\5\26\u0098\u0099\b\5\1\2\u0099\u00cc\3\2\2\2\u009a"+
		"\u009b\7\63\2\2\u009b\u00cc\b\5\1\2\u009c\u009d\7\63\2\2\u009d\u009e\7"+
		"\22\2\2\u009e\u00aa\b\5\1\2\u009f\u00a0\5\b\5\2\u00a0\u00a7\b\5\1\2\u00a1"+
		"\u00a2\7\31\2\2\u00a2\u00a3\5\b\5\2\u00a3\u00a4\b\5\1\2\u00a4\u00a6\3"+
		"\2\2\2\u00a5\u00a1\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u009f\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\7\23\2\2\u00ad"+
		"\u00cc\b\5\1\2\u00ae\u00af\7\24\2\2\u00af\u00b0\b\5\1\2\u00b0\u00b1\5"+
		"\n\6\2\u00b1\u00b2\7\33\2\2\u00b2\u00b9\b\5\1\2\u00b3\u00b4\5\n\6\2\u00b4"+
		"\u00b5\7\33\2\2\u00b5\u00b6\b\5\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3"+
		"\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\7\25\2\2\u00bd\u00be\b"+
		"\5\1\2\u00be\u00cc\3\2\2\2\u00bf\u00c0\7\22\2\2\u00c0\u00c1\5\b\5\2\u00c1"+
		"\u00c2\7\32\2\2\u00c2\u00c3\5\6\4\2\u00c3\u00c4\7\23\2\2\u00c4\u00c5\b"+
		"\5\1\2\u00c5\u00cc\3\2\2\2\u00c6\u00c7\7\22\2\2\u00c7\u00c8\5\b\5\2\u00c8"+
		"\u00c9\7\23\2\2\u00c9\u00ca\b\5\1\2\u00ca\u00cc\3\2\2\2\u00cbu\3\2\2\2"+
		"\u00cbx\3\2\2\2\u00cbz\3\2\2\2\u00cb|\3\2\2\2\u00cb~\3\2\2\2\u00cb\u0080"+
		"\3\2\2\2\u00cb\u0082\3\2\2\2\u00cb\u0086\3\2\2\2\u00cb\u008a\3\2\2\2\u00cb"+
		"\u008e\3\2\2\2\u00cb\u0092\3\2\2\2\u00cb\u0096\3\2\2\2\u00cb\u009a\3\2"+
		"\2\2\u00cb\u009c\3\2\2\2\u00cb\u00ae\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb"+
		"\u00c6\3\2\2\2\u00cc\u012b\3\2\2\2\u00cd\u00ce\f\25\2\2\u00ce\u00cf\7"+
		"%\2\2\u00cf\u00d0\5\b\5\26\u00d0\u00d1\b\5\1\2\u00d1\u012a\3\2\2\2\u00d2"+
		"\u00d3\f\24\2\2\u00d3\u00d4\7&\2\2\u00d4\u00d5\5\b\5\25\u00d5\u00d6\b"+
		"\5\1\2\u00d6\u012a\3\2\2\2\u00d7\u00d8\f\23\2\2\u00d8\u00d9\7\'\2\2\u00d9"+
		"\u00da\5\b\5\24\u00da\u00db\b\5\1\2\u00db\u012a\3\2\2\2\u00dc\u00dd\f"+
		"\22\2\2\u00dd\u00de\7(\2\2\u00de\u00df\5\b\5\23\u00df\u00e0\b\5\1\2\u00e0"+
		"\u012a\3\2\2\2\u00e1\u00e2\f\21\2\2\u00e2\u00e3\7)\2\2\u00e3\u00e4\5\b"+
		"\5\22\u00e4\u00e5\b\5\1\2\u00e5\u012a\3\2\2\2\u00e6\u00e7\f\20\2\2\u00e7"+
		"\u00e8\7\37\2\2\u00e8\u00e9\5\b\5\21\u00e9\u00ea\b\5\1\2\u00ea\u012a\3"+
		"\2\2\2\u00eb\u00ec\f\17\2\2\u00ec\u00ed\7 \2\2\u00ed\u00ee\5\b\5\20\u00ee"+
		"\u00ef\b\5\1\2\u00ef\u012a\3\2\2\2\u00f0\u00f1\f\16\2\2\u00f1\u00f2\7"+
		"!\2\2\u00f2\u00f3\5\b\5\17\u00f3\u00f4\b\5\1\2\u00f4\u012a\3\2\2\2\u00f5"+
		"\u00f6\f\r\2\2\u00f6\u00f7\7\"\2\2\u00f7\u00f8\5\b\5\16\u00f8\u00f9\b"+
		"\5\1\2\u00f9\u012a\3\2\2\2\u00fa\u00fb\f\f\2\2\u00fb\u00fc\7#\2\2\u00fc"+
		"\u00fd\5\b\5\r\u00fd\u00fe\b\5\1\2\u00fe\u012a\3\2\2\2\u00ff\u0100\f\13"+
		"\2\2\u0100\u0101\7$\2\2\u0101\u0102\5\b\5\f\u0102\u0103\b\5\1\2\u0103"+
		"\u012a\3\2\2\2\u0104\u0105\f\n\2\2\u0105\u0106\7\34\2\2\u0106\u0107\5"+
		"\b\5\13\u0107\u0108\b\5\1\2\u0108\u012a\3\2\2\2\u0109\u010a\f\t\2\2\u010a"+
		"\u010b\7\35\2\2\u010b\u010c\5\b\5\n\u010c\u010d\b\5\1\2\u010d\u012a\3"+
		"\2\2\2\u010e\u010f\f\36\2\2\u010f\u0110\7\26\2\2\u0110\u0111\5\b\5\2\u0111"+
		"\u0112\7\27\2\2\u0112\u0113\b\5\1\2\u0113\u012a\3\2\2\2\u0114\u0115\f"+
		"\35\2\2\u0115\u0116\7*\2\2\u0116\u012a\b\5\1\2\u0117\u0118\f\34\2\2\u0118"+
		"\u0119\7\30\2\2\u0119\u011a\7\63\2\2\u011a\u012a\b\5\1\2\u011b\u011c\f"+
		"\b\2\2\u011c\u011d\7\20\2\2\u011d\u011e\7\24\2\2\u011e\u0122\b\5\1\2\u011f"+
		"\u0120\5\4\3\2\u0120\u0121\b\5\1\2\u0121\u0123\3\2\2\2\u0122\u011f\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\u0127\7\25\2\2\u0127\u0128\b\5\1\2\u0128\u012a\3"+
		"\2\2\2\u0129\u00cd\3\2\2\2\u0129\u00d2\3\2\2\2\u0129\u00d7\3\2\2\2\u0129"+
		"\u00dc\3\2\2\2\u0129\u00e1\3\2\2\2\u0129\u00e6\3\2\2\2\u0129\u00eb\3\2"+
		"\2\2\u0129\u00f0\3\2\2\2\u0129\u00f5\3\2\2\2\u0129\u00fa\3\2\2\2\u0129"+
		"\u00ff\3\2\2\2\u0129\u0104\3\2\2\2\u0129\u0109\3\2\2\2\u0129\u010e\3\2"+
		"\2\2\u0129\u0114\3\2\2\2\u0129\u0117\3\2\2\2\u0129\u011b\3\2\2\2\u012a"+
		"\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\t\3\2\2\2"+
		"\u012d\u012b\3\2\2\2\u012e\u012f\5\b\5\2\u012f\u0130\b\6\1\2\u0130\u0145"+
		"\3\2\2\2\u0131\u0132\5\b\5\2\u0132\u0133\7+\2\2\u0133\u0134\5\b\5\2\u0134"+
		"\u0135\b\6\1\2\u0135\u0145\3\2\2\2\u0136\u0137\7\t\2\2\u0137\u0138\5\b"+
		"\5\2\u0138\u0139\7\f\2\2\u0139\u013a\5\n\6\2\u013a\u013b\7\7\2\2\u013b"+
		"\u013c\5\n\6\2\u013c\u013d\b\6\1\2\u013d\u0145\3\2\2\2\u013e\u013f\7\21"+
		"\2\2\u013f\u0140\5\b\5\2\u0140\u0141\7\6\2\2\u0141\u0142\5\n\6\2\u0142"+
		"\u0143\b\6\1\2\u0143\u0145\3\2\2\2\u0144\u012e\3\2\2\2\u0144\u0131\3\2"+
		"\2\2\u0144\u0136\3\2\2\2\u0144\u013e\3\2\2\2\u0145\13\3\2\2\2\21\22\63"+
		"\66AEhs\u00a7\u00aa\u00b9\u00cb\u0124\u0129\u012b\u0144";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}