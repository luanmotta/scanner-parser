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
public final static short abre_chaves=261;
public final static short fecha_chaves=262;
public final static short abre_colchetes=263;
public final static short fecha_colchetes=264;
public final static short abre_parenteses=265;
public final static short fecha_parenteses=266;
public final static short atribuicao=267;
public final static short retornar=268;
public final static short se=269;
public final static short senao=270;
public final static short inteiro=271;
public final static short real=272;
public final static short caracter=273;
public final static short inclusao_arquivo=274;
public final static short valor_primitivo=275;
public final static short comentario=276;
public final static short operador=277;
public final static short comparador=278;
public final static short identificador=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    1,    2,    3,    4,    5,
    6,    6,    6,    7,    8,    8,    9,    9,   10,   10,
   10,   10,   11,   12,   13,   14,   15,   15,   15,   16,
   16,   16,   17,   18,   19,   20,   21,   21,   22,   23,
   24,   24,   24,   25,   26,   27,   27,   28,   28,   28,
   29,   29,   29,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    2,    0,    2,    2,    4,    3,
    1,    3,    0,    3,    2,    1,    1,    1,    1,    1,
    1,    1,    2,    3,    2,    3,    1,    3,    0,    1,
    1,    1,    1,    1,    2,    4,    2,    0,    3,    3,
    1,    1,    2,    3,    2,    2,    0,    1,    1,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    0,    0,    7,
    0,    8,   51,   52,   53,    0,    0,    5,    2,    3,
    4,    0,    0,   18,    0,    0,    0,   17,   19,   20,
   21,   22,   30,   31,   32,   33,    0,    0,    0,   23,
    0,   48,    0,   50,   35,    0,   41,    0,    0,    0,
    0,    0,   25,   14,   15,    0,    0,    9,    0,    0,
   43,   45,    0,    0,    0,    0,    0,   24,   10,    0,
   44,   46,   39,    0,    0,   36,   26,    0,   12,   40,
   37,   28,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   39,   56,   12,   26,   27,   28,
   29,   30,   44,   53,   66,   32,   33,   34,   35,   36,
   76,   50,   63,   45,   46,   47,   61,   48,   17,
};
final static short yysindex[] = {                      -227,
 -270, -246, -211, -227,    0,    0, -227, -227, -227,    0,
 -173,    0,    0,    0,    0, -256, -261,    0,    0,    0,
    0, -224, -237,    0, -209, -229, -173,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -261, -211, -246,    0,
 -231,    0, -201,    0,    0, -238,    0, -238, -231, -246,
 -224, -224,    0,    0,    0, -212, -188,    0, -192, -224,
    0,    0, -187, -194, -184, -175, -159,    0,    0, -211,
    0,    0,    0, -231, -246,    0,    0, -224,    0,    0,
    0,    0,
};
final static short yyrindex[] = {                        87,
    0,    0,    0,   87,    0,    0,   87,   87,   87,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -160,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -179, -161,    0,    0,
    0,    0, -252,    0,    0, -226,    0, -203,    0,    0,
 -156,    0,    0,    0,    0,    0, -155,    0,    0,    0,
    0,    0,    0,    0, -191,    0, -154,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  100,    0,    0,    0,    0,    0,  -38,   86,    0,    0,
   -3,    0,   -5,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -49,    0,  -36,   66,    0,   -4,
};
final static int YYTABLESIZE=114;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
   58,   67,   68,   10,   59,   31,   37,   49,   38,   49,
   72,   65,   64,   49,   11,   49,   49,   40,   49,   49,
   49,   31,   37,   49,   49,   49,   49,   49,   82,    1,
    2,    3,   54,   42,   57,   42,   81,   80,   60,   42,
   41,   42,   42,   42,   42,   42,   42,   43,    4,   42,
   42,   42,   42,   69,   43,   51,   47,   52,   47,   13,
   14,   15,   47,   51,   47,   47,   79,   47,   47,   47,
   38,   70,   47,   71,   47,   47,   38,   38,   73,   38,
   38,   38,   34,   74,   38,   75,    6,   38,   34,   34,
   77,   34,   34,   34,   22,   23,   34,   13,   14,   15,
   78,   16,   24,   18,   13,   25,   19,   20,   21,   29,
   11,   27,   55,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          3,
   39,   51,   52,  274,   41,   11,   11,  260,  265,  262,
   60,   50,   49,  266,  261,  268,  269,  279,  271,  272,
  273,   27,   27,  276,  277,  278,  279,  265,   78,  257,
  258,  259,  262,  260,   38,  262,   75,   74,  277,  266,
  265,  268,  269,  275,  271,  272,  273,  279,  276,  276,
  275,  278,  279,  266,  279,  265,  260,  267,  262,  271,
  272,  273,  266,  265,  268,  269,   70,  271,  272,  273,
  262,  260,  276,  266,  278,  279,  268,  269,  266,  271,
  272,  273,  262,  278,  276,  270,    0,  279,  268,  269,
  266,  271,  272,  273,  268,  269,  276,  271,  272,  273,
  260,  262,  276,    4,  266,  279,    7,    8,    9,  266,
  266,  266,   27,   48,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=279;
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
null,null,null,"incluir","main","subrotina","virgula","abre_chaves",
"fecha_chaves","abre_colchetes","fecha_colchetes","abre_parenteses",
"fecha_parenteses","atribuicao","retornar","se","senao","inteiro","real",
"caracter","inclusao_arquivo","valor_primitivo","comentario","operador",
"comparador","identificador",
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
"CALL_PARAMS : EXPRESSAO virgula EXPRESSAO",
"CALL_PARAMS :",
"COMANDO : CONDICIONAL",
"COMANDO : LACO",
"COMANDO : RETURN",
"CONDICIONAL : IF",
"LACO : TIPO",
"RETURN : retornar EXPRESSAO",
"IF : se CONDICAO BLOCO ELSE",
"ELSE : senao BLOCO",
"ELSE :",
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
"VALOR : CALL",
"TIPO : inteiro",
"TIPO : real",
"TIPO : caracter",
};

//#line 208 "inicioCT.y"
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
//#line 327 "Parser.java"
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
//#line 81 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 84 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 85 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 86 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 87 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 6:
//#line 88 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 7:
//#line 91 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 8:
//#line 94 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 9:
//#line 97 "inicioCT.y"
{yyval.sval=   "\n" + val_peek(2).sval + " " + val_peek(1).sval + val_peek(0).sval    ;}
break;
case 10:
//#line 100 "inicioCT.y"
{yyval.sval=   "(" + val_peek(1).sval + ")"    ;}
break;
case 11:
//#line 103 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 12:
//#line 104 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 13:
//#line 105 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 14:
//#line 108 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 15:
//#line 111 "inicioCT.y"
{yyval.sval=   "  " + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 16:
//#line 112 "inicioCT.y"
{yyval.sval=   "  " + val_peek(0).sval + "\n"        ;}
break;
case 17:
//#line 115 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 18:
//#line 116 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 19:
//#line 119 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 20:
//#line 120 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 21:
//#line 121 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 22:
//#line 122 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 23:
//#line 125 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 24:
//#line 128 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 25:
//#line 131 "inicioCT.y"
{yyval.sval=   val_peek(1).sval + val_peek(0).sval   ;}
break;
case 26:
//#line 134 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")"   ;}
break;
case 27:
//#line 137 "inicioCT.y"
{yyval.sval=    val_peek(0).sval                 ;}
break;
case 28:
//#line 138 "inicioCT.y"
{yyval.sval=    val_peek(2).sval + ", " + val_peek(0).sval     ;}
break;
case 29:
//#line 139 "inicioCT.y"
{yyval.sval=    ""                 ;}
break;
case 30:
//#line 142 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 31:
//#line 143 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 32:
//#line 144 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 147 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 34:
//#line 150 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 35:
//#line 153 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 36:
//#line 156 "inicioCT.y"
{yyval.sval=  "if " + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval  ;}
break;
case 37:
//#line 159 "inicioCT.y"
{yyval.sval=   "else " + val_peek(0).sval   ;}
break;
case 38:
//#line 160 "inicioCT.y"
{yyval.sval=   ""   ;}
break;
case 39:
//#line 163 "inicioCT.y"
{yyval.sval=    "( " + val_peek(1).sval + " )"   ;}
break;
case 40:
//#line 166 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " " + val_peek(1).sval + " " + val_peek(0).sval   ;}
break;
case 41:
//#line 169 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 42:
//#line 170 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 43:
//#line 171 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 44:
//#line 174 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 45:
//#line 177 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 46:
//#line 180 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 47:
//#line 181 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 48:
//#line 183 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 49:
//#line 184 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 50:
//#line 185 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 51:
//#line 188 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 52:
//#line 189 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 53:
//#line 190 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 688 "Parser.java"
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
