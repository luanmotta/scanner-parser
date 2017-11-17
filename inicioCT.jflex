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

":="             { return Parser.atribuicao;       }

retornar         { return Parser.retornar;         }

inteiro          { return Parser.inteiro;          }
real             { return Parser.real;             }
caracter         { return Parser.caracter;         }


\<.*\>	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.inclusao_arquivo;
}

([0-9]+)|'.*' {
	yyparser.yylval = new ParserVal(yytext());
	return Parser.valor_primitivo;
}

\/\/.*|\/\*.*\*\/ {
	yyparser.yylval = new ParserVal(yytext());
	return Parser.comentario;
}

[\/\*\-\+] {
	yyparser.yylval = new ParserVal(yytext());
	return Parser.operador;
}

[a-zA-Z_][_a-zA-Z0-9\[\]]*	{
	yyparser.yylval = new ParserVal(yytext());
	return Parser.identificador;
}


{NL}|" "|\t	{  }
