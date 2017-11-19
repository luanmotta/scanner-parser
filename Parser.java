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






//#line 3 "inicioCT.y"
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
public final static short main=258;
public final static short subrotina=259;
public final static short virgula=260;
public final static short ponto_e_virgula=261;
public final static short dois_pontos=262;
public final static short abre_chaves=263;
public final static short fecha_chaves=264;
public final static short abre_colchetes=265;
public final static short fecha_colchetes=266;
public final static short abre_parenteses=267;
public final static short fecha_parenteses=268;
public final static short atribuicao=269;
public final static short retornar=270;
public final static short se=271;
public final static short senao=272;
public final static short caso=273;
public final static short opcao=274;
public final static short fim_opcao=275;
public final static short enquanto=276;
public final static short para=277;
public final static short faca=278;
public final static short ate=279;
public final static short inteiro=280;
public final static short real=281;
public final static short caracter=282;
public final static short inclusao_arquivo=283;
public final static short valor_primitivo=284;
public final static short comentario=285;
public final static short operador=286;
public final static short comparador=287;
public final static short incdec=288;
public final static short identificador=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    4,    5,
    6,    6,    6,    7,    8,    8,    8,    9,    9,   10,
   10,   10,   10,   10,   11,   12,   13,   14,   15,   15,
   15,   16,   16,   16,   17,   17,   18,   18,   18,   19,
   20,   21,   21,   22,   22,   23,   24,   25,   26,   26,
   27,   27,   28,   28,   29,   30,   30,   31,   32,   33,
   34,   35,   36,   37,   37,   38,   38,   39,   39,   40,
   41,   42,   42,   42,   43,   44,   45,   45,   46,   46,
   46,   46,   47,   48,   48,   48,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    3,    0,    3,    2,    1,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    3,    2,    3,    1,    3,
    0,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    4,    1,    1,    2,    0,    3,    3,    3,    2,    1,
    4,    3,    1,    1,    1,    2,    1,    3,    3,    2,
    3,    3,    5,    1,    0,    1,    0,    1,    0,    3,
    3,    1,    1,    2,    3,    2,    2,    0,    1,    1,
    1,    1,    2,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   84,   85,   86,    0,    0,    5,    2,    3,
    4,    0,    0,    0,    0,    0,    0,   19,    0,    0,
    0,   18,   20,   21,   22,   24,   32,   33,   34,   35,
   36,   37,   38,   39,   23,    0,    0,   25,    0,   79,
    0,   82,   40,    0,   72,    0,   81,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   83,   27,   14,
   15,    0,    0,    9,    0,    0,   74,   76,    0,    0,
   42,   43,    0,    0,    0,   46,   58,    0,   64,    0,
    0,   61,    0,   59,    0,    0,   26,   10,    0,   75,
   77,   70,    0,    0,   41,   47,    0,    0,    0,   62,
    0,   60,   28,    0,   12,   71,   44,   54,   53,    0,
   48,   49,    0,   66,   30,    0,    0,   57,    0,   51,
   55,   63,   68,   56,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   47,   72,   12,   30,   31,   32,
   33,   34,   52,   69,   95,   36,   37,   38,   39,   40,
   83,  105,   41,   61,   86,  108,  109,  120,  130,  131,
   42,   43,   94,   44,   64,   90,   91,  123,  132,   59,
   79,   53,   54,   55,   77,   56,   57,   17,
};
final static short yysindex[] = {                      -248,
 -261, -237, -217, -248,    0,    0, -248, -248, -248,    0,
  -63,    0,    0,    0,    0, -226, -259,    0,    0,    0,
    0, -255, -223, -215, -223, -213, -237,    0, -242, -198,
  -63,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -217, -237,    0, -271,    0,
 -252,    0,    0, -239,    0, -239,    0, -271, -220, -219,
 -192, -237, -216, -237, -203, -255, -255,    0,    0,    0,
    0, -191, -181,    0, -187, -255,    0,    0, -185, -199,
    0,    0, -179, -171, -172,    0,    0, -165,    0, -163,
 -155,    0, -223,    0, -161, -152,    0,    0, -217,    0,
    0,    0, -271, -237,    0,    0, -265, -154, -172,    0,
 -271,    0,    0, -255,    0,    0,    0,    0,    0, -153,
    0,    0, -150,    0,    0,  -80, -255,    0,  -80,    0,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                       112,
    0,    0,    0,  112,    0,    0,  112,  112,  112,    0,
 -144,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -142,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -141,    0,    0,    0,    0,
 -186,    0,    0, -147,    0, -117,    0,    0,    0,    0,
    0,    0, -129,    0,    0, -132,    0,    0,    0,    0,
    0,    0, -131,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -127,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -125,    0,
 -116,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -258, -122,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   24,    0,    0,    0,    0,    0,  -24,  117,  -87,   90,
   -1,   87,  -11,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   43,    0,    0,    0,   28,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -21,
   51,  -59,    0,  -44,  110,    0,  -10,    0,
};
final static int YYTABLESIZE=226;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   45,   16,   65,   62,   75,   52,   96,   97,    1,    2,
    3,   49,   50,   80,   66,   52,  101,   51,  118,   35,
   45,   10,   74,  119,   66,   11,   67,   18,   50,   48,
   19,   20,   21,   51,   81,   68,    4,   87,  129,   92,
   46,  129,   11,   58,   73,   68,   76,   35,   45,   22,
   23,   60,   24,   63,  125,   25,   26,   27,  116,   13,
   14,   15,   13,   14,   15,   70,   80,  133,   29,   84,
   85,  112,   88,   80,   80,   93,   98,   80,   99,  117,
  100,   80,  102,   80,   80,   80,   80,  103,   80,   80,
   80,   80,  104,   80,   80,   80,  106,  115,   80,   80,
   80,  107,   80,   67,  110,  111,  113,  114,  126,  121,
  127,    6,   73,   73,   35,   45,   73,   35,   45,   17,
   73,   16,   73,   73,   73,   73,   13,   73,   73,   73,
   73,   65,   73,   73,   73,   31,   11,   73,   50,   73,
   29,   73,   78,   78,   67,   69,   78,   71,   82,   89,
   78,  122,   78,   78,   78,   78,  134,   78,   78,   78,
   78,  124,   78,   78,   78,   78,   45,   78,    0,   78,
    0,   78,   45,   45,    0,   45,    0,   45,   45,   45,
   45,    0,   45,   45,   45,    0,    0,   45,    0,   22,
   23,   45,   24,    0,  128,   25,   26,   27,    0,   13,
   14,   15,    0,    0,   28,    0,   22,   23,   29,   24,
    0,    0,   25,   26,   27,    0,   13,   14,   15,    0,
    0,   28,    0,    0,    0,   29,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         11,
   11,    3,   27,   25,   49,  264,   66,   67,  257,  258,
  259,  267,  284,   58,  267,  274,   76,  289,  284,   31,
   31,  283,   47,  289,  267,  263,  269,    4,  284,  289,
    7,    8,    9,  289,   59,  288,  285,   62,  126,   64,
  267,  129,  263,  267,   46,  288,  286,   59,   59,  270,
  271,  267,  273,  267,  114,  276,  277,  278,  103,  280,
  281,  282,  280,  281,  282,  264,  111,  127,  289,  289,
  263,   93,  289,  260,  261,  279,  268,  264,  260,  104,
  268,  268,  268,  270,  271,  272,  273,  287,  275,  276,
  277,  278,  272,  280,  281,  282,  268,   99,  285,  286,
  287,  274,  289,  269,  268,  261,  268,  260,  262,  264,
  261,    0,  260,  261,  126,  126,  264,  129,  129,  264,
  268,  264,  270,  271,  272,  273,  268,  275,  276,  277,
  278,  261,  280,  281,  282,  268,  268,  285,  264,  287,
  268,  289,  260,  261,  261,  268,  264,   31,   59,   63,
  268,  109,  270,  271,  272,  273,  129,  275,  276,  277,
  278,  111,  280,  281,  282,   56,  264,  285,   -1,  287,
   -1,  289,  270,  271,   -1,  273,   -1,  275,  276,  277,
  278,   -1,  280,  281,  282,   -1,   -1,  285,   -1,  270,
  271,  289,  273,   -1,  275,  276,  277,  278,   -1,  280,
  281,  282,   -1,   -1,  285,   -1,  270,  271,  289,  273,
   -1,   -1,  276,  277,  278,   -1,  280,  281,  282,   -1,
   -1,  285,   -1,   -1,   -1,  289,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=289;
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
null,null,null,"incluir","main","subrotina","virgula","ponto_e_virgula",
"dois_pontos","abre_chaves","fecha_chaves","abre_colchetes","fecha_colchetes",
"abre_parenteses","fecha_parenteses","atribuicao","retornar","se","senao",
"caso","opcao","fim_opcao","enquanto","para","faca","ate","inteiro","real",
"caracter","inclusao_arquivo","valor_primitivo","comentario","operador",
"comparador","incdec","identificador",
};
final static String yyrule[] = {
"$accept : INICIO",
"INICIO : PROGRAMA",
"PROGRAMA : INCLUSAO PROGRAMA",
"PROGRAMA : MAIN PROGRAMA",
"PROGRAMA : SUBROTINA PROGRAMA",
"PROGRAMA : comentario PROGRAMA",
"PROGRAMA :",
"INCLUSAO : incluir inclusao_arquivo",
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
};

//#line 306 "inicioCT.y"
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
//#line 418 "Parser.java"
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
//#line 110 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 113 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 114 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 115 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 116 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 117 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 120 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 123 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 126 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 129 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 132 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 12:
//#line 133 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 13:
//#line 134 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 14:
//#line 137 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 15:
//#line 140 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 16:
//#line 141 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 17:
//#line 142 "inicioCT.y"
{yyval.sval=   ""                     ;}
break;
case 18:
//#line 145 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 19:
//#line 146 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 20:
//#line 149 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 150 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 22:
//#line 151 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 23:
//#line 152 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 153 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 25:
//#line 156 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 26:
//#line 159 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 27:
//#line 162 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 28:
//#line 165 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 29:
//#line 168 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 30:
//#line 169 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 31:
//#line 170 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 32:
//#line 173 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 174 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 34:
//#line 175 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 35:
//#line 178 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 36:
//#line 179 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 37:
//#line 181 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 38:
//#line 182 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 39:
//#line 183 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 40:
//#line 186 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 41:
//#line 189 "inicioCT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 42:
//#line 192 "inicioCT.y"
{yyval.sval=    val_peek(0).sval        ;}
break;
case 43:
//#line 193 "inicioCT.y"
{yyval.sval=   " " + val_peek(0).sval   ;}
break;
case 44:
//#line 196 "inicioCT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 45:
//#line 197 "inicioCT.y"
{yyval.sval=   ""             ;}
break;
case 46:
//#line 200 "inicioCT.y"
{yyval.sval=  "switch " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 47:
//#line 203 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 48:
//#line 206 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 49:
//#line 209 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 50:
//#line 210 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 51:
//#line 213 "inicioCT.y"
{yyval.sval=   "case " + val_peek(2).sval + ":\n" + val_peek(0).sval   ;}
break;
case 52:
//#line 214 "inicioCT.y"
{yyval.sval=   "case " + val_peek(1).sval + ":\n"        ;}
break;
case 53:
//#line 217 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 54:
//#line 218 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 55:
//#line 221 "inicioCT.y"
{yyval.sval=    "{\n" + val_peek(0).sval + "}\n"   ;}
break;
case 56:
//#line 224 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 57:
//#line 225 "inicioCT.y"
{yyval.sval=   "break;\n"         ;}
break;
case 58:
//#line 227 "inicioCT.y"
{yyval.sval=  "while " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 59:
//#line 230 "inicioCT.y"
{yyval.sval=   "do " + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 60:
//#line 233 "inicioCT.y"
{yyval.sval=   "while " + val_peek(0).sval + ";\n"  ;}
break;
case 61:
//#line 236 "inicioCT.y"
{yyval.sval=  "for " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 62:
//#line 239 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 63:
//#line 242 "inicioCT.y"
{yyval.sval= val_peek(4).sval + " ; " + val_peek(2).sval + " ; " + val_peek(0).sval  ;}
break;
case 64:
//#line 245 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 65:
//#line 246 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 66:
//#line 249 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 67:
//#line 250 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 68:
//#line 253 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 69:
//#line 254 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 70:
//#line 257 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 71:
//#line 260 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 72:
//#line 263 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 73:
//#line 264 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 74:
//#line 265 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 75:
//#line 268 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 76:
//#line 271 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 77:
//#line 274 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 78:
//#line 275 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 79:
//#line 277 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 80:
//#line 278 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 81:
//#line 279 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 82:
//#line 280 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 83:
//#line 283 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval   ;}
break;
case 84:
//#line 286 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 85:
//#line 287 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 86:
//#line 288 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 911 "Parser.java"
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
