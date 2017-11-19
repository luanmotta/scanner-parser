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
public final static short abre_chaves=260;
public final static short fecha_chaves=261;
public final static short abre_colchetes=262;
public final static short fecha_colchetes=263;
public final static short abre_parenteses=264;
public final static short fecha_parenteses=265;
public final static short atribuicao=266;
public final static short retornar=267;
public final static short se=268;
public final static short inteiro=269;
public final static short real=270;
public final static short caracter=271;
public final static short inclusao_arquivo=272;
public final static short valor_primitivo=273;
public final static short comentario=274;
public final static short operador=275;
public final static short comparador=276;
public final static short identificador=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    4,    5,
    6,    6,    7,    8,    8,    9,    9,   10,   10,   10,
   10,   11,   12,   13,   14,   15,   15,   16,   16,   16,
   17,   18,   19,   20,   21,   22,   23,   23,   23,   24,
   25,   26,   26,   27,   27,   27,   28,   28,   28,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    0,    3,    2,    1,    1,    1,    1,    1,    1,
    1,    2,    3,    2,    3,    1,    0,    1,    1,    1,
    1,    1,    2,    3,    3,    3,    1,    1,    2,    3,
    2,    2,    0,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   47,   48,   49,    0,    0,    5,    2,    3,
    4,    0,    0,   17,    0,    0,    0,   16,   18,   19,
   20,   21,   28,   29,   30,   31,    0,    0,    0,   22,
    0,   44,    0,   46,   33,    0,   37,    0,    0,    0,
    0,    0,   24,   13,   14,    0,   11,    9,    0,    0,
   39,   41,    0,    0,   34,    0,   26,   23,   10,   40,
   42,   35,    0,   25,   36,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   39,   56,   12,   26,   27,   28,
   29,   30,   44,   53,   66,   32,   33,   34,   35,   36,
   50,   63,   45,   46,   47,   61,   48,   17,
};
final static short yysindex[] = {                      -255,
 -260, -224, -191, -255,    0,    0, -255, -255, -255,    0,
 -203,    0,    0,    0,    0, -225, -236,    0,    0,    0,
    0, -204, -214,    0, -258, -198, -203,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -236, -191, -224,    0,
 -268,    0, -194,    0,    0, -193,    0, -193, -268, -224,
 -204, -204,    0,    0,    0, -184,    0,    0, -182, -204,
    0,    0, -181, -190,    0, -180,    0,    0,    0,    0,
    0,    0, -268,    0,    0,
};
final static short yyrindex[] = {                        87,
    0,    0,    0,   87,    0,    0,   87,   87,   87,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -173,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -212, -176,    0,    0,
    0,    0, -254,    0,    0, -237,    0, -223,    0,    0,
 -175,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   68,    0,    0,    0,    0,    0,  -21,   64,    0,    0,
   -3,    0,   -1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,   51,   45,  -48,   16,
};
final static int YYTABLESIZE=93;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
   64,    1,    2,    3,   42,   51,   45,   52,   43,   31,
   45,   10,   45,   45,   45,   45,   45,   58,    4,   45,
   45,   45,   45,   38,   75,   31,   37,   38,   65,   38,
   38,   38,   38,   38,   57,   11,   38,   43,   38,   38,
   40,   43,   37,   43,   43,   43,   43,   43,   32,   49,
   43,   67,   68,   43,   32,   32,   32,   32,   32,   41,
   71,   32,   54,   22,   23,   13,   14,   15,   42,   51,
   24,   18,   43,   25,   19,   20,   21,   13,   14,   15,
   69,   60,   70,   72,   74,   73,    6,   15,   12,   27,
   55,   59,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          3,
   49,  257,  258,  259,  273,  264,  261,  266,  277,   11,
  265,  272,  267,  268,  269,  270,  271,   39,  274,  274,
  275,  276,  277,  261,   73,   27,   11,  265,   50,  267,
  268,  269,  270,  271,   38,  260,  274,  261,  264,  277,
  277,  265,   27,  267,  268,  269,  270,  271,  261,  264,
  274,   51,   52,  277,  267,  268,  269,  270,  271,  264,
   60,  274,  261,  267,  268,  269,  270,  271,  273,  264,
  274,    4,  277,  277,    7,    8,    9,  269,  270,  271,
  265,  275,  265,  265,  265,  276,    0,  261,  265,  265,
   27,   41,   48,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=277;
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
null,null,null,"incluir","main","subrotina","abre_chaves","fecha_chaves",
"abre_colchetes","fecha_colchetes","abre_parenteses","fecha_parenteses",
"atribuicao","retornar","se","inteiro","real","caracter","inclusao_arquivo",
"valor_primitivo","comentario","operador","comparador","identificador",
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
"PARAMS :",
"BLOCO : abre_chaves CONTEUDO fecha_chaves",
"CONTEUDO : LINHA CONTEUDO",
"CONTEUDO : LINHA",
"LINHA : EXECUCAO",
"LINHA : comentario",
"EXECUCAO : DECLARACAO",
"EXECUCAO : ATRIBUICAO",
"EXECUCAO : CALL",
"EXECUCAO : COMANDO",
"DECLARACAO : TIPO identificador",
"ATRIBUICAO : identificador atribuicao EXPRESSAO",
"CALL : identificador CALL_PARAM",
"CALL_PARAM : abre_parenteses CALL_PARAMS fecha_parenteses",
"CALL_PARAMS : EXPRESSAO",
"CALL_PARAMS :",
"COMANDO : CONDICIONAL",
"COMANDO : LACO",
"COMANDO : RETURN",
"CONDICIONAL : IF",
"LACO : TIPO",
"RETURN : retornar EXPRESSAO",
"IF : se CONDICAO BLOCO",
"CONDICAO : abre_parenteses COMPARACAO fecha_parenteses",
"COMPARACAO : VALOR comparador VALOR",
"EXPRESSAO : EXP",
"EXPRESSAO : PARENTESES_EXP",
"EXPRESSAO : PARENTESES_EXP OPERACAO",
"PARENTESES_EXP : abre_parenteses EXP fecha_parenteses",
"EXP : VALOR OPERACAO",
"OPERACAO : operador EXPRESSAO",
"OPERACAO :",
"VALOR : valor_primitivo",
"VALOR : identificador",
"VALOR : CALL",
"TIPO : inteiro",
"TIPO : real",
"TIPO : caracter",
};

//#line 198 "inicioCT.y"
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
//#line 311 "Parser.java"
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
//#line 77 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 80 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 81 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 82 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 83 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 84 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 87 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 90 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 93 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 96 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 99 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 12:
//#line 100 "inicioCT.y"
{yyval.sval=    ""     ;}
break;
case 13:
//#line 103 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 14:
//#line 106 "inicioCT.y"
{yyval.sval=   "  " + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 15:
//#line 107 "inicioCT.y"
{yyval.sval=   "  " + val_peek(0).sval + "\n"        ;}
break;
case 16:
//#line 110 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 17:
//#line 111 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 18:
//#line 114 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 19:
//#line 115 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 20:
//#line 116 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 117 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 22:
//#line 120 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 23:
//#line 123 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 24:
//#line 126 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 25:
//#line 129 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 26:
//#line 132 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 27:
//#line 133 "inicioCT.y"
{yyval.sval=    ""     ;}
break;
case 28:
//#line 136 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 29:
//#line 137 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 30:
//#line 138 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 31:
//#line 141 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 32:
//#line 144 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 147 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 34:
//#line 150 "inicioCT.y"
{yyval.sval=  "if " + val_peek(1).sval + val_peek(0).sval   ;}
break;
case 35:
//#line 153 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 36:
//#line 156 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 37:
//#line 159 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 38:
//#line 160 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 39:
//#line 161 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 40:
//#line 164 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 41:
//#line 167 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 42:
//#line 170 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 43:
//#line 171 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 44:
//#line 173 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 45:
//#line 174 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 46:
//#line 175 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 47:
//#line 178 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 48:
//#line 179 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 49:
//#line 180 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 656 "Parser.java"
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
