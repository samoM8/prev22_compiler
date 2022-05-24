lexer grammar PrevLexer;

@header {
	package prev.phase.lexan;
	import prev.common.report.*;
	import prev.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {
		return (Token) super.nextToken();
	}
}

//Keywords
BOOL  : 'bool'  ;
CHAR  : 'char'  ;
DEL   : 'del'   ;
DO    : 'do'    ;
ELSE  : 'else'  ;
FUN   : 'fun'   ;
IF    : 'if'    ;
INT   : 'int'   ;
NEW   : 'new'   ;
THEN  : 'then'  ;
TYP   : 'typ'   ;
VAR   : 'var'   ;
VOID  : 'void'  ;
WHERE : 'where' ;
WHILE : 'while' ;

//Symbols
LPAREN : '('  ;
RPAREN : ')'  ;
LBRACE : '{'  ;
RBRACE : '}'  ;
LBRACK : '['  ;
RBRACK : ']'  ;
DOT    : '.'  ;
COMMA  : ','  ;
COLON  : ':'  ;
SECOL  : ';'  ;
BITAND : '&'  ;
BITOR  : '|'  ;
BANG   : '!'  ;
EQUAL  : '==' ;
NOTEQ  : '!=' ;
LT     : '<'  ;
GT     : '>'  ;
LE     : '<=' ;
GE     : '>=' ;
MUL    : '*'  ;
DIV    : '/'  ;
MOD    : '%'  ;
PLUS   : '+'  ;
MINUS  : '-'  ;
POWER  : '^'  ;
ASSIGN : '='  ;

// Constants
NONE : 'none' ;
BOOLLIT : 'true' | 'false' ;

ZERO_PADDED_ERROR
    : '0'+ [1-9] [0-9]*
    {if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Zero padded number/leading zeros"); } ;

NUM : '0' | [1-9] [0-9]* ;
//fragment DIGIT : [0-9] ; //not a token

//[\u0020-\u007E] od 32 - 126 v ascii
CHARLIT : '\'' ([\u0020-\u0026\u0028-\u007E] | '\\\'') '\'' ; //char ali \'

STRINGLIT : '"' ([\u0020\u0021\u0023-\u007E] | '\\"')* '"' ;
//fragment SingleCharacter : [\u0020-\u007E]; // od 32 - 126 v ascii

NIL : 'nil' ;

// Identifier
ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

// White space and comments
WS      : [ \n\r]+ -> skip ; //skip white space
TAB     : [\t]
        {
        int pos = this.getCharPositionInLine();
        int mod = (pos-1) % 8;
        this.setCharPositionInLine(pos-1 + 8 - mod);
        }
        -> skip; //add so many places so position + 1 % 8 == 0

COMMENT : '#' ~[\r\n]* -> skip ; //skip comments. Starts with # till new line

// Errors
UNTERMINATED_STRING
    : '"' ([\u0020\u0021\u0023-\u007E] | '\\"')*
    {if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unterminated string"); } ;

UNTERMINATED_CHAR
    : '\'' ([\u0020-\u0026\u0028-\u007E] | '\\\'')
    {if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unterminated character"); } ;

UNESCAPED_CHARACTER
    : ('\'\'\'' | '"""')
    {if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unescaped character"); } ;

UNRECOGNIZED
    : .
    {if (true) throw new Report.Error(new Location(this.getLine(), this.getCharPositionInLine()), "Unrecognized: " + this.getText()); } ;
