parser grammar dcmParser;
options { tokenVocab=dcmLexer; }

@header {
	package net.alenzen.dcm.antlr;
}

string_exp : string=STRING;

/*
 * ROOT rule
 */
dcm_file : comment_exp? konservierung_format_exp dcm_file_sub_exp;

/* TODO: name the elements of each expression to access them more easily */

nl : EOF | NEWLINE;
value : INT | DECIMAL | TRUE | FALSE;

comment_exp : COMMENT+;

konservierung_format_exp : KONSERVIERUNG_FORMAT nl; 
dcm_file_sub_exp : 
	(functions_exp 
	| variant_coding_exp 
	| module_header_exp 
	| parameter_exp
	| array_exp
	| matrix_exp
	| characteristic_line_exp
	| characteristic_map_exp
	| fixed_characteristic_line_exp
	| fixed_characteristic_map_exp
	| group_characteristic_line_exp
	| group_characteristic_map_exp
	| distribution_exp
	)*;
	
end_exp : END nl;

functions_exp : preComment=comment_exp? FUNKTIONEN nl functions_sub_exp* endComment=comment_exp? end_exp;
functions_sub_exp : comment_exp? FKT name=IDENTIFIER version=string_exp longname=string_exp nl;

variant_coding_exp : preComment=comment_exp? VARIANTENKODIERUNG nl variant_coding_sub_exp* endComment=comment_exp? end_exp;
variant_coding_sub_exp : comment_exp? KRITERIUM name=IDENTIFIER values+=value* nl;

module_header_exp : comment_exp? (MODULKOPF name=IDENTIFIER text=string_exp nl) lines+=module_header_short_exp*;
module_header_short_exp : comment_exp? MODULKOPF text=string_exp nl;

parameter_name_exp : IDENTIFIER | PARAMETER_NAME;

parameter_exp : preComment=comment_exp? FESTWERT name=parameter_name_exp nl parameter_sub_exp endComment=comment_exp? end_exp;
parameter_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp 
	| unit_w_exp 
	| value_exp
	)*;

longname_exp : comment_exp? LANGNAME string_exp nl;
displayname_exp : comment_exp? DISPLAYNAME parameter_name_exp nl;

var_exp : comment_exp? VAR name_value_pair* nl;
name_value_pair : name=IDENTIFIER EQUALS value;

function_exp : comment_exp? FUNKTION name=IDENTIFIER nl;
unit_w_exp : comment_exp? EINHEIT_W string_exp nl;
unit_x_exp : comment_exp? EINHEIT_X string_exp nl;
unit_y_exp : comment_exp? EINHEIT_Y string_exp nl;

value_exp : number_value_exp | string_value_exp;
number_value_exp : comment_exp? WERT value nl;
string_value_exp : comment_exp? TEXT string_exp nl;

size_exp : INT;
array_exp : preComment=comment_exp? FESTWERTEBLOCK name=parameter_name_exp sizeX=size_exp nl array_sub_exp endComment=comment_exp? end_exp;
array_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp 
	| unit_w_exp
	| value_list_exp
	)*;
value_list_exp : number_value_list_exp | string_value_list_exp;
number_value_list_exp : comment_exp? WERT value+ nl;
string_value_list_exp : comment_exp? TEXT string_exp+ nl;

matrix_exp : preComment=comment_exp? FESTWERTEBLOCK name=parameter_name_exp sizeX=size_exp AT sizeY=size_exp nl matrix_sub_exp endComment=comment_exp? end_exp;
matrix_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp 
	| unit_w_exp
	| value_list_exp
	)*;
	
characteristic_line_exp : preComment=comment_exp? KENNLINIE name=parameter_name_exp sizeX=size_exp nl characteristic_line_sub_exp endComment=comment_exp? end_exp;
characteristic_line_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| unit_w_exp
	| st_x_exp
	| value_list_exp
	)*;
st_x_exp : comment_exp? ST_X value+ nl;
st_y_exp : comment_exp? ST_Y value nl;

characteristic_map_exp : preComment=comment_exp? KENNFELD name=parameter_name_exp sizeX=size_exp sizeY=size_exp nl characteristic_map_sub_exp endComment=comment_exp? end_exp;
characteristic_map_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| unit_y_exp
	| unit_w_exp
	| st_x_exp
	| st_y_exp
	| value_list_exp
	)*;

fixed_characteristic_line_exp : preComment=comment_exp? FESTKENNLINIE name=parameter_name_exp sizeX=size_exp nl fixed_characteristic_line_sub_exp endComment=comment_exp? end_exp;
fixed_characteristic_line_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| unit_w_exp
	| st_x_exp
	| value_list_exp
	)*;
	
fixed_characteristic_map_exp : preComment=comment_exp? FESTKENNFELD name=parameter_name_exp sizeX=size_exp sizeY=size_exp nl fixed_characteristic_map_sub_exp endComment=comment_exp? end_exp;
fixed_characteristic_map_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp
	| unit_y_exp 
	| unit_w_exp
	| st_x_exp
	| st_y_exp
	| value_list_exp
	)*;

group_characteristic_line_exp : preComment=comment_exp? GRUPPENKENNLINIE name=parameter_name_exp sizeX=size_exp nl group_characteristic_line_sub_exp endComment=comment_exp? end_exp;
group_characteristic_line_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| unit_w_exp
	| sstx_exp
	| st_x_exp
	| value_list_exp
	)*;
sstx_exp : comment_exp? SSTX;

group_characteristic_map_exp : preComment=comment_exp? GRUPPENKENNFELD name=parameter_name_exp sizeX=size_exp sizeY=size_exp nl group_characteristic_map_sub_exp endComment=comment_exp? end_exp;
group_characteristic_map_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| unit_y_exp 
	| unit_w_exp
	| sstx_exp
	| ssty_exp
	| st_x_exp
	| st_y_exp
	| value_list_exp
	)*;
ssty_exp : comment_exp? SSTY;

distribution_exp : preComment=comment_exp? STUETZSTELLENVERTEILUNG name=parameter_name_exp sizeX=size_exp nl distribution_sub_exp endComment=comment_exp? end_exp;
distribution_sub_exp : (
	longname_exp 
	| displayname_exp 
	| var_exp 
	| function_exp
	| unit_x_exp 
	| st_x_exp
	)*;