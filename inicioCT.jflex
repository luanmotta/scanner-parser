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

funcao_principal { return Parser.FUNCAO_PRINCIPAL; }
incluir	{ return Parser.INCLUIR; }
inteiro { return Parser.INTEIRO; }
\<.*\>	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.INCLUSAO_ARQUIVO; }
"{"	{ return Parser.ABRE_CHAVES; }
"}" { return Parser.FECHA_CHAVES; }
[a-zA-Z][a-zA-Z0-9]*	{ 
		yyparser.yylval = new ParserVal(yytext());
		return Parser.IDENTIFICADOR;
	}
{NL}|" "|\t	{  }
