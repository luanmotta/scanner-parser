import java.io.*;

%%

%byaccj

%{

	// Armazena uma referencia para o parser
	private Parser yyparser;

	// Construtor recebendo o parser como parametro adicional
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}

%}

NL = \n | \r | \r\n

%%

incluir	         { return Parser.INCLUIR;          }

funcao_principal { return Parser.FUNCAO_PRINCIPAL; }
funcao           { return Parser.SUBROTINA;        }

"{"	             { return Parser.ABRE_CHAVES;      }
"}"              { return Parser.FECHA_CHAVES;     }
"["	             { return Parser.ABRE_COLCHETES;   }
"]"              { return Parser.FECHA_COLCHETES;  }
"("	             { return Parser.ABRE_PARENTESES;  }
")"              { return Parser.FECHA_PARENTESES; }

inteiro          { return Parser.INTEIRO;          }
real             { return Parser.REAL;             }
caracter         { return Parser.CARACTERE;        }

\<.*\>	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.INCLUSAO_ARQUIVO;
}

[a-zA-Z_][_a-zA-Z0-9]*	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.IDENTIFICADOR;
}

{NL}|" "|\t	{  }
