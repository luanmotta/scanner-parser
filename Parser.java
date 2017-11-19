//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "./src/CT.y"
	import java.io.*;
	import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short incluir=257;
public final static short definir=258;
public final static short main=259;
public final static short subrotina=260;
public final static short virgula=261;
public final static short ponto_e_virgula=262;
public final static short dois_pontos=263;
public final static short abre_chaves=264;
public final static short fecha_chaves=265;
public final static short abre_colchetes=266;
public final static short fecha_colchetes=267;
public final static short abre_parenteses=268;
public final static short fecha_parenteses=269;
public final static short atribuicao=270;
public final static short retornar=271;
public final static short se=272;
public final static short senao=273;
public final static short caso=274;
public final static short opcao=275;
public final static short fim_opcao=276;
public final static short enquanto=277;
public final static short para=278;
public final static short faca=279;
public final static short ate=280;
public final static short inteiro=281;
public final static short real=282;
public final static short caracter=283;
public final static short inclusao_arquivo=284;
public final static short valor_primitivo=285;
public final static short comentario=286;
public final static short operador=287;
public final static short comparador=288;
public final static short incdec=289;
public final static short identificador=290;
public final static short imprima=291;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    1,    2,    3,    4,
    5,    6,    7,    7,    7,    8,    9,    9,    9,   10,
   10,   11,   11,   11,   11,   11,   11,   12,   13,   14,
   15,   16,   16,   16,   17,   17,   17,   18,   18,   19,
   19,   19,   20,   21,   22,   22,   23,   23,   24,   25,
   26,   27,   27,   28,   28,   29,   29,   30,   31,   31,
   32,   33,   34,   35,   36,   37,   38,   38,   39,   39,
   40,   40,   41,   42,   43,   43,   43,   44,   45,   46,
   46,   47,   47,   47,   47,   48,   49,   49,   49,   50,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    2,    0,    2,    3,    2,
    4,    3,    1,    3,    0,    3,    2,    1,    0,    1,
    1,    1,    1,    1,    1,    1,    1,    2,    3,    2,
    3,    1,    3,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    4,    1,    1,    2,    0,    3,    3,
    3,    2,    1,    4,    3,    1,    1,    1,    2,    1,
    3,    3,    2,    3,    3,    5,    1,    0,    1,    0,
    1,    0,    3,    3,    1,    1,    2,    3,    2,    2,
    0,    1,    1,    1,    1,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    0,    8,    0,    0,   10,   87,   88,   89,    0,    0,
    6,    2,    3,    4,    5,    9,    0,    0,    0,    0,
    0,    0,   21,    0,   90,    0,    0,   20,   22,   23,
   24,   27,   35,   36,   37,   38,   39,   40,   41,   42,
   26,   25,    0,    0,   28,    0,   82,    0,   85,   43,
    0,   75,    0,   84,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   86,   30,   16,   17,    0,    0,
   11,    0,    0,   77,   79,    0,    0,   45,   46,    0,
    0,    0,   49,   61,    0,   67,    0,    0,   64,    0,
   62,    0,    0,   29,   12,    0,   78,   80,   73,    0,
    0,   44,   50,    0,    0,    0,   65,    0,   63,   31,
    0,   14,   74,   47,   57,   56,    0,   51,   52,    0,
   69,   33,    0,    0,   60,    0,   54,   58,   66,   71,
   59,
};
final static short yydgoto[] = {                          6,
    7,    8,    9,   10,   11,   54,   79,   15,   36,   37,
   38,   39,   40,   59,   76,  102,   42,   43,   44,   45,
   46,   90,  112,   47,   68,   93,  115,  116,  127,  137,
  138,   48,   49,  101,   50,   71,   97,   98,  130,  139,
   66,   86,   60,   61,   62,   84,   63,   64,   20,   52,
};
final static short yysindex[] = {                      -248,
 -269, -261, -243, -225, -248,    0,    0, -248, -248, -248,
 -248,    0, -241,  -28,    0,    0,    0,    0, -232, -245,
    0,    0,    0,    0,    0,    0, -255, -221, -219, -221,
 -218, -243,    0, -250,    0, -211,  -28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -225, -243,    0, -244,    0, -262,    0,    0,
 -227,    0, -227,    0, -244,  -92, -229, -202, -243, -226,
 -243, -217, -255, -255,    0,    0,    0,    0, -204, -195,
    0, -200, -255,    0,    0, -199, -216,    0,    0, -198,
 -196, -197,    0,    0, -191,    0, -188, -179,    0, -221,
    0, -184, -190,    0,    0, -225,    0,    0,    0, -244,
 -243,    0,    0, -242, -175, -197,    0, -244,    0,    0,
 -255,    0,    0,    0,    0,    0, -168,    0,    0, -163,
    0,    0,  -50, -255,    0,  -50,    0,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                       100,
    0,    0,    0,    0,  100,    0,    0,  100,  100,  100,
  100,    0,    0, -158,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -157,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -160,    0,    0,    0,    0, -185,    0,    0,
 -144,    0, -113,    0,    0,    0,    0,    0,    0, -152,
    0,    0, -156,    0,    0,    0,    0,    0,    0, -155,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -71,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -154,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -153,    0, -151,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -249, -145,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   23,    0,    0,    0,    0,    0,    0,  -29,   79, -114,
   60,   -2,   61,  -14,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   20,    0,    0,    0,
    4,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -26,   25,  -66,    0,  -51,   78,    0,  -13,    0,    0,
};
final static int YYTABLESIZE=263;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
   51,   19,   72,   69,   82,   73,  103,  104,    1,    2,
    3,    4,   56,   87,   12,   55,  108,   73,  136,   74,
   14,  136,   41,   51,   81,   55,   75,   21,   13,   57,
   22,   23,   24,   25,   58,   53,   88,    5,   75,   94,
   57,   99,  125,   26,   55,   58,   65,  126,   67,   70,
   80,   41,   51,   77,  132,   16,   17,   18,  123,   83,
   91,   92,  100,   95,  105,  106,   87,  140,  107,  109,
  121,  110,  113,  119,  111,   83,   83,  114,   74,   83,
  117,  124,  118,   83,  120,   83,   83,   83,   83,  128,
   83,   83,   83,   83,  133,   83,   83,   83,  134,    7,
   83,   83,   83,  122,   83,   83,   19,   18,   15,   68,
   70,   53,   34,   13,   32,   78,   76,   76,   41,   51,
   76,   41,   51,   72,   76,   89,   76,   76,   76,   76,
   96,   76,   76,   76,   76,  129,   76,   76,   76,  141,
   85,   76,  131,   76,    0,   76,   76,   81,   81,    0,
    0,   81,    0,    0,    0,   81,    0,   81,   81,   81,
   81,    0,   81,   81,   81,   81,    0,   81,   81,   81,
    0,   14,   81,    0,   81,    0,   81,   81,   27,   28,
    0,   29,    0,    0,   30,   31,   32,    0,   16,   17,
   18,    0,    0,   48,    0,    0,    0,   34,   35,   48,
   48,    0,   48,    0,   48,   48,   48,   48,    0,   48,
   48,   48,    0,    0,   48,    0,    0,    0,   48,   48,
   27,   28,    0,   29,    0,  135,   30,   31,   32,    0,
   16,   17,   18,    0,    0,   33,    0,    0,    0,   34,
   35,    0,   27,   28,    0,   29,    0,    0,   30,   31,
   32,    0,   16,   17,   18,    0,    0,   33,    0,    0,
    0,   34,   35,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         14,
   14,    4,   32,   30,   56,  268,   73,   74,  257,  258,
  259,  260,  268,   65,  284,  265,   83,  268,  133,  270,
  264,  136,   37,   37,   54,  275,  289,    5,  290,  285,
    8,    9,   10,   11,  290,  268,   66,  286,  289,   69,
  285,   71,  285,  285,  290,  290,  268,  290,  268,  268,
   53,   66,   66,  265,  121,  281,  282,  283,  110,  287,
  290,  264,  280,  290,  269,  261,  118,  134,  269,  269,
  261,  288,  269,  100,  273,  261,  262,  275,  270,  265,
  269,  111,  262,  269,  269,  271,  272,  273,  274,  265,
  276,  277,  278,  279,  263,  281,  282,  283,  262,    0,
  286,  287,  288,  106,  290,  291,  265,  265,  269,  262,
  262,  265,  269,  269,  269,   37,  261,  262,  133,  133,
  265,  136,  136,  269,  269,   66,  271,  272,  273,  274,
   70,  276,  277,  278,  279,  116,  281,  282,  283,  136,
   63,  286,  118,  288,   -1,  290,  291,  261,  262,   -1,
   -1,  265,   -1,   -1,   -1,  269,   -1,  271,  272,  273,
  274,   -1,  276,  277,  278,  279,   -1,  281,  282,  283,
   -1,  264,  286,   -1,  288,   -1,  290,  291,  271,  272,
   -1,  274,   -1,   -1,  277,  278,  279,   -1,  281,  282,
  283,   -1,   -1,  265,   -1,   -1,   -1,  290,  291,  271,
  272,   -1,  274,   -1,  276,  277,  278,  279,   -1,  281,
  282,  283,   -1,   -1,  286,   -1,   -1,   -1,  290,  291,
  271,  272,   -1,  274,   -1,  276,  277,  278,  279,   -1,
  281,  282,  283,   -1,   -1,  286,   -1,   -1,   -1,  290,
  291,   -1,  271,  272,   -1,  274,   -1,   -1,  277,  278,
  279,   -1,  281,  282,  283,   -1,   -1,  286,   -1,   -1,
   -1,  290,  291,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=291;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"incluir","definir","main","subrotina","virgula",
"ponto_e_virgula","dois_pontos","abre_chaves","fecha_chaves","abre_colchetes",
"fecha_colchetes","abre_parenteses","fecha_parenteses","atribuicao","retornar",
"se","senao","caso","opcao","fim_opcao","enquanto","para","faca","ate",
"inteiro","real","caracter","inclusao_arquivo","valor_primitivo","comentario",
"operador","comparador","incdec","identificador","imprima",
};
final static String yyrule[] = {
"$accept : INICIO",
"INICIO : PROGRAMA",
"PROGRAMA : INCLUSAO PROGRAMA",
"PROGRAMA : DEFINICAO PROGRAMA",
"PROGRAMA : MAIN PROGRAMA",
"PROGRAMA : SUBROTINA PROGRAMA",
"PROGRAMA : comentario PROGRAMA",
"PROGRAMA :",
"INCLUSAO : incluir inclusao_arquivo",
"DEFINICAO : definir identificador valor_primitivo",
"MAIN : main BLOCO",
"SUBROTINA : subrotina DECLARACAO PARAM BLOCO",
"PARAM : abre_parenteses PARAMS fecha_parenteses",
"PARAMS : DECLARACAO",
"PARAMS : DECLARACAO virgula DECLARACAO",
"PARAMS :",
"BLOCO : abre_chaves CONTEUDO fecha_chaves",
"CONTEUDO : LINHA CONTEUDO",
"CONTEUDO : LINHA",
"CONTEUDO :",
"LINHA : EXECUCAO",
"LINHA : comentario",
"EXECUCAO : DECLARACAO",
"EXECUCAO : ATRIBUICAO",
"EXECUCAO : CALL",
"EXECUCAO : IMPRIMIR",
"EXECUCAO : ID_INCDEC",
"EXECUCAO : COMANDO",
"DECLARACAO : TIPO identificador",
"ATRIBUICAO : identificador atribuicao EXPRESSAO",
"CALL : identificador CALL_PARAM",
"CALL_PARAM : abre_parenteses CALL_PARAMS fecha_parenteses",
"CALL_PARAMS : EXPRESSAO",
"CALL_PARAMS : EXPRESSAO virgula EXPRESSAO",
"CALL_PARAMS :",
"COMANDO : CONDICIONAL",
"COMANDO : LACO",
"COMANDO : RETURN",
"CONDICIONAL : IF",
"CONDICIONAL : SWITCH",
"LACO : WHILE",
"LACO : DO",
"LACO : FOR",
"RETURN : retornar EXPRESSAO",
"IF : se CONDICAO CAN_BLOCO ELSE",
"CAN_BLOCO : BLOCO",
"CAN_BLOCO : EXECUCAO",
"ELSE : senao BLOCO",
"ELSE :",
"SWITCH : caso SWITCH_ID SWITCH_BLOCO",
"SWITCH_ID : abre_parenteses identificador fecha_parenteses",
"SWITCH_BLOCO : abre_chaves SWITCH_OPCOES fecha_chaves",
"SWITCH_OPCOES : SWITCH_OPCAO SWITCH_OPCOES",
"SWITCH_OPCOES : SWITCH_OPCAO",
"SWITCH_OPCAO : opcao OPCAO_VALOR dois_pontos OPCAO_BLOCO",
"SWITCH_OPCAO : opcao OPCAO_VALOR dois_pontos",
"OPCAO_VALOR : identificador",
"OPCAO_VALOR : valor_primitivo",
"OPCAO_BLOCO : OPCAO_CONTEUDO",
"OPCAO_CONTEUDO : LINHA OPCAO_CONTEUDO",
"OPCAO_CONTEUDO : fim_opcao",
"WHILE : enquanto CONDICAO BLOCO",
"DO : faca BLOCO UNTIL",
"UNTIL : ate CONDICAO",
"FOR : para FOR_PARENTESES BLOCO",
"FOR_PARENTESES : abre_parenteses FOR_COMPOSICAO fecha_parenteses",
"FOR_COMPOSICAO : CAN_ATRIBUICAO ponto_e_virgula CAN_COMPARACAO ponto_e_virgula CAN_EXPRESSAO",
"CAN_ATRIBUICAO : ATRIBUICAO",
"CAN_ATRIBUICAO :",
"CAN_COMPARACAO : COMPARACAO",
"CAN_COMPARACAO :",
"CAN_EXPRESSAO : EXPRESSAO",
"CAN_EXPRESSAO :",
"CONDICAO : abre_parenteses COMPARACAO fecha_parenteses",
"COMPARACAO : EXP comparador EXP",
"EXPRESSAO : EXP",
"EXPRESSAO : PARENTESES_EXP",
"EXPRESSAO : PARENTESES_EXP OPERACAO",
"PARENTESES_EXP : abre_parenteses EXP fecha_parenteses",
"EXP : VALOR OPERACAO",
"OPERACAO : operador EXPRESSAO",
"OPERACAO :",
"VALOR : valor_primitivo",
"VALOR : identificador",
"VALOR : ID_INCDEC",
"VALOR : CALL",
"ID_INCDEC : identificador incdec",
"TIPO : inteiro",
"TIPO : real",
"TIPO : caracter",
"IMPRIMIR : imprima",
};

//#line 315 "./src/CT.y"
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
//#line 435 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 114 "./src/CT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 117 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 118 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 119 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 120 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 121 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 7:
//#line 122 "./src/CT.y"
{yyval.sval=    ""               ;}
break;
case 8:
//#line 125 "./src/CT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 9:
//#line 128 "./src/CT.y"
{yyval.sval=    "#define " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 10:
//#line 131 "./src/CT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 11:
//#line 134 "./src/CT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 12:
//#line 137 "./src/CT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 13:
//#line 140 "./src/CT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 14:
//#line 141 "./src/CT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 15:
//#line 142 "./src/CT.y"
{yyval.sval=    ""                 ;}
break;
case 16:
//#line 145 "./src/CT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 17:
//#line 148 "./src/CT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 18:
//#line 149 "./src/CT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 19:
//#line 150 "./src/CT.y"
{yyval.sval=   ""                     ;}
break;
case 20:
//#line 153 "./src/CT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 21:
//#line 154 "./src/CT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 22:
//#line 157 "./src/CT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 23:
//#line 158 "./src/CT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 159 "./src/CT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 25:
//#line 160 "./src/CT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 26:
//#line 161 "./src/CT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 27:
//#line 162 "./src/CT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 28:
//#line 165 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 29:
//#line 168 "./src/CT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 30:
//#line 171 "./src/CT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 31:
//#line 174 "./src/CT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 32:
//#line 177 "./src/CT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 33:
//#line 178 "./src/CT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 34:
//#line 179 "./src/CT.y"
{yyval.sval=    ""                 ;}
break;
case 35:
//#line 182 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 36:
//#line 183 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 37:
//#line 184 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 38:
//#line 187 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 39:
//#line 188 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 40:
//#line 190 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 41:
//#line 191 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 42:
//#line 192 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 43:
//#line 195 "./src/CT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 44:
//#line 198 "./src/CT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 45:
//#line 201 "./src/CT.y"
{yyval.sval=    val_peek(0).sval        ;}
break;
case 46:
//#line 202 "./src/CT.y"
{yyval.sval=   " " + val_peek(0).sval   ;}
break;
case 47:
//#line 205 "./src/CT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 48:
//#line 206 "./src/CT.y"
{yyval.sval=   ""             ;}
break;
case 49:
//#line 209 "./src/CT.y"
{yyval.sval=  "switch " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 50:
//#line 212 "./src/CT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 51:
//#line 215 "./src/CT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 52:
//#line 218 "./src/CT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 53:
//#line 219 "./src/CT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 54:
//#line 222 "./src/CT.y"
{yyval.sval=   "case " + val_peek(2).sval + ":\n" + val_peek(0).sval   ;}
break;
case 55:
//#line 223 "./src/CT.y"
{yyval.sval=   "case " + val_peek(1).sval + ":\n"        ;}
break;
case 56:
//#line 226 "./src/CT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 57:
//#line 227 "./src/CT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 58:
//#line 230 "./src/CT.y"
{yyval.sval=    "{\n" + val_peek(0).sval + "}\n"   ;}
break;
case 59:
//#line 233 "./src/CT.y"
{yyval.sval=   val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 60:
//#line 234 "./src/CT.y"
{yyval.sval=   "break;\n"         ;}
break;
case 61:
//#line 236 "./src/CT.y"
{yyval.sval=  "while " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 62:
//#line 239 "./src/CT.y"
{yyval.sval=   "do " + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 63:
//#line 242 "./src/CT.y"
{yyval.sval=   "while " + val_peek(0).sval + ";\n"  ;}
break;
case 64:
//#line 245 "./src/CT.y"
{yyval.sval=  "for " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 65:
//#line 248 "./src/CT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 66:
//#line 251 "./src/CT.y"
{yyval.sval= val_peek(4).sval + " ; " + val_peek(2).sval + " ; " + val_peek(0).sval  ;}
break;
case 67:
//#line 254 "./src/CT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 68:
//#line 255 "./src/CT.y"
{yyval.sval=   ""   ;}
break;
case 69:
//#line 258 "./src/CT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 70:
//#line 259 "./src/CT.y"
{yyval.sval=   ""   ;}
break;
case 71:
//#line 262 "./src/CT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 72:
//#line 263 "./src/CT.y"
{yyval.sval=   ""   ;}
break;
case 73:
//#line 266 "./src/CT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 74:
//#line 269 "./src/CT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 75:
//#line 272 "./src/CT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 76:
//#line 273 "./src/CT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 77:
//#line 274 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 78:
//#line 277 "./src/CT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 79:
//#line 280 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 80:
//#line 283 "./src/CT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 81:
//#line 284 "./src/CT.y"
{yyval.sval=        ""                 ;}
break;
case 82:
//#line 286 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 83:
//#line 287 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 84:
//#line 288 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 85:
//#line 289 "./src/CT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 86:
//#line 292 "./src/CT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval   ;}
break;
case 87:
//#line 295 "./src/CT.y"
{yyval.sval=    "int "      ;}
break;
case 88:
//#line 296 "./src/CT.y"
{yyval.sval=    "double "   ;}
break;
case 89:
//#line 297 "./src/CT.y"
{yyval.sval=    "char "     ;}
break;
case 90:
//#line 300 "./src/CT.y"
{yyval.sval=   "printf" + val_peek(0).sval.substring(7, val_peek(0).sval.length())   ;}
break;
//#line 944 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
