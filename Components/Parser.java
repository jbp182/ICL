/* Generated By:JavaCC: Do not edit this line. Parser.java */
/** ID lister. */
package Components;
import LanguageComponents.Environments.CodeBlock;
import LanguageComponents.Environments.CompilerEnvironment;
import LanguageComponents.Environments.Environment;
import LanguageComponents.Nodes.*;
import LanguageComponents.Nodes.ArithmeticOperators.*;
import LanguageComponents.Nodes.FuncionalCore.*;
import LanguageComponents.Nodes.ImperativeCore.*;
import LanguageComponents.Nodes.LogicOperators.*;
import LanguageComponents.Nodes.RelationalOperators.*;
import java.io.FileInputStream;
import java.io.File;
import java.util.LinkedList;

public class Parser implements ParserConstants {

  /** Main entry point. */
  public static void main(String args[]) throws  Exception {

    if(args.length > 1 && args[0].equals("-f")){
        compile(args[1]);
        return;
    }

    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
        try {
        exp = parser.Start();
        exp.eval(new Environment(null)).show();
        } catch (Exception e) {
          System.out.println ("Syntax Error!\u005cn" + e.getMessage());
          parser.ReInit(System.in);
        }
    }
  }

  private static void compile(String fileName) throws Exception{
      File f = new File(fileName);
      Parser parser = new Parser(new FileInputStream(f));
      ASTNode exp = parser.Start();
      CodeBlock blk = new CodeBlock();
      exp.compile(new CompilerEnvironment(null),blk);
      blk.dump("Main.j");
  }

  static final public ASTNode Start() throws ParseException {
  ASTNode t;
    t = EM();
    jj_consume_token(EL);
     {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode EM() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = E();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEQ:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      op = jj_consume_token(SEQ);
      t2 = EM();
                                  t1 = new ASTSeq(t1,t2);
    }
          {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode E() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = EA();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ISEQ:
      op = jj_consume_token(ISEQ);
      t2 = EA();
                                  t1 = new ASTEquals(t1,t2);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
      {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode EA() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = T();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
      case AND:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        op = jj_consume_token(PLUS);
        break;
      case MINUS:
        op = jj_consume_token(MINUS);
        break;
      case AND:
        op = jj_consume_token(AND);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = EA();
                   if (op.kind == PLUS)
                         t1 = new ASTPlus(t1,t2);
                   else if (op.kind == MINUS)
                                 t1 = new ASTSub(t1,t2);
                   else t1 = new ASTAnd(t1,t2);
    }
       {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode T() throws ParseException {
  Token op;
  ASTNode f1, f2;
    f1 = F();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      f2 = E();
                                  f1 = new ASTAssign(f1,f2);
      break;
    case GT:
    case LT:
    case GE:
    case LE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case GE:
        op = jj_consume_token(GE);
        break;
      case LE:
        op = jj_consume_token(LE);
        break;
      case GT:
        op = jj_consume_token(GT);
        break;
      case LT:
        op = jj_consume_token(LT);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      f2 = F();
                                          if (op.kind == GE)
                                                        f1 = new ASTGreaterEqual(f1,f2);
                                          else if (op.kind == LE)
                                                        f1 = new ASTLowerEqual(f1,f2);
                                          else if (op.kind == GT)
                                                        f1 = new ASTGreater(f1,f2);
                                          else if (op.kind == LT)
                                                        f1 = new ASTLower(f1,f2);
      break;
    default:
      jj_la1[7] = jj_gen;
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
        case DIV:
        case OR:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
          op = jj_consume_token(TIMES);
          break;
        case DIV:
          op = jj_consume_token(DIV);
          break;
        case OR:
          op = jj_consume_token(OR);
          break;
        default:
          jj_la1[6] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        f2 = T();
                               if (op.kind == TIMES)
                                        f1 = new ASTMul(f1,f2);
                                else if (op.kind == DIV)
                                        f1 = new ASTDiv(f1,f2);
                                else f1 = new ASTOr(f1,f2);
      }
    }
          {if (true) return f1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode F() throws ParseException {
  Token n, id, id1, id2;
  ASTNode t, init1, init2, body;
  LinkedList<String> ids;
  LinkedList<ASTNode> inits;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      n = jj_consume_token(Num);
                      t = new ASTNum(Integer.parseInt(n.image));
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t = EM();
      jj_consume_token(RPAR);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      t = F();
                       t = new ASTMul(new ASTNum(-1), t);
      break;
    case PLUS:
      jj_consume_token(PLUS);
      t = F();
      break;
    case BOOL:
      n = jj_consume_token(BOOL);
                   t = new ASTBool( n.image );
      break;
    case NEW:
      jj_consume_token(NEW);
      t = F();
                     t = new ASTNewRef( t );
      break;
    case DESREF:
      jj_consume_token(DESREF);
      t = F();
                        t = new ASTDeref( t );
      break;
    case IF:
      jj_consume_token(IF);
      t = EM();
      jj_consume_token(THEN);
      init1 = EM();
      jj_consume_token(ELSE);
      init2 = EM();
      jj_consume_token(END);
                          t = new ASTIf( t, init1, init2 );
      break;
    case WHILE:
      jj_consume_token(WHILE);
      t = EM();
      jj_consume_token(DO);
      body = EM();
      jj_consume_token(END);
                          t = new ASTWhile(t,body);
      break;
    case NOT:
      jj_consume_token(NOT);
      t = F();
                           t = new ASTNeg(t);
      break;
    case LET:
      jj_consume_token(LET);
      id1 = jj_consume_token(Id);
      jj_consume_token(EQ);
      init1 = EM();
                                ids = new LinkedList();
                                inits = new LinkedList();
                                ids.add(id1.image); inits.add(init1);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        id2 = jj_consume_token(Id);
        jj_consume_token(EQ);
        init2 = EM();
                          ids.add(id2.image); inits.add(init2);
      }
      jj_consume_token(IN);
      body = EM();
      jj_consume_token(END);
                  t = new ASTLet(ids, inits, body);
      break;
    case Id:
      id = jj_consume_token(Id);
                      t = new ASTId( id.image );
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x4000000,0x20000,0x40060,0x40060,0xf00000,0x80180,0x80180,0x8f00000,0x8000,0x32011270,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xd,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[36];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 36; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}