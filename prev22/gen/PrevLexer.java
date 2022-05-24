// Generated from /home/samom8/Desktop/faks/3.Letnik/2.semester/Prevajalniki/prev22/src/prev/phase/lexan/PrevLexer.g4 by ANTLR 4.9.2

	package prev.phase.lexan;
	import prev.common.report.*;
	import prev.data.sym.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BOOL", "CHAR", "DEL", "DO", "ELSE", "FUN", "IF", "INT", "NEW", "THEN", 
			"TYP", "VAR", "VOID", "WHERE", "WHILE", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "LBRACK", "RBRACK", "DOT", "COMMA", "COLON", "SECOL", "BITAND", 
			"BITOR", "BANG", "EQUAL", "NOTEQ", "LT", "GT", "LE", "GE", "MUL", "DIV", 
			"MOD", "PLUS", "MINUS", "POWER", "ASSIGN", "NONE", "BOOLLIT", "ZERO_PADDED_ERROR", 
			"NUM", "CHARLIT", "STRINGLIT", "NIL", "ID", "WS", "TAB", "COMMENT", "UNTERMINATED_STRING", 
			"UNTERMINATED_CHAR", "UNESCAPED_CHARACTER", "UNRECOGNIZED"
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
		public Token nextToken() {
			return (Token) super.nextToken();
		}


	public PrevLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrevLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 43:
			ZERO_PADDED_ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			TAB_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			UNTERMINATED_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			UNTERMINATED_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		case 54:
			UNESCAPED_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		case 55:
			UNRECOGNIZED_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ZERO_PADDED_ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Zero padded number/leading zeros"); 
			break;
		}
	}
	private void TAB_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			        int pos = this.getCharPositionInLine();
			        int mod = (pos-1) % 8;
			        this.setCharPositionInLine(pos-1 + 8 - mod);
			        
			break;
		}
	}
	private void UNTERMINATED_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unterminated string"); 
			break;
		}
	}
	private void UNTERMINATED_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unterminated character"); 
			break;
		}
	}
	private void UNESCAPED_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unescaped character"); 
			break;
		}
	}
	private void UNRECOGNIZED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unrecognized: " + this.getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0169\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3"+
		"(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u00fd\n"+
		",\3-\6-\u0100\n-\r-\16-\u0101\3-\3-\7-\u0106\n-\f-\16-\u0109\13-\3-\3"+
		"-\3.\3.\3.\7.\u0110\n.\f.\16.\u0113\13.\5.\u0115\n.\3/\3/\3/\3/\5/\u011b"+
		"\n/\3/\3/\3\60\3\60\3\60\3\60\7\60\u0123\n\60\f\60\16\60\u0126\13\60\3"+
		"\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\7\62\u0130\n\62\f\62\16\62\u0133"+
		"\13\62\3\63\6\63\u0136\n\63\r\63\16\63\u0137\3\63\3\63\3\64\3\64\3\64"+
		"\3\64\3\64\3\65\3\65\7\65\u0143\n\65\f\65\16\65\u0146\13\65\3\65\3\65"+
		"\3\66\3\66\3\66\3\66\7\66\u014e\n\66\f\66\16\66\u0151\13\66\3\66\3\66"+
		"\3\67\3\67\3\67\3\67\5\67\u0159\n\67\3\67\3\67\38\38\38\38\38\38\58\u0163"+
		"\n8\38\38\39\39\39\2\2:\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62"+
		"c\63e\64g\65i\66k\67m8o9q:\3\2\13\3\2\63;\3\2\62;\4\2\"(*\u0080\4\2\""+
		"#%\u0080\5\2C\\aac|\6\2\62;C\\aac|\5\2\f\f\17\17\"\"\3\2\13\13\4\2\f\f"+
		"\17\17\2\u0177\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5x\3\2\2\2\7}\3\2\2"+
		"\2\t\u0081\3\2\2\2\13\u0084\3\2\2\2\r\u0089\3\2\2\2\17\u008d\3\2\2\2\21"+
		"\u0090\3\2\2\2\23\u0094\3\2\2\2\25\u0098\3\2\2\2\27\u009d\3\2\2\2\31\u00a1"+
		"\3\2\2\2\33\u00a5\3\2\2\2\35\u00aa\3\2\2\2\37\u00b0\3\2\2\2!\u00b6\3\2"+
		"\2\2#\u00b8\3\2\2\2%\u00ba\3\2\2\2\'\u00bc\3\2\2\2)\u00be\3\2\2\2+\u00c0"+
		"\3\2\2\2-\u00c2\3\2\2\2/\u00c4\3\2\2\2\61\u00c6\3\2\2\2\63\u00c8\3\2\2"+
		"\2\65\u00ca\3\2\2\2\67\u00cc\3\2\2\29\u00ce\3\2\2\2;\u00d0\3\2\2\2=\u00d3"+
		"\3\2\2\2?\u00d6\3\2\2\2A\u00d8\3\2\2\2C\u00da\3\2\2\2E\u00dd\3\2\2\2G"+
		"\u00e0\3\2\2\2I\u00e2\3\2\2\2K\u00e4\3\2\2\2M\u00e6\3\2\2\2O\u00e8\3\2"+
		"\2\2Q\u00ea\3\2\2\2S\u00ec\3\2\2\2U\u00ee\3\2\2\2W\u00fc\3\2\2\2Y\u00ff"+
		"\3\2\2\2[\u0114\3\2\2\2]\u0116\3\2\2\2_\u011e\3\2\2\2a\u0129\3\2\2\2c"+
		"\u012d\3\2\2\2e\u0135\3\2\2\2g\u013b\3\2\2\2i\u0140\3\2\2\2k\u0149\3\2"+
		"\2\2m\u0154\3\2\2\2o\u0162\3\2\2\2q\u0166\3\2\2\2st\7d\2\2tu\7q\2\2uv"+
		"\7q\2\2vw\7n\2\2w\4\3\2\2\2xy\7e\2\2yz\7j\2\2z{\7c\2\2{|\7t\2\2|\6\3\2"+
		"\2\2}~\7f\2\2~\177\7g\2\2\177\u0080\7n\2\2\u0080\b\3\2\2\2\u0081\u0082"+
		"\7f\2\2\u0082\u0083\7q\2\2\u0083\n\3\2\2\2\u0084\u0085\7g\2\2\u0085\u0086"+
		"\7n\2\2\u0086\u0087\7u\2\2\u0087\u0088\7g\2\2\u0088\f\3\2\2\2\u0089\u008a"+
		"\7h\2\2\u008a\u008b\7w\2\2\u008b\u008c\7p\2\2\u008c\16\3\2\2\2\u008d\u008e"+
		"\7k\2\2\u008e\u008f\7h\2\2\u008f\20\3\2\2\2\u0090\u0091\7k\2\2\u0091\u0092"+
		"\7p\2\2\u0092\u0093\7v\2\2\u0093\22\3\2\2\2\u0094\u0095\7p\2\2\u0095\u0096"+
		"\7g\2\2\u0096\u0097\7y\2\2\u0097\24\3\2\2\2\u0098\u0099\7v\2\2\u0099\u009a"+
		"\7j\2\2\u009a\u009b\7g\2\2\u009b\u009c\7p\2\2\u009c\26\3\2\2\2\u009d\u009e"+
		"\7v\2\2\u009e\u009f\7{\2\2\u009f\u00a0\7r\2\2\u00a0\30\3\2\2\2\u00a1\u00a2"+
		"\7x\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4\7t\2\2\u00a4\32\3\2\2\2\u00a5\u00a6"+
		"\7x\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7f\2\2\u00a9"+
		"\34\3\2\2\2\u00aa\u00ab\7y\2\2\u00ab\u00ac\7j\2\2\u00ac\u00ad\7g\2\2\u00ad"+
		"\u00ae\7t\2\2\u00ae\u00af\7g\2\2\u00af\36\3\2\2\2\u00b0\u00b1\7y\2\2\u00b1"+
		"\u00b2\7j\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7n\2\2\u00b4\u00b5\7g\2\2"+
		"\u00b5 \3\2\2\2\u00b6\u00b7\7*\2\2\u00b7\"\3\2\2\2\u00b8\u00b9\7+\2\2"+
		"\u00b9$\3\2\2\2\u00ba\u00bb\7}\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7\177\2"+
		"\2\u00bd(\3\2\2\2\u00be\u00bf\7]\2\2\u00bf*\3\2\2\2\u00c0\u00c1\7_\2\2"+
		"\u00c1,\3\2\2\2\u00c2\u00c3\7\60\2\2\u00c3.\3\2\2\2\u00c4\u00c5\7.\2\2"+
		"\u00c5\60\3\2\2\2\u00c6\u00c7\7<\2\2\u00c7\62\3\2\2\2\u00c8\u00c9\7=\2"+
		"\2\u00c9\64\3\2\2\2\u00ca\u00cb\7(\2\2\u00cb\66\3\2\2\2\u00cc\u00cd\7"+
		"~\2\2\u00cd8\3\2\2\2\u00ce\u00cf\7#\2\2\u00cf:\3\2\2\2\u00d0\u00d1\7?"+
		"\2\2\u00d1\u00d2\7?\2\2\u00d2<\3\2\2\2\u00d3\u00d4\7#\2\2\u00d4\u00d5"+
		"\7?\2\2\u00d5>\3\2\2\2\u00d6\u00d7\7>\2\2\u00d7@\3\2\2\2\u00d8\u00d9\7"+
		"@\2\2\u00d9B\3\2\2\2\u00da\u00db\7>\2\2\u00db\u00dc\7?\2\2\u00dcD\3\2"+
		"\2\2\u00dd\u00de\7@\2\2\u00de\u00df\7?\2\2\u00dfF\3\2\2\2\u00e0\u00e1"+
		"\7,\2\2\u00e1H\3\2\2\2\u00e2\u00e3\7\61\2\2\u00e3J\3\2\2\2\u00e4\u00e5"+
		"\7\'\2\2\u00e5L\3\2\2\2\u00e6\u00e7\7-\2\2\u00e7N\3\2\2\2\u00e8\u00e9"+
		"\7/\2\2\u00e9P\3\2\2\2\u00ea\u00eb\7`\2\2\u00ebR\3\2\2\2\u00ec\u00ed\7"+
		"?\2\2\u00edT\3\2\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1"+
		"\7p\2\2\u00f1\u00f2\7g\2\2\u00f2V\3\2\2\2\u00f3\u00f4\7v\2\2\u00f4\u00f5"+
		"\7t\2\2\u00f5\u00f6\7w\2\2\u00f6\u00fd\7g\2\2\u00f7\u00f8\7h\2\2\u00f8"+
		"\u00f9\7c\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7u\2\2\u00fb\u00fd\7g\2\2"+
		"\u00fc\u00f3\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fdX\3\2\2\2\u00fe\u0100\7"+
		"\62\2\2\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0107\t\2\2\2\u0104\u0106\t\3"+
		"\2\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\b-"+
		"\2\2\u010bZ\3\2\2\2\u010c\u0115\7\62\2\2\u010d\u0111\t\2\2\2\u010e\u0110"+
		"\t\3\2\2\u010f\u010e\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u010c\3\2"+
		"\2\2\u0114\u010d\3\2\2\2\u0115\\\3\2\2\2\u0116\u011a\7)\2\2\u0117\u011b"+
		"\t\4\2\2\u0118\u0119\7^\2\2\u0119\u011b\7)\2\2\u011a\u0117\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\7)\2\2\u011d^\3\2\2\2\u011e"+
		"\u0124\7$\2\2\u011f\u0123\t\5\2\2\u0120\u0121\7^\2\2\u0121\u0123\7$\2"+
		"\2\u0122\u011f\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122"+
		"\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0124\3\2\2\2\u0127"+
		"\u0128\7$\2\2\u0128`\3\2\2\2\u0129\u012a\7p\2\2\u012a\u012b\7k\2\2\u012b"+
		"\u012c\7n\2\2\u012cb\3\2\2\2\u012d\u0131\t\6\2\2\u012e\u0130\t\7\2\2\u012f"+
		"\u012e\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132d\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0136\t\b\2\2\u0135\u0134"+
		"\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139\u013a\b\63\3\2\u013af\3\2\2\2\u013b\u013c\t\t\2\2"+
		"\u013c\u013d\b\64\4\2\u013d\u013e\3\2\2\2\u013e\u013f\b\64\3\2\u013fh"+
		"\3\2\2\2\u0140\u0144\7%\2\2\u0141\u0143\n\n\2\2\u0142\u0141\3\2\2\2\u0143"+
		"\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2"+
		"\2\2\u0146\u0144\3\2\2\2\u0147\u0148\b\65\3\2\u0148j\3\2\2\2\u0149\u014f"+
		"\7$\2\2\u014a\u014e\t\5\2\2\u014b\u014c\7^\2\2\u014c\u014e\7$\2\2\u014d"+
		"\u014a\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2"+
		"\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151\u014f\3\2\2\2\u0152"+
		"\u0153\b\66\5\2\u0153l\3\2\2\2\u0154\u0158\7)\2\2\u0155\u0159\t\4\2\2"+
		"\u0156\u0157\7^\2\2\u0157\u0159\7)\2\2\u0158\u0155\3\2\2\2\u0158\u0156"+
		"\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\b\67\6\2\u015bn\3\2\2\2\u015c"+
		"\u015d\7)\2\2\u015d\u015e\7)\2\2\u015e\u0163\7)\2\2\u015f\u0160\7$\2\2"+
		"\u0160\u0161\7$\2\2\u0161\u0163\7$\2\2\u0162\u015c\3\2\2\2\u0162\u015f"+
		"\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165\b8\7\2\u0165p\3\2\2\2\u0166\u0167"+
		"\13\2\2\2\u0167\u0168\b9\b\2\u0168r\3\2\2\2\22\2\u00fc\u0101\u0107\u0111"+
		"\u0114\u011a\u0122\u0124\u0131\u0137\u0144\u014d\u014f\u0158\u0162\t\3"+
		"-\2\b\2\2\3\64\3\3\66\4\3\67\5\38\6\39\7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}