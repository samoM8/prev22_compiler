		LOC	Data_Segment
		GREG 0
		GREG 0
		GREG 0
OutBuff		OCTA	0,0
InSize		IS	32
InBuff		BYTE	0
		LOC	InBuff+InSize
InArgs		OCTA	InBuff,InSize
		LOC	#100
Main		SETH	$254,24576
		ADD	$253,$254,16
		SETH $252,16384
		SET	$0,252
		PUT	rG,$0
		PUSHJ	$8,_main
		LDO	$255,$254,0
End		TRAP	 0,Halt,0
_main		SETL $0,8
		NEG $0,$0
		STO $253,$254,$0
		ADD $253,$254,0
		SETL $0,48
		NEG $0,$0
		ADD $254,$254,$0
		GET $0,rJ
		SETL $1,16
		NEG $1,$1
		STO $0,$253,$1
		JMP L0
L0		LDO $1,$253,0
		SETL $0,55
		SET $3,$0
		SETL $0,56
		SET $0,$0
		SETL $2,57
		SET $2,$2
		STO $1,$254,0
		STO $3,$254,8
		STO $0,$254,16
		STO $2,$254,24
		PUSHJ $8,_fun1
		LDO $0,$254,0
		SET $0,$0
		LDO $1,$253,0
		STO $1,$254,0
		STO $0,$254,8
		PUSHJ $8,_putChar
		LDO $0,$254,0
		LDO $0,$253,0
		STO $0,$254,0
		SETL $0,10
		STO $0,$254,8
		PUSHJ $8,_putChar
		LDO $0,$254,0
		SETL $0,0
		SET $0,$0
		SET $0,$0
		JMP L1
L1		STO $0,$253,0
		SETL $1,16
		NEG $1,$1
		LDO $0,$253,$1
		PUT rJ,$0
		ADD $254,$253,0
		SETL $0,8
		NEG $0,$0
		LDO $253,$254,$0
		POP 8,0
_fun1		SETL $0,8
		NEG $0,$0
		STO $253,$254,$0
		ADD $253,$254,0
		SETL $0,40
		NEG $0,$0
		ADD $254,$254,$0
		GET $0,rJ
		SETL $1,16
		NEG $1,$1
		STO $0,$253,$1
		JMP L2
L2		LDO $0,$253,0
		SET $2,$253
		SETL $1,8
		SET $1,$1
		ADD $1,$2,$1
		LDO $3,$1,0
		SET $2,$253
		SETL $1,16
		SET $1,$1
		ADD $1,$2,$1
		LDO $1,$1,0
		STO $0,$254,0
		STO $3,$254,8
		STO $1,$254,16
		PUSHJ $8,_fun2
		LDO $0,$254,0
		SET $1,$0
		LDO $0,$253,0
		STO $0,$254,0
		STO $1,$254,8
		PUSHJ $8,_putChar
		LDO $0,$254,0
		SET $1,$253
		SETL $0,24
		SET $0,$0
		ADD $0,$1,$0
		LDO $1,$0,0
		SETL $0,10
		SET $0,$0
		ADD $0,$1,$0
		SET $0,$0
		SET $0,$0
		JMP L3
L3		STO $0,$253,0
		SETL $1,16
		NEG $1,$1
		LDO $0,$253,$1
		PUT rJ,$0
		ADD $254,$253,0
		SETL $0,8
		NEG $0,$0
		LDO $253,$254,$0
		POP 8,0
_fun2		SETL $0,8
		NEG $0,$0
		STO $253,$254,$0
		ADD $253,$254,0
		SETL $0,32
		NEG $0,$0
		ADD $254,$254,$0
		GET $0,rJ
		SETL $1,16
		NEG $1,$1
		STO $0,$253,$1
		JMP L4
L4		LDO $2,$253,0
		SET $1,$253
		SETL $0,8
		SET $0,$0
		ADD $0,$1,$0
		LDO $0,$0,0
		STO $2,$254,0
		STO $0,$254,8
		PUSHJ $8,_fun3
		LDO $0,$254,0
		SET $0,$0
		LDO $1,$253,0
		STO $1,$254,0
		STO $0,$254,8
		PUSHJ $8,_putChar
		LDO $0,$254,0
		SET $1,$253
		SETL $0,16
		SET $0,$0
		ADD $0,$1,$0
		LDO $1,$0,0
		SETL $0,10
		SET $0,$0
		ADD $0,$1,$0
		SET $0,$0
		SET $0,$0
		JMP L5
L5		STO $0,$253,0
		SETL $1,16
		NEG $1,$1
		LDO $0,$253,$1
		PUT rJ,$0
		ADD $254,$253,0
		SETL $0,8
		NEG $0,$0
		LDO $253,$254,$0
		POP 8,0
_fun3		SETL $0,8
		NEG $0,$0
		STO $253,$254,$0
		ADD $253,$254,0
		SETL $0,16
		NEG $0,$0
		ADD $254,$254,$0
		GET $0,rJ
		SETL $1,16
		NEG $1,$1
		STO $0,$253,$1
		JMP L6
L6		SET $1,$253
		SETL $0,8
		SET $0,$0
		ADD $0,$1,$0
		LDO $1,$0,0
		SETL $0,10
		SET $0,$0
		ADD $0,$1,$0
		SET $0,$0
		SET $0,$0
		JMP L7
L7		STO $0,$253,0
		SETL $1,16
		NEG $1,$1
		LDO $0,$253,$1
		PUT rJ,$0
		ADD $254,$253,0
		SETL $0,8
		NEG $0,$0
		LDO $253,$254,$0
		POP 8,0
_getChar		LDA	$255,InArgs
		TRAP	0,Fgets,StdIn
		LDB	$0,InBuff
		STO	$0,$254,0
		POP
_putChar		LDO	$0,$254,8
		LDA	$255,OutBuff
		STB	$0,$255,0
		TRAP	0,Fputs,StdOut
		POP
_new		LDO	$0,$254,0
		STO	$252,$254,0
		ADD	$252,$252,$0
		POP	0,0
_del		POP	0,0
