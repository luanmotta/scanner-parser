/* Imports */
%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */

%token incluir

%token main
%token subrotina

%token abre_chaves
%token fecha_chaves
%token abre_colchetes
%token fecha_colchetes
%token abre_parenteses
%token fecha_parenteses

%token inteiro
%token real
%token caractere



%token <sval> inclusao_arquivo
%token <sval> identificador



%type <sval> PROGRAMA
%type <sval> INCLUSAO
%type <sval> MAIN
%type <sval> FUNCAO
%type <sval> PARAMETROS
%type <sval> COMANDOS
%type <sval> DECLARACAO
%type <sval> TIPO






/* Inicio das regras da gramática */
%%
INICIO      : PROGRAMA	 { System.out.println($1); }


PROGRAMA    : INCLUSAO         PROGRAMA {$$=    $1 + "\n" + $2   ;}
				    | FUNCAO           PROGRAMA {$$=    $1 + "\n" + $2   ;}
		        | MAIN             PROGRAMA {$$=    $1 + "\n" + $2   ;}
	          |					                  {$$=    ""               ;}

MAIN        : main abre_chaves COMANDOS fecha_chaves {$$=    "int main() {\n " + $3 + "}\n"   ;}


FUNCAO      : subrotina DECLARACAO abre_parenteses PARAMETROS fecha_parenteses abre_chaves COMANDOS fecha_chaves {$$=    $2 + "(" + $4 + ")" + "{\n " + $7 + "}\n"   ;}


PARAMETROS  : DECLARACAO {$$=    $1   ;}
            |            {$$=    ""   ;}


INCLUSAO    : incluir inclusao_arquivo	{$$=    "#include " + $2   ;}


COMANDOS    : DECLARACAO	{$$=    $1   ;}
		        |					    {$$=    ""   ;}


DECLARACAO  : TIPO identificador DECLARACAO	  {$$=    $1 + $2 + ";\n" + $3   ;}
		        |                                 {$$=    ""                     ;}


TIPO        : inteiro   {$$=    "int "      ;}
            | real      {$$=    "double "   ;}
		        | caractere {$$=    "char "     ;}
















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
