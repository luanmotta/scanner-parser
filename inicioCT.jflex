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

incluir	         { return Parser.incluir;          }

funcao_principal { return Parser.main;             }

"{"	             { return Parser.abre_chaves;      }
"}"              { return Parser.fecha_chaves;     }
"["	             { return Parser.abre_colchetes;   }
"]"              { return Parser.fecha_colchetes;  }
"("	             { return Parser.abre_parenteses;  }
")"              { return Parser.fecha_parenteses; }

inteiro          { return Parser.inteiro;          }
real             { return Parser.real;             }
caracter         { return Parser.caractere;        }


\<.*\>	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.inclusao_arquivo;
}

[a-zA-Z_][_a-zA-Z0-9]*	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.identificador;
}


{NL}|" "|\t	{  }
