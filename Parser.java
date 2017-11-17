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
public final static short abre_chaves=259;
public final static short fecha_chaves=260;
public final static short abre_colchetes=261;
public final static short fecha_colchetes=262;
public final static short abre_parenteses=263;
public final static short fecha_parenteses=264;
public final static short atribuicao=265;
public final static short retornar=266;
public final static short inteiro=267;
public final static short real=268;
public final static short caracter=269;
public final static short inclusao_arquivo=270;
public final static short valor_primitivo=271;
public final static short identificador=272;
public final static short comentario=273;
public final static short operador=274;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    2,    3,    4,    5,    5,
    6,    6,    7,    7,    7,    8,    9,   10,   10,   10,
   11,   12,   13,   14,   14,   14,   15,   16,   17,   17,
   18,   18,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    2,    2,    3,    2,    1,
    1,    1,    1,    1,    1,    2,    3,    1,    1,    1,
    1,    1,    2,    1,    1,    2,    3,    2,    2,    0,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    6,    0,    7,
    4,    2,    3,    0,   33,   34,   35,    0,   12,    0,
    0,   11,   13,   14,   15,   18,   19,   20,    0,    0,
   31,   32,   23,    0,   24,    0,    0,    8,    9,   16,
    0,    0,   26,   28,   17,   27,   29,
};
final static short yydgoto[] = {                          4,
    5,    6,    7,   10,   20,   21,   22,   23,   24,   25,
   26,   27,   28,   33,   34,   35,   43,   36,   29,
};
final static short yysindex[] = {                      -257,
 -265, -241, -257,    0,    0, -257, -257,    0, -229,    0,
    0,    0,    0, -230,    0,    0,    0, -231,    0, -225,
 -229,    0,    0,    0,    0,    0,    0,    0, -227, -268,
    0,    0,    0, -228,    0, -228, -230,    0,    0,    0,
 -217, -230,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                        48,
    0,    0,   48,    0,    0,   48,   48,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -211,    0,    0,    0,    0,    0,    0,    0, -237,    0,
    0,    0,    0, -258,    0, -247,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   21,    0,    0,    0,   29,    0,    0,    0,    0,    0,
    0,    0,    0,  -30,    0,   22,   15,    0,    0,
};
final static int YYTABLESIZE=52;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                          1,
    2,   25,   31,   32,    8,   25,   45,   25,   25,   25,
   25,   47,   30,   25,   25,    3,   30,    9,   30,   30,
   30,   30,   21,   11,   30,   30,   12,   13,   21,   21,
   21,   21,   30,   37,   38,   21,   14,   15,   16,   17,
   31,   32,   18,   19,   40,   42,   46,    5,   10,   39,
   44,   41,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        257,
  258,  260,  271,  272,  270,  264,   37,  266,  267,  268,
  269,   42,  260,  272,  273,  273,  264,  259,  266,  267,
  268,  269,  260,    3,  272,  273,    6,    7,  266,  267,
  268,  269,  263,  265,  260,  273,  266,  267,  268,  269,
  271,  272,  272,  273,  272,  274,  264,    0,  260,   21,
   36,   30,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=274;
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
null,null,null,"incluir","main","abre_chaves","fecha_chaves","abre_colchetes",
"fecha_colchetes","abre_parenteses","fecha_parenteses","atribuicao","retornar",
"inteiro","real","caracter","inclusao_arquivo","valor_primitivo",
"identificador","comentario","operador",
};
final static String yyrule[] = {
"$accept : INICIO",
"INICIO : PROGRAMA",
"PROGRAMA : INCLUSAO PROGRAMA",
"PROGRAMA : MAIN PROGRAMA",
"PROGRAMA : comentario PROGRAMA",
"PROGRAMA :",
"INCLUSAO : incluir inclusao_arquivo",
"MAIN : main BLOCO",
"BLOCO : abre_chaves CONTEUDO fecha_chaves",
"CONTEUDO : LINHA CONTEUDO",
"CONTEUDO : LINHA",
"LINHA : EXECUCAO",
"LINHA : comentario",
"EXECUCAO : DECLARACAO",
"EXECUCAO : ATRIBUICAO",
"EXECUCAO : COMANDO",
"DECLARACAO : TIPO identificador",
"ATRIBUICAO : identificador atribuicao EXPRESSAO",
"COMANDO : CONDICIONAL",
"COMANDO : LACO",
"COMANDO : RETURN",
"CONDICIONAL : TIPO",
"LACO : TIPO",
"RETURN : retornar EXPRESSAO",
"EXPRESSAO : EXP",
"EXPRESSAO : PARENTESES_EXP",
"EXPRESSAO : PARENTESES_EXP OPERACAO",
"PARENTESES_EXP : abre_parenteses EXP fecha_parenteses",
"EXP : VALOR OPERACAO",
"OPERACAO : operador EXPRESSAO",
"OPERACAO :",
"VALOR : valor_primitivo",
"VALOR : identificador",
"TIPO : inteiro",
"TIPO : real",
"TIPO : caracter",
};

//#line 154 "inicioCT.y"
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
//#line 273 "Parser.java"
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
//#line 65 "inicioCT.y"
{ System.out.println("\n\n\n" + val_peek(0).sval); }
break;
case 2:
//#line 68 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 3:
//#line 69 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 4:
//#line 70 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 5:
//#line 71 "inicioCT.y"
{yyval.sval=    ""               ;}
break;
case 6:
//#line 74 "inicioCT.y"
{yyval.sval=    "#include " + val_peek(0).sval   ;}
break;
case 7:
//#line 77 "inicioCT.y"
{yyval.sval=    "\nint main()" + val_peek(0).sval   ;}
break;
case 8:
//#line 80 "inicioCT.y"
{yyval.sval=    " {\n" + val_peek(1).sval + "}\n"   ;}
break;
case 9:
//#line 83 "inicioCT.y"
{yyval.sval=   "  " + val_peek(1).sval + "\n" + val_peek(0).sval   ;}
break;
case 10:
//#line 84 "inicioCT.y"
{yyval.sval=   "  " + val_peek(0).sval + "\n"        ;}
break;
case 11:
//#line 87 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 12:
//#line 88 "inicioCT.y"
{yyval.sval=    val_peek(0).sval     ;}
break;
case 13:
//#line 91 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 14:
//#line 92 "inicioCT.y"
{yyval.sval=    val_peek(0).sval + ";"   ;}
break;
case 15:
//#line 93 "inicioCT.y"
{yyval.sval=    val_peek(0).sval         ;}
break;
case 16:
//#line 96 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval  ;}
break;
case 17:
//#line 99 "inicioCT.y"
{yyval.sval=  val_peek(2).sval + " = " + val_peek(0).sval   ;}
break;
case 18:
//#line 102 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 19:
//#line 103 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 20:
//#line 104 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 21:
//#line 107 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 22:
//#line 110 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 23:
//#line 113 "inicioCT.y"
{yyval.sval=    "return " + val_peek(0).sval + ";"   ;}
break;
case 24:
//#line 116 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 25:
//#line 117 "inicioCT.y"
{yyval.sval=    val_peek(0).sval              ;}
break;
case 26:
//#line 118 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval         ;}
break;
case 27:
//#line 121 "inicioCT.y"
{yyval.sval=    "(" + val_peek(1).sval + ")" ;}
break;
case 28:
//#line 124 "inicioCT.y"
{yyval.sval=    val_peek(1).sval + val_peek(0).sval    ;}
break;
case 29:
//#line 127 "inicioCT.y"
{yyval.sval=    " " + val_peek(1).sval + " " + val_peek(0).sval    ;}
break;
case 30:
//#line 128 "inicioCT.y"
{yyval.sval=        ""                 ;}
break;
case 31:
//#line 130 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 32:
//#line 131 "inicioCT.y"
{yyval.sval=    val_peek(0).sval    ;}
break;
case 33:
//#line 134 "inicioCT.y"
{yyval.sval=    "int "      ;}
break;
case 34:
//#line 135 "inicioCT.y"
{yyval.sval=    "double "   ;}
break;
case 35:
//#line 136 "inicioCT.y"
{yyval.sval=    "char "     ;}
break;
//#line 562 "Parser.java"
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
