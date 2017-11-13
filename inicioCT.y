/* Imports */
%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> INCLUSAO_ARQUIVO

%token INCLUIR

%token ABRE_CHAVES
%token FECHA_CHAVES
%token ABRE_COLCHETES
%token FECHA_COLCHETES
%token ABRE_PARENTESES
%token FECHA_PARENTESES

%token FUNCAO
%token FUNCAO_PRINCIPAL

%token INTEIRO
%token REAL
%token CARACTERE

%type <sval> programa
%type <sval> funcao
%type <sval> funcao_principal
%type <sval> inclusao
%type <sval> comandos
%type <sval> declaracao

/* Inicio das regras da gramática */
%%
inicio   : programa	 { System.out.println($1); }

programa : inclusao         programa { $$ = $1 + "\n" + $2; }
				 | funcao           programa { $$ = $1 + "\n" + $2; }
		     | funcao_principal programa { $$ = $1 + "\n" + $2; }
	       |					                 { $$ = ""; }

// funcao : FUNCAO ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao	{ $$ = $1; }
		     |					  { $$ = ""; }

declaracao : tipo IDENTIFICADOR declaracao	  { $$ = "$1"    + $2 + ";\n" + $3; }
		       | tipo IDENTIFICADOR declaracao    { $$ = "$2"    + $2 + ";\n" + $3; }
		       |                                  { $$ = "";                        }

tipo: INTEIRO   { $$ = "int" }
    | REAL      { $$ = "double" }
		| CARACTERE { $$ = "" }


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
