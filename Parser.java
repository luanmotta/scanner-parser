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
public final static short enquanto=275;
public final static short para=276;
public final static short faca=277;
public final static short ate=278;
public final static short inteiro=279;
public final static short real=280;
public final static short caracter=281;
public final static short inclusao_arquivo=282;
public final static short valor_primitivo=283;
public final static short comentario=284;
public final static short operador=285;
public final static short comparador=286;
public final static short incdec=287;
public final static short identificador=288;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    4,    5,
    6,    6,    6,    7,    8,    8,    8,    9,    9,   10,
   10,   10,   10,   10,   11,   12,   13,   14,   15,   15,
   15,   16,   16,   16,   17,   17,   18,   18,   18,   19,
   20,   21,   21,   22,   23,   24,   25,   25,   26,   27,
   28,   29,   30,   31,   32,   33,   33,   34,   34,   35,
   35,   36,   37,   38,   38,   38,   39,   40,   41,   41,
   42,   42,   42,   42,   43,   44,   44,   44,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    3,    0,    3,    2,    1,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    3,    2,    3,    1,    3,
    0,    1,    1,    1,    1,    1,    1,    1,    1,    2,
    4,    2,    0,    3,    3,    3,    2,    1,    3,    3,
    3,    2,    3,    3,    5,    1,    0,    1,    0,    1,
    0,    3,    3,    1,    1,    2,    3,    2,    2,    0,
    1,    1,    1,    1,    2,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   76,   77,   78,    0,    0,    5,    2,    3,
    4,    0,    0,    0,    0,    0,    0,   19,    0,    0,
    0,   18,   20,   21,   22,   24,   32,   33,   34,   35,
   36,   37,   38,   39,   23,    0,    0,   25,    0,   71,
    0,   74,   40,    0,   64,    0,   73,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   75,   27,   14,
   15,    0,    0,    9,    0,    0,   66,   68,    0,    0,
    0,    0,    0,   44,   50,    0,   56,    0,    0,   53,
    0,   51,    0,    0,   26,   10,    0,   67,   69,   62,
    0,    0,   41,   45,    0,    0,    0,   54,    0,   52,
   28,    0,   12,   63,   42,    0,   46,   47,    0,   58,
   30,   49,    0,   55,   60,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   47,   72,   12,   30,   31,   32,
   33,   34,   52,   69,   93,   36,   37,   38,   39,   40,
  103,   41,   61,   84,  106,  107,   42,   43,   92,   44,
   64,   88,   89,  119,  124,   59,   79,   53,   54,   55,
   77,   56,   57,   17,
};
final static short yysindex[] = {                      -250,
 -276, -248, -223, -250,    0,    0, -250, -250, -250,    0,
 -112,    0,    0,    0,    0, -254, -269,    0,    0,    0,
    0, -251, -227, -221, -227, -213, -248,    0, -228, -220,
 -112,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -223, -248,    0, -241,    0,
 -257,    0,    0, -225,    0, -225,    0, -241, -248, -226,
 -199, -248, -222, -248, -211, -251, -251,    0,    0,    0,
    0, -203, -191,    0, -198, -251,    0,    0, -195, -212,
 -194, -188, -197,    0,    0, -186,    0, -183, -172,    0,
 -227,    0, -175, -162,    0,    0, -223,    0,    0,    0,
 -241, -248,    0,    0, -181, -161, -197,    0, -241,    0,
    0, -251,    0,    0,    0, -157,    0,    0, -155,    0,
    0,    0, -251,    0,    0,
};
final static short yyrindex[] = {                       107,
    0,    0,    0,  107,    0,    0,  107,  107,  107,    0,
 -152,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -150,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -159,    0,    0,    0,    0,
 -189,    0,    0, -160,    0, -131,    0,    0,    0,    0,
    0,    0, -143,    0,    0, -146,    0,    0,    0,    0,
    0,    0, -145,    0,    0,    0,    0,    0,    0,    0,
 -253,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -141,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -139,    0, -130,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -136,    0,    0,
};
final static short yygindex[] = {                         0,
   44,    0,    0,    0,    0,    0,  -26,  103,    0,    0,
   -3,   72,   14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   29,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -23,   32,  -62,    0,  -46,
   82,    0,   18,    0,
};
final static int YYTABLESIZE=176;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
   65,   62,   75,   94,   95,   10,    1,    2,    3,   66,
   43,   80,   46,   99,   11,   49,   43,   43,   48,   43,
   74,   43,   43,   43,   35,   43,   43,   43,   45,   68,
   43,   50,   81,    4,   43,   85,   51,   90,   66,   58,
   67,   50,   73,   70,   35,   60,   51,   18,   45,  121,
   19,   20,   21,   63,  114,   13,   14,   15,   68,   76,
  125,   82,   80,   83,   96,   86,   91,  110,   97,   98,
   72,   72,  100,  101,   72,  115,  105,  102,   72,  104,
   72,   72,   67,   72,  108,   72,   72,   72,  109,   72,
   72,   72,  111,  113,   72,   72,   72,  112,   72,   65,
   65,  116,  117,   65,  122,  123,    6,   65,   13,   65,
   65,   17,   65,   16,   65,   65,   65,   57,   65,   65,
   65,   31,   11,   65,   48,   65,   29,   65,   70,   70,
   59,   61,   70,   71,   87,  118,   70,   78,   70,   70,
  120,   70,    0,   70,   70,   70,    0,   70,   70,   70,
    0,    0,   70,    0,   70,    0,   70,   22,   23,    0,
   24,    0,   25,   26,   27,    0,   13,   14,   15,    0,
    0,   28,    0,    0,    0,   29,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          3,
   27,   25,   49,   66,   67,  282,  257,  258,  259,  267,
  264,   58,  267,   76,  263,  267,  270,  271,  288,  273,
   47,  275,  276,  277,   11,  279,  280,  281,   11,  287,
  284,  283,   59,  284,  288,   62,  288,   64,  267,  267,
  269,  283,   46,  264,   31,  267,  288,    4,   31,  112,
    7,    8,    9,  267,  101,  279,  280,  281,  287,  285,
  123,  288,  109,  263,  268,  288,  278,   91,  260,  268,
  260,  261,  268,  286,  264,  102,  274,  272,  268,  268,
  270,  271,  269,  273,  268,  275,  276,  277,  261,  279,
  280,  281,  268,   97,  284,  285,  286,  260,  288,  260,
  261,  283,  264,  264,  262,  261,    0,  268,  268,  270,
  271,  264,  273,  264,  275,  276,  277,  261,  279,  280,
  281,  268,  268,  284,  264,  286,  268,  288,  260,  261,
  261,  268,  264,   31,   63,  107,  268,   56,  270,  271,
  109,  273,   -1,  275,  276,  277,   -1,  279,  280,  281,
   -1,   -1,  284,   -1,  286,   -1,  288,  270,  271,   -1,
  273,   -1,  275,  276,  277,   -1,  279,  280,  281,   -1,
   -1,  284,   -1,   -1,   -1,  288,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=288;
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
"caso","opcao","enquanto","para","faca","ate","inteiro","real","caracter",
"inclusao_arquivo","valor_primitivo","comentario","operador","comparador",
"incdec","identificador",
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
"SWITCH_OPCAO : opcao valor_primitivo dois_pontos",
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

//#line 286 "inicioCT.y"
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
//#line 394 "Parser.java"
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
//#line 105 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 108 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 109 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 110 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 111 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 112 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 115 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 118 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 121 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 124 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 127 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 12:
//#line 128 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 13:
//#line 129 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 14:
//#line 132 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 15:
//#line 135 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 16:
//#line 136 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 17:
//#line 137 "inicioCT.y"
{yyval.sval=   ""                     ;}
break;
case 18:
//#line 140 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 19:
//#line 141 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 20:
//#line 144 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 145 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 22:
//#line 146 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 23:
//#line 147 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 148 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 25:
//#line 151 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 26:
//#line 154 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 27:
//#line 157 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 28:
//#line 160 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 29:
//#line 163 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 30:
//#line 164 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 31:
//#line 165 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 32:
//#line 168 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 169 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 34:
//#line 170 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 35:
//#line 173 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 36:
//#line 174 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 37:
//#line 176 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 38:
//#line 177 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 39:
//#line 178 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 40:
//#line 181 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 41:
//#line 184 "inicioCT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 42:
//#line 187 "inicioCT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 43:
//#line 188 "inicioCT.y"
{yyval.sval=   ""             ;}
break;
case 44:
//#line 191 "inicioCT.y"
{yyval.sval=  "switch " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 45:
//#line 194 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 46:
//#line 197 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 47:
//#line 200 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 48:
//#line 201 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 49:
//#line 204 "inicioCT.y"
{yyval.sval=   "case " + val_peek(1).sval + ":\n"   ;}
break;
case 50:
//#line 207 "inicioCT.y"
{yyval.sval=  "while " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 51:
//#line 210 "inicioCT.y"
{yyval.sval=   "do " + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 52:
//#line 213 "inicioCT.y"
{yyval.sval=   "while " + val_peek(0).sval + ";\n"  ;}
break;
case 53:
//#line 216 "inicioCT.y"
{yyval.sval=  "for " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 54:
//#line 219 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 55:
//#line 222 "inicioCT.y"
{yyval.sval= val_peek(4).sval + " ; " + val_peek(2).sval + " ; " + val_peek(0).sval  ;}
break;
case 56:
//#line 225 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 57:
//#line 226 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 58:
//#line 229 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 59:
//#line 230 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 60:
//#line 233 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 61:
//#line 234 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 62:
//#line 237 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 63:
//#line 240 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 64:
//#line 243 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 65:
//#line 244 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 66:
//#line 245 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 67:
//#line 248 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 68:
//#line 251 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 69:
//#line 254 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 70:
//#line 255 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 71:
//#line 257 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 72:
//#line 258 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 73:
//#line 259 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 74:
//#line 260 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 75:
//#line 263 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval   ;}
break;
case 76:
//#line 266 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 77:
//#line 267 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 78:
//#line 268 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 855 "Parser.java"
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
