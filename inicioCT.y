/* Imports */
%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */

%token incluir

%token main

%token abre_chaves
%token fecha_chaves
%token abre_colchetes
%token fecha_colchetes
%token abre_parenteses
%token fecha_parenteses

%token atribuicao

%token inteiro
%token real
%token caracter



%token <sval> inclusao_arquivo
%token <sval> valor_primitivo
%token <sval> identificador
%token <sval> comentario
%token <sval> operador



%type <sval> PROGRAMA
%type <sval> INCLUSAO
%type <sval> MAIN
%type <sval> BLOCO
%type <sval> CONTEUDO
%type <sval> LINHA
%type <sval> EXECUCAO
%type <sval> DECLARACAO
%type <sval> ATRIBUICAO
%type <sval> EXPRESSAO
%type <sval> PARENTESES_EXP
%type <sval> EXP
%type <sval> OPERACAO
%type <sval> VALOR
%type <sval> TIPO






/* Inicio das regras da gramática */
%%
INICIO          : PROGRAMA	 { System.out.println("\n\n\n" + $1); }


PROGRAMA        : INCLUSAO         PROGRAMA {$$=    $1 + "\n" + $2   ;}
		            | MAIN             PROGRAMA {$$=    $1 + "\n" + $2   ;}
	              |					                  {$$=    ""               ;}


INCLUSAO        : incluir inclusao_arquivo	{$$=    "#include " + $2   ;}


MAIN            : main BLOCO {$$=    "\nint main()" + $2   ;}


BLOCO           : abre_chaves CONTEUDO fecha_chaves {$$=    " {\n" + $2 + "}\n"   ;}


CONTEUDO        : LINHA CONTEUDO {$$=   "  " + $1 + "\n" + $2   ;}
                | LINHA          {$$=   "  " + $1 + "\n"        ;}


LINHA           : EXECUCAO    {$$=    $1     ;}
                | comentario  {$$=    $1     ;}


EXECUCAO        : DECLARACAO {$$=    $1 + ";"   ;}
					      | ATRIBUICAO {$$=    $1 + ";"   ;}


DECLARACAO      : TIPO identificador {$$=    $1 + $2  ;}


ATRIBUICAO      : identificador atribuicao EXPRESSAO {$$=  $1 + " = " + $3   ;}


EXPRESSAO       : EXP                      {$$=    $1              ;}
                | PARENTESES_EXP           {$$=    $1              ;}
								| PARENTESES_EXP OPERACAO  {$$=    $1 + $2   ;}


PARENTESES_EXP  : abre_parenteses EXP fecha_parenteses {$$=    "(" + $2 + ")" ;}


EXP             : VALOR OPERACAO   {$$=    $1 + $2    ;}
                | VALOR            {$$=    $1         ;}


OPERACAO        : operador EXPRESSAO {$$=    " " + $1 + " " + $2    ;}


VALOR           : valor_primitivo {$$=    $1    ;}
                | identificador   {$$=    $1    ;}


TIPO            : inteiro   {$$=    "int "      ;}
                | real      {$$=    "double "   ;}
		            | caracter  {$$=    "char "     ;}
















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
