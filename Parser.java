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
   20,   21,   21,   22,   23,   24,   25,   25,   26,   26,
   27,   27,   28,   28,   29,   30,   31,   32,   33,   34,
   35,   35,   36,   36,   37,   37,   38,   39,   40,   40,
   40,   41,   42,   43,   43,   44,   44,   44,   44,   45,
   46,   46,   46,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    3,    0,    3,    2,    1,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    3,    2,    3,    1,    3,
    0,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    4,    2,    0,    3,    3,    3,    2,    1,    4,    3,
    1,    1,    2,    1,    3,    3,    2,    3,    3,    5,
    1,    0,    1,    0,    1,    0,    3,    3,    1,    1,
    2,    3,    2,    2,    0,    1,    1,    1,    1,    2,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   81,   82,   83,    0,    0,    5,    2,    3,
    4,    0,    0,    0,    0,    0,    0,   19,    0,    0,
    0,   18,   20,   21,   22,   24,   32,   33,   34,   35,
   36,   37,   38,   39,   23,    0,    0,   25,    0,   76,
    0,   79,   40,    0,   69,    0,   78,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   80,   27,   14,
   15,    0,    0,    9,    0,    0,   71,   73,    0,    0,
    0,    0,    0,   44,   55,    0,   61,    0,    0,   58,
    0,   56,    0,    0,   26,   10,    0,   72,   74,   67,
    0,    0,   41,   45,    0,    0,    0,   59,    0,   57,
   28,    0,   12,   68,   42,   52,   51,    0,   46,   47,
    0,   63,   30,    0,    0,   54,    0,   49,   60,   65,
   53,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   47,   72,   12,   30,   31,   32,
   33,   34,   52,   69,   93,   36,   37,   38,   39,   40,
  103,   41,   61,   84,  106,  107,  118,  128,   42,   43,
   92,   44,   64,   88,   89,  121,  129,   59,   79,   53,
   54,   55,   77,   56,   57,   17,
};
final static short yysindex[] = {                      -248,
 -257, -233, -178, -248,    0,    0, -248, -248, -248,    0,
 -222,    0,    0,    0,    0, -231, -250,    0,    0,    0,
    0, -255, -225, -223, -225, -220, -233,    0, -245, -214,
 -222,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -178, -233,    0, -271,    0,
 -261,    0,    0, -234,    0, -234,    0, -271, -233, -228,
 -201, -233, -221, -233, -215, -255, -255,    0,    0,    0,
    0, -199, -189,    0, -194, -255,    0,    0, -193, -210,
 -191, -184, -195,    0,    0, -183,    0, -177, -166,    0,
 -225,    0, -168, -155,    0,    0, -178,    0,    0,    0,
 -271, -233,    0,    0, -243, -158, -195,    0, -271,    0,
    0, -255,    0,    0,    0,    0,    0, -154,    0,    0,
 -152,    0,    0,  -82, -255,    0,  -82,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                       107,
    0,    0,    0,  107,    0,    0,  107,  107,  107,    0,
 -146,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -144,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -145,    0,    0,    0,    0,
 -188,    0,    0, -149,    0, -119,    0,    0,    0,    0,
    0,    0, -151,    0,    0, -143,    0,    0,    0,    0,
    0,    0, -138,    0,    0,    0,    0,    0,    0,    0,
  -99,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -134,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -129,    0, -124,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -249, -125,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   24,    0,    0,    0,    0,    0,  -24,  108, -108,    0,
   -1,   81,  -11,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   39,    0,    0,   20,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -21,   41,  -59,
    0,  -44,   92,    0,  -10,    0,
};
final static int YYTABLESIZE=207;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   45,   16,   65,   62,   75,   66,   94,   95,    1,    2,
    3,   49,   50,   80,   50,  127,   99,   51,  127,   35,
   45,   66,   74,   67,   50,   10,   68,   18,   50,   11,
   19,   20,   21,   51,   81,   46,    4,   85,   48,   90,
  116,   58,   68,   60,   73,  117,   63,   22,   23,   70,
   24,   76,  123,   25,   26,   27,  114,   13,   14,   15,
   82,   83,   28,   91,   80,  130,   29,   86,   96,  110,
   97,   77,   77,   98,  100,   77,  101,  115,  105,   77,
  102,   77,   77,  104,   77,   67,   77,   77,   77,   77,
  108,   77,   77,   77,  109,  113,   77,   77,   77,  111,
   77,   13,   14,   15,  112,  119,    6,  124,  125,   62,
   70,   70,   35,   45,   70,   35,   45,   17,   70,   16,
   70,   70,   13,   70,   31,   70,   70,   70,   70,   11,
   70,   70,   70,   29,   48,   70,   64,   70,   71,   70,
   75,   75,   66,   87,   75,  120,  131,   78,   75,  122,
   75,   75,    0,   75,    0,   75,   75,   75,   75,    0,
   75,   75,   75,    0,   43,   75,    0,   75,    0,   75,
   43,   43,    0,   43,    0,   43,   43,   43,   43,    0,
   43,   43,   43,    0,    0,   43,    0,   22,   23,   43,
   24,    0,  126,   25,   26,   27,    0,   13,   14,   15,
    0,    0,   28,    0,    0,    0,   29,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         11,
   11,    3,   27,   25,   49,  267,   66,   67,  257,  258,
  259,  267,  284,   58,  264,  124,   76,  289,  127,   31,
   31,  267,   47,  269,  274,  283,  288,    4,  284,  263,
    7,    8,    9,  289,   59,  267,  285,   62,  289,   64,
  284,  267,  288,  267,   46,  289,  267,  270,  271,  264,
  273,  286,  112,  276,  277,  278,  101,  280,  281,  282,
  289,  263,  285,  279,  109,  125,  289,  289,  268,   91,
  260,  260,  261,  268,  268,  264,  287,  102,  274,  268,
  272,  270,  271,  268,  273,  269,  275,  276,  277,  278,
  268,  280,  281,  282,  261,   97,  285,  286,  287,  268,
  289,  280,  281,  282,  260,  264,    0,  262,  261,  261,
  260,  261,  124,  124,  264,  127,  127,  264,  268,  264,
  270,  271,  268,  273,  268,  275,  276,  277,  278,  268,
  280,  281,  282,  268,  264,  285,  261,  287,   31,  289,
  260,  261,  268,   63,  264,  107,  127,   56,  268,  109,
  270,  271,   -1,  273,   -1,  275,  276,  277,  278,   -1,
  280,  281,  282,   -1,  264,  285,   -1,  287,   -1,  289,
  270,  271,   -1,  273,   -1,  275,  276,  277,  278,   -1,
  280,  281,  282,   -1,   -1,  285,   -1,  270,  271,  289,
  273,   -1,  275,  276,  277,  278,   -1,  280,  281,  282,
   -1,   -1,  285,   -1,   -1,   -1,  289,
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
"IF : se CONDICAO BLOCO ELSE",
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
"OPCAO_BLOCO : LINHA OPCAO_BLOCO",
"OPCAO_BLOCO : fim_opcao",
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

//#line 298 "inicioCT.y"
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
//#line 411 "Parser.java"
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
//#line 108 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 111 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 112 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 113 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 114 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 115 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 118 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 121 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 124 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 127 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 130 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 12:
//#line 131 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 13:
//#line 132 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 14:
//#line 135 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 15:
//#line 138 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 16:
//#line 139 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 17:
//#line 140 "inicioCT.y"
{yyval.sval=   ""                     ;}
break;
case 18:
//#line 143 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 19:
//#line 144 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 20:
//#line 147 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 148 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 22:
//#line 149 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 23:
//#line 150 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 151 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 25:
//#line 154 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 26:
//#line 157 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 27:
//#line 160 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 28:
//#line 163 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 29:
//#line 166 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 30:
//#line 167 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 31:
//#line 168 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 32:
//#line 171 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 172 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 34:
//#line 173 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 35:
//#line 176 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 36:
//#line 177 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 37:
//#line 179 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 38:
//#line 180 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 39:
//#line 181 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 40:
//#line 184 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 41:
//#line 187 "inicioCT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 42:
//#line 190 "inicioCT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 43:
//#line 191 "inicioCT.y"
{yyval.sval=   ""             ;}
break;
case 44:
//#line 194 "inicioCT.y"
{yyval.sval=  "switch " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 45:
//#line 197 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 46:
//#line 200 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 47:
//#line 203 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 48:
//#line 204 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 49:
//#line 207 "inicioCT.y"
{yyval.sval=   "case " + val_peek(2).sval + ":\n" + val_peek(0).sval   ;}
break;
case 50:
//#line 208 "inicioCT.y"
{yyval.sval=   "case " + val_peek(1).sval + ":\n"        ;}
break;
case 51:
//#line 211 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 52:
//#line 212 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 53:
//#line 215 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 54:
//#line 216 "inicioCT.y"
{yyval.sval=   "break;"         ;}
break;
case 55:
//#line 219 "inicioCT.y"
{yyval.sval=  "while " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 56:
//#line 222 "inicioCT.y"
{yyval.sval=   "do " + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 57:
//#line 225 "inicioCT.y"
{yyval.sval=   "while " + val_peek(0).sval + ";\n"  ;}
break;
case 58:
//#line 228 "inicioCT.y"
{yyval.sval=  "for " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 59:
//#line 231 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 60:
//#line 234 "inicioCT.y"
{yyval.sval= val_peek(4).sval + " ; " + val_peek(2).sval + " ; " + val_peek(0).sval  ;}
break;
case 61:
//#line 237 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 62:
//#line 238 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 63:
//#line 241 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 64:
//#line 242 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 65:
//#line 245 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 66:
//#line 246 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 67:
//#line 249 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 68:
//#line 252 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 69:
//#line 255 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 70:
//#line 256 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 71:
//#line 257 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 72:
//#line 260 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 73:
//#line 263 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 74:
//#line 266 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 75:
//#line 267 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 76:
//#line 269 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 77:
//#line 270 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 78:
//#line 271 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 79:
//#line 272 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 80:
//#line 275 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval   ;}
break;
case 81:
//#line 278 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 82:
//#line 279 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 83:
//#line 280 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 892 "Parser.java"
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
