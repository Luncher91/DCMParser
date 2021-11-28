lexer grammar dcmLexer;

@header {
	package net.alenzen.dcm.antlr;
}

SSTX : {getCharPositionInLine() == 0}? WS* '*SSTX' .*? NEWLINE;
SSTY : {getCharPositionInLine() == 0}? WS* '*SSTY' .*? NEWLINE;

COMMENT : {getCharPositionInLine() == 0}? WS* ('!' | '.' | '*') .*? NEWLINE;

WS : (' ' | '\t') ->  skip;
NEWLINE : ('\r'? '\n' | '\r')+;

STRING : '"' (~('"' | '\\') | '\\' ('"' | '\\' | '\'' | 'n' | 'r' | 't') | '""')* '"';

// DCM 1.x
WERT : 'WERT';
TEXT : 'TEXT';
END : 'END';

KONSERVIERUNG_FORMAT : 'KONSERVIERUNG_FORMAT' WS+ '2.0';
FESTWERT : 'FESTWERT';
FESTWERTEBLOCK : 'FESTWERTEBLOCK';
KENNLINIE : 'KENNLINIE';
ST_X : 'ST/X';
KENNFELD : 'KENNFELD';
ST_Y : 'ST/Y';
FESTKENNLINIE : 'FESTKENNLINIE';
FESTKENNFELD : 'FESTKENNFELD';
GRUPPENKENNLINIE : 'GRUPPENKENNLINIE';
GRUPPENKENNFELD : 'GRUPPENKENNFELD';
STUETZSTELLENVERTEILUNG : 'STUETZSTELLENVERTEILUNG';


// DCM 2.x
FUNKTIONEN : 'FUNKTIONEN';
FKT : 'FKT';
VARIANTENKODIERUNG : 'VARIANTENKODIERUNG';
KRITERIUM : 'KRITERIUM';
MODULKOPF : 'MODULKOPF';
LANGNAME : 'LANGNAME';
DISPLAYNAME : 'DISPLAYNAME';
VAR : 'VAR';
FUNKTION : 'FUNKTION';
EINHEIT_W : 'EINHEIT_W';
EINHEIT_X : 'EINHEIT_X';
EINHEIT_Y : 'EINHEIT_Y';

INT : [\-+]? [0-9]+;
//DECIMAL : [\-+]? (([0-9]* '.'? [0-9]+)|([0-9]+ '.'? [0-9]*)) ([eE][\-+]?[0-9]+)?;
DECIMAL : [\-+]? (([0-9]+ '.'? [0-9]*) | ([0-9]* '.' [0-9]+)) ([eE] [\-+]? [0-9]+)?;
TRUE : 'true';
FALSE : 'false';


IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_]*;
PARAMETER_NAME : [a-zA-Z_][a-zA-Z0-9_\-]* ('[' [a-zA-Z0-9_]+ ']')* ([_.] [a-zA-Z0-9_\-]* ('[' [a-zA-Z0-9_]+ ']')*)*;

EQUALS : '=';
AT : '@';