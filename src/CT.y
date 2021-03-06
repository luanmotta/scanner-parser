/* Imports */
%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */

%token incluir
%token definir

%token main
%token subrotina

%token virgula
%token ponto_e_virgula
%token dois_pontos

%token abre_chaves
%token fecha_chaves
%token abre_colchetes
%token fecha_colchetes
%token abre_parenteses
%token fecha_parenteses

%token atribuicao

%token retornar
%token se
%token senao
%token caso
%token opcao
%token fim_opcao
%token enquanto
%token para
%token faca
%token ate

%token inteiro
%token real
%token caracter



%token <sval> inclusao_arquivo
%token <sval> valor_primitivo
%token <sval> comentario
%token <sval> operador
%token <sval> comparador
%token <sval> incdec
%token <sval> identificador
%token <sval> imprima



%type <sval> PROGRAMA
%type <sval> INCLUSAO
%type <sval> DEFINICAO
%type <sval> MAIN
%type <sval> SUBROTINA
%type <sval> PARAM
%type <sval> PARAMS
%type <sval> BLOCO
%type <sval> CONTEUDO
%type <sval> LINHA
%type <sval> EXECUCAO
%type <sval> DECLARACAO
%type <sval> ATRIBUICAO
%type <sval> CALL
%type <sval> CALL_PARAM
%type <sval> CALL_PARAMS
%type <sval> COMANDO
%type <sval> CONDICIONAL
%type <sval> LACO
%type <sval> RETURN
%type <sval> IF
%type <sval> CAN_BLOCO
%type <sval> ELSE
%type <sval> SWITCH
%type <sval> SWITCH_ID
%type <sval> SWITCH_BLOCO
%type <sval> SWITCH_OPCOES
%type <sval> SWITCH_OPCAO
%type <sval> OPCAO_VALOR
%type <sval> OPCAO_BLOCO
%type <sval> OPCAO_CONTEUDO
%type <sval> WHILE
%type <sval> DO
%type <sval> UNTIL
%type <sval> FOR
%type <sval> FOR_PARENTESES
%type <sval> FOR_COMPOSICAO
%type <sval> CAN_ATRIBUICAO
%type <sval> CAN_COMPARACAO
%type <sval> CAN_EXPRESSAO
%type <sval> CONDICAO
%type <sval> COMPARACAO
%type <sval> EXPRESSAO
%type <sval> PARENTESES_EXP
%type <sval> EXP
%type <sval> OPERACAO
%type <sval> VALOR
%type <sval> ID_INCDEC
%type <sval> TIPO
%type <sval> IMPRIMIR






/* Inicio das regras da gramática */
%%
INICIO          : PROGRAMA	 { System.out.println("\n\n\n" + $1); }


PROGRAMA        : INCLUSAO         PROGRAMA {$$=    $1 + "\n" + $2   ;}
                | DEFINICAO        PROGRAMA {$$=    $1 + "\n" + $2   ;}
		            | MAIN             PROGRAMA {$$=    $1 + "\n" + $2   ;}
								| SUBROTINA        PROGRAMA {$$=    $1 + "\n" + $2   ;}
								| comentario       PROGRAMA {$$=    $1 + "\n" + $2   ;}
	              |					                  {$$=    ""               ;}


INCLUSAO        : incluir inclusao_arquivo	{$$=    "#include " + $2   ;}


DEFINICAO       : definir identificador valor_primitivo	{$$=    "#define " + $2 + " " + $3   ;}


MAIN            : main BLOCO {$$=    "\nint main()" + $2   ;}


SUBROTINA       : subrotina DECLARACAO PARAM BLOCO {$$=   "\n" + $2 + " " + $3 + $4    ;}


PARAM           : abre_parenteses PARAMS fecha_parenteses {$$=   "(" + $2 + ")"    ;}


PARAMS          : DECLARACAO                     {$$=    $1                 ;}
                | DECLARACAO virgula DECLARACAO  {$$=    $1 + ", " + $3     ;}
								|                                {$$=    ""                 ;}


BLOCO           : abre_chaves CONTEUDO fecha_chaves {$$=    " {\n" + $2 + "}\n"   ;}


CONTEUDO        : LINHA CONTEUDO {$$=   "" + $1 + "\n" + $2   ;}
                | LINHA          {$$=   "" + $1 + "\n"        ;}
								|                {$$=   ""                     ;}


LINHA           : EXECUCAO    {$$=    $1     ;}
                | comentario  {$$=    $1     ;}


EXECUCAO        : DECLARACAO {$$=    $1 + ";"   ;}
					      | ATRIBUICAO {$$=    $1 + ";"   ;}
								| CALL       {$$=    $1 + ";"   ;}
								| IMPRIMIR   {$$=    $1 + ";"   ;}
								| ID_INCDEC  {$$=    $1 + ";"   ;}
								| COMANDO    {$$=    $1         ;}


DECLARACAO      : TIPO identificador {$$=    $1 + $2  ;}


ATRIBUICAO      : identificador atribuicao EXPRESSAO {$$=  $1 + " = " + $3   ;}


CALL            : identificador CALL_PARAM  {$$=   $1 + $2   ;}


CALL_PARAM      : abre_parenteses CALL_PARAMS fecha_parenteses  {$$=    "(" + $2 + ")"   ;}


CALL_PARAMS     : EXPRESSAO                    {$$=    $1                 ;}
                | EXPRESSAO virgula EXPRESSAO  {$$=    $1 + ", " + $3     ;}
                |                              {$$=    ""                 ;}


COMANDO         : CONDICIONAL {$$=    $1    ;}
                | LACO        {$$=    $1    ;}
								| RETURN      {$$=    $1    ;}


CONDICIONAL     : IF       {$$=    $1    ;}
                | SWITCH   {$$=    $1    ;}

LACO            : WHILE {$$=    $1    ;}
                | DO    {$$=    $1    ;}
                | FOR   {$$=    $1    ;}


RETURN          : retornar EXPRESSAO   {$$=    "return " + $2 + ";"   ;}


IF              : se CONDICAO CAN_BLOCO ELSE  {$$=  "if " + $2 + $3 + $4  ;}


CAN_BLOCO       : BLOCO    {$$=    $1        ;}
                | EXECUCAO {$$=   " " + $1   ;}


ELSE            : senao BLOCO {$$=   "else " + $2   ;}
                |             {$$=   ""             ;}


SWITCH          : caso SWITCH_ID SWITCH_BLOCO {$$=  "switch " + $2 + $3   ;}


SWITCH_ID       : abre_parenteses identificador fecha_parenteses {$$=    "( " + $2 + " )"   ;}


SWITCH_BLOCO    : abre_chaves SWITCH_OPCOES fecha_chaves {$$=    " {\n" + $2 + "}\n"   ;}


SWITCH_OPCOES   : SWITCH_OPCAO SWITCH_OPCOES {$$=   "" + $1 + "\n" + $2   ;}
                | SWITCH_OPCAO               {$$=   "" + $1 + "\n"        ;}


SWITCH_OPCAO    : opcao OPCAO_VALOR dois_pontos OPCAO_BLOCO {$$=   "case " + $2 + ":\n" + $4   ;}
                | opcao OPCAO_VALOR dois_pontos             {$$=   "case " + $2 + ":\n"        ;}


OPCAO_VALOR     : identificador   {$$=   $1   ;}
                | valor_primitivo {$$=   $1   ;}


OPCAO_BLOCO     : OPCAO_CONTEUDO {$$=    "{\n" + $1 + "}\n"   ;}


OPCAO_CONTEUDO  : LINHA OPCAO_CONTEUDO  {$$=   $1 + "\n" + $2   ;}
                | fim_opcao             {$$=   "break;\n"         ;}

WHILE           : enquanto CONDICAO BLOCO {$$=  "while " + $2 + $3   ;}


DO              : faca BLOCO UNTIL {$$=   "do " + $2 + $3  ;}


UNTIL           : ate CONDICAO {$$=   "while " + $2 + ";\n"  ;}


FOR             : para FOR_PARENTESES BLOCO {$$=  "for " + $2 + $3   ;}


FOR_PARENTESES  : abre_parenteses FOR_COMPOSICAO fecha_parenteses {$$=    "( " + $2 + " )"   ;}


FOR_COMPOSICAO  : CAN_ATRIBUICAO ponto_e_virgula CAN_COMPARACAO ponto_e_virgula CAN_EXPRESSAO {$$= $1 + " ; " + $3 + " ; " + $5  ;}


CAN_ATRIBUICAO  : ATRIBUICAO {$$=   $1   ;}
                |            {$$=   ""   ;}


CAN_COMPARACAO  : COMPARACAO {$$=   $1   ;}
                |            {$$=   ""   ;}


CAN_EXPRESSAO   : EXPRESSAO  {$$=   $1   ;}
                |            {$$=   ""   ;}


CONDICAO        : abre_parenteses COMPARACAO fecha_parenteses {$$=    "( " + $2 + " )"   ;}


COMPARACAO      : EXP comparador EXP  {$$=  $1 + " " + $2 + " " + $3   ;}


EXPRESSAO       : EXP                      {$$=    $1              ;}
                | PARENTESES_EXP           {$$=    $1              ;}
								| PARENTESES_EXP OPERACAO  {$$=    $1 + $2         ;}


PARENTESES_EXP  : abre_parenteses EXP fecha_parenteses {$$=    "(" + $2 + ")" ;}


EXP             : VALOR OPERACAO   {$$=    $1 + $2    ;}


OPERACAO        : operador EXPRESSAO {$$=    " " + $1 + " " + $2    ;}
                |                    {$$=        ""                 ;}

VALOR           : valor_primitivo    {$$=    $1    ;}
                | identificador      {$$=    $1    ;}
                | ID_INCDEC          {$$=    $1    ;}
								| CALL               {$$=    $1    ;}


ID_INCDEC       : identificador incdec {$$=    $1 + $2   ;}


TIPO            : inteiro   {$$=    "int "      ;}
                | real      {$$=    "double "   ;}
		            | caracter  {$$=    "char "     ;}


IMPRIMIR        : imprima  {$$=   "printf" + $1.substring(7, $1.length())   ;}













%%
/* Início do Código em Java */

	// Referencia ao JFlex
	private Yylex lexer;

	/* Interface com o JFlex */
	private int yylex(){
		int yyl_return = -1;
		try {
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("Erro de IO: " + e);
		}
		return yyl_return;
	}

	/* Reporte de erro */
	public void yyerror(String error){
		System.err.println("Error: " + error);
	}

	// Interface com o JFlex eh criado no construtor
	public Parser(Reader r){
		lexer = new Yylex(r, this);
	}

	// Main
	public static void main(String[] args){
		try{
			Parser yyparser = new Parser(new FileReader(args[0]));
			yyparser.yyparse();
			} catch (IOException ex) {
				System.err.println("Error: " + ex);
			}
	}
