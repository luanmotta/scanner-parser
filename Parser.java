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
public final static short abre_chaves=262;
public final static short fecha_chaves=263;
public final static short abre_colchetes=264;
public final static short fecha_colchetes=265;
public final static short abre_parenteses=266;
public final static short fecha_parenteses=267;
public final static short atribuicao=268;
public final static short retornar=269;
public final static short se=270;
public final static short senao=271;
public final static short enquanto=272;
public final static short para=273;
public final static short faca=274;
public final static short ate=275;
public final static short inteiro=276;
public final static short real=277;
public final static short caracter=278;
public final static short inclusao_arquivo=279;
public final static short valor_primitivo=280;
public final static short comentario=281;
public final static short operador=282;
public final static short comparador=283;
public final static short incdec=284;
public final static short identificador=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    4,    5,
    6,    6,    6,    7,    8,    8,    8,    9,    9,   10,
   10,   10,   10,   10,   11,   12,   13,   14,   15,   15,
   15,   16,   16,   16,   17,   18,   18,   18,   19,   20,
   21,   21,   22,   23,   24,   25,   26,   27,   28,   28,
   29,   29,   30,   30,   31,   32,   33,   33,   33,   34,
   35,   36,   36,   37,   37,   37,   37,   38,   39,   39,
   39,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    3,    0,    3,    2,    1,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    3,    2,    3,    1,    3,
    0,    1,    1,    1,    1,    1,    1,    1,    2,    4,
    2,    0,    3,    3,    2,    3,    3,    5,    1,    0,
    1,    0,    1,    0,    3,    3,    1,    1,    2,    3,
    2,    2,    0,    1,    1,    1,    1,    2,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   69,   70,   71,    0,    0,    5,    2,    3,
    4,    0,    0,    0,    0,    0,   19,    0,    0,    0,
   18,   20,   21,   22,   24,   32,   33,   34,   35,   36,
   37,   38,   23,    0,    0,   25,    0,   64,    0,   67,
   39,    0,   57,    0,   66,    0,    0,    0,    0,    0,
    0,    0,    0,   68,   27,   14,   15,    0,    0,    9,
    0,    0,   59,   61,    0,    0,    0,   43,    0,   49,
    0,    0,   46,    0,   44,    0,    0,   26,   10,    0,
   60,   62,   55,    0,    0,   40,   47,    0,   45,   28,
    0,   12,   56,   41,    0,   51,   30,    0,   48,   53,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   45,   68,   12,   29,   30,   31,
   32,   33,   50,   65,   86,   35,   36,   37,   38,   39,
   96,   40,   41,   85,   42,   60,   81,   82,  105,  109,
   57,   75,   51,   52,   53,   73,   54,   55,   17,
};
final static short yysindex[] = {                      -250,
 -266, -248, -252, -250,    0,    0, -250, -250, -250,    0,
 -120,    0,    0,    0,    0, -239, -257,    0,    0,    0,
    0, -262, -236, -236, -232, -248,    0, -247, -225, -120,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -252, -248,    0, -269,    0, -244,    0,
    0, -243,    0, -243,    0, -269, -248, -248, -242, -248,
 -227, -262, -262,    0,    0,    0,    0, -218, -208,    0,
 -214, -262,    0,    0, -212, -226, -215,    0, -210,    0,
 -207, -202,    0, -236,    0, -206, -197,    0,    0, -252,
    0,    0,    0, -269, -248,    0,    0, -269,    0,    0,
 -262,    0,    0,    0, -196,    0,    0, -262,    0,    0,
};
final static short yyrindex[] = {                        64,
    0,    0,    0,   64,    0,    0,   64,   64,   64,    0,
 -191,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -190,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -201,    0,    0,    0,    0, -192,    0,
    0, -166,    0, -147,    0,    0,    0,    0, -194,    0,
    0, -193,    0,    0,    0,    0,    0,    0, -188,    0,
    0,    0,    0,    0,    0,    0, -130,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -184,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -185,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -179,    0,    0,
};
final static short yygindex[] = {                         0,
   38,    0,    0,    0,    0,    0,  -25,   62,    0,    0,
   -3,   37,   -1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -22,    1,  -57,    0,  -44,   44,    0,    6,    0,
};
final static int YYTABLESIZE=165;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
   61,   58,   71,   47,   87,   88,    1,    2,    3,   34,
   48,   76,   10,   11,   92,   49,   43,   48,   62,   70,
   63,   62,   49,   13,   14,   15,   44,   46,   34,   56,
    4,   77,   78,   59,   83,   43,   64,   66,   72,   64,
   69,   18,   79,  107,   19,   20,   21,   84,   89,  103,
  110,   90,   91,   76,   93,   95,   94,   63,   98,   97,
  100,   99,  101,    6,  108,   13,   50,   65,   65,  104,
   65,   17,   16,   31,   65,   52,   65,   65,   11,   65,
   65,   65,   29,   65,   65,   65,  102,   54,   65,   65,
   65,   67,   65,   58,   58,   80,   58,   74,  106,    0,
   58,    0,   58,   58,    0,   58,   58,   58,    0,   58,
   58,   58,   63,   63,   58,   63,   58,    0,   58,   63,
    0,   63,   63,    0,   63,   63,   63,    0,   63,   63,
   63,    0,   42,   63,    0,   63,    0,   63,   42,   42,
    0,   42,   42,   42,    0,   42,   42,   42,   22,   23,
   42,   24,   25,   26,   42,   13,   14,   15,    0,    0,
   27,    0,    0,    0,   28,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          3,
   26,   24,   47,  266,   62,   63,  257,  258,  259,   11,
  280,   56,  279,  262,   72,  285,   11,  280,  266,   45,
  268,  266,  285,  276,  277,  278,  266,  285,   30,  266,
  281,   57,   58,  266,   60,   30,  284,  263,  282,  284,
   44,    4,  285,  101,    7,    8,    9,  275,  267,   94,
  108,  260,  267,   98,  267,  271,  283,  268,  261,  267,
  267,   84,  260,    0,  261,  267,  261,  260,  261,   95,
  263,  263,  263,  267,  267,  261,  269,  270,  267,  272,
  273,  274,  267,  276,  277,  278,   90,  267,  281,  282,
  283,   30,  285,  260,  261,   59,  263,   54,   98,   -1,
  267,   -1,  269,  270,   -1,  272,  273,  274,   -1,  276,
  277,  278,  260,  261,  281,  263,  283,   -1,  285,  267,
   -1,  269,  270,   -1,  272,  273,  274,   -1,  276,  277,
  278,   -1,  263,  281,   -1,  283,   -1,  285,  269,  270,
   -1,  272,  273,  274,   -1,  276,  277,  278,  269,  270,
  281,  272,  273,  274,  285,  276,  277,  278,   -1,   -1,
  281,   -1,   -1,   -1,  285,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=285;
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
"abre_chaves","fecha_chaves","abre_colchetes","fecha_colchetes",
"abre_parenteses","fecha_parenteses","atribuicao","retornar","se","senao",
"enquanto","para","faca","ate","inteiro","real","caracter","inclusao_arquivo",
"valor_primitivo","comentario","operador","comparador","incdec","identificador",
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
"LACO : WHILE",
"LACO : DO",
"LACO : FOR",
"RETURN : retornar EXPRESSAO",
"IF : se CONDICAO BLOCO ELSE",
"ELSE : senao BLOCO",
"ELSE :",
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

//#line 262 "inicioCT.y"
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
//#line 373 "Parser.java"
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
//#line 97 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 100 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 101 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 102 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 103 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 104 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 107 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 110 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 113 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 116 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 119 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 12:
//#line 120 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 13:
//#line 121 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 14:
//#line 124 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 15:
//#line 127 "inicioCT.y"
{yyval.sval=   "" + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 16:
//#line 128 "inicioCT.y"
{yyval.sval=   "" + val_peek(0).sval + "\n"        ;}
break;
case 17:
//#line 129 "inicioCT.y"
{yyval.sval=   ""                      ;}
break;
case 18:
//#line 132 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 19:
//#line 133 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 20:
//#line 136 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 137 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 22:
//#line 138 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 23:
//#line 139 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 140 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 25:
//#line 143 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 26:
//#line 146 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 27:
//#line 149 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 28:
//#line 152 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 29:
//#line 155 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 30:
//#line 156 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 31:
//#line 157 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 32:
//#line 160 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 161 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 34:
//#line 162 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 35:
//#line 165 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 36:
//#line 168 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 37:
//#line 169 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 38:
//#line 170 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 39:
//#line 173 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 40:
//#line 176 "inicioCT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 41:
//#line 179 "inicioCT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 42:
//#line 180 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 43:
//#line 183 "inicioCT.y"
{yyval.sval=  "while " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 44:
//#line 186 "inicioCT.y"
{yyval.sval=   "do " + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 45:
//#line 189 "inicioCT.y"
{yyval.sval=   "while " + val_peek(0).sval + ";\n"  ;}
break;
case 46:
//#line 192 "inicioCT.y"
{yyval.sval=  "for " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 47:
//#line 195 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 48:
//#line 198 "inicioCT.y"
{yyval.sval= val_peek(4).sval + " ; " + val_peek(2).sval + " ; " + val_peek(0).sval  ;}
break;
case 49:
//#line 201 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 50:
//#line 202 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 51:
//#line 205 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 52:
//#line 206 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 53:
//#line 209 "inicioCT.y"
{yyval.sval=   val_peek(0).sval   ;}
break;
case 54:
//#line 210 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 55:
//#line 213 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 56:
//#line 216 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 57:
//#line 219 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 58:
//#line 220 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 59:
//#line 221 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 60:
//#line 224 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 61:
//#line 227 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 62:
//#line 230 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 63:
//#line 231 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 64:
//#line 233 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 65:
//#line 234 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 66:
//#line 235 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 67:
//#line 236 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 68:
//#line 239 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval   ;}
break;
case 69:
//#line 242 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 70:
//#line 243 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 71:
//#line 244 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 806 "Parser.java"
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
