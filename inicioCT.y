/* Imports */
%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */

%token INCLUIR

%token FUNCAO_PRINCIPAL
%token SUBROTINA

%token ABRE_CHAVES
%token FECHA_CHAVES
%token ABRE_COLCHETES
%token FECHA_COLCHETES
%token ABRE_PARENTESES
%token FECHA_PARENTESES


%token INTEIRO
%token REAL
%token CARACTERE


%token <sval> INCLUSAO_ARQUIVO
%token <sval> IDENTIFICADOR


%type <sval> programa
%type <sval> inclusao
%type <sval> funcao_principal
%type <sval> funcao
%type <sval> parametros
%type <sval> comandos
%type <sval> declaracao
%type <sval> tipo

/* Inicio das regras da gramática */
%%
inicio   : programa	 { System.out.println($1); }

programa : inclusao         programa { $$ = $1 + "\n" + $2; }
				 | funcao           programa { $$ = $1 + "\n" + $2; }
		     | funcao_principal programa { $$ = $1 + "\n" + $2; }
	       |					                 { $$ = "";             }

funcao_principal : FUNCAO_PRINCIPAL ABRE_CHAVES comandos FECHA_CHAVES { $$ = "int main() {\n " + $3 + "}\n"; }

funcao : SUBROTINA declaracao ABRE_PARENTESES parametros FECHA_PARENTESES ABRE_CHAVES comandos FECHA_CHAVES { $$ = $2 + "(" + $4 + ")" + "{\n " + $7 + "}\n"; }

parametros : declaracao { $$ = $1; }
           |            { $$ = ""; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao	{ $$ = $1; }
		     |					  { $$ = ""; }

declaracao : tipo IDENTIFICADOR declaracao	  { $$ = $1 + $2 + ";\n" + $3; }
		       |                                  { $$ = "";                        }

tipo : INTEIRO   { $$ = "int ";    }
     | REAL      { $$ = "double "; }
		 | CARACTERE { $$ = "char ";   }

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
