/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
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
import LanguageComponents.Nodes.ExtendedCore.*;
import LanguageComponents.Types.*;
import java.io.FileInputStream;
import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

/** Token Manager. */
public class ParserTokenManager implements ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 47;
            return 7;
         }
         if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 47;
            return 3;
         }
         if ((active0 & 0x7c3a2007000L) != 0L)
         {
            jjmatchedKind = 47;
            return 10;
         }
         return -1;
      case 1:
         if ((active0 & 0x4220002000L) != 0L)
            return 10;
         if ((active0 & 0x789c2005000L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 47;
               jjmatchedPos = 1;
            }
            return 10;
         }
         return -1;
      case 2:
         if ((active0 & 0x14802005000L) != 0L)
            return 10;
         if ((active0 & 0x681c0000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 2;
            return 10;
         }
         return -1;
      case 3:
         if ((active0 & 0x80c0000000L) != 0L)
            return 10;
         if ((active0 & 0x60100000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 3;
            return 10;
         }
         return -1;
      case 4:
         if ((active0 & 0x100000000L) != 0L)
            return 10;
         if ((active0 & 0x60000000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 4;
            return 10;
         }
         return -1;
      case 5:
         if ((active0 & 0x20000000000L) != 0L)
            return 10;
         if ((active0 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 5;
            return 10;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 10:
         return jjStopAtPos(0, 11);
      case 33:
         return jjStopAtPos(0, 28);
      case 34:
         return jjStopAtPos(0, 45);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 40:
         return jjStopAtPos(0, 9);
      case 41:
         return jjStopAtPos(0, 10);
      case 42:
         return jjStopAtPos(0, 7);
      case 43:
         return jjStopAtPos(0, 5);
      case 44:
         return jjStopAtPos(0, 15);
      case 45:
         jjmatchedKind = 6;
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case 46:
         return jjStopAtPos(0, 46);
      case 47:
         return jjStopAtPos(0, 8);
      case 58:
         jjmatchedKind = 37;
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case 59:
         return jjStopAtPos(0, 26);
      case 60:
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 61:
         jjmatchedKind = 24;
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 62:
         jjmatchedKind = 20;
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x80004000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x4020002000L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 123:
         return jjStopAtPos(0, 43);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 125:
         return jjStopAtPos(0, 44);
      case 126:
         return jjStopAtPos(0, 16);
      default :
         return jjMoveNfa_0(4, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         break;
      case 61:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(1, 22);
         else if ((active0 & 0x800000L) != 0L)
            return jjStopAtPos(1, 23);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStopAtPos(1, 27);
         break;
      case 62:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 36);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x10002001000L);
      case 102:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(1, 29, 10);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x140000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 110:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x4000004000L);
      case 111:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(1, 33, 10);
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L);
      case 124:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(1, 19);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(2, 14, 10);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 102:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 40, 10);
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x40100000000L);
      case 110:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(2, 35, 10);
         break;
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 10);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 38, 10);
         break;
      case 119:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(2, 25, 10);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(3, 31, 10);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000000L);
      case 108:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 10);
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000L);
      case 110:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(3, 30, 10);
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(4, 32, 10);
         break;
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 103:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 41, 10);
         break;
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 42, 10);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 11;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 4)
                     kind = 4;
                  jjCheckNAdd(0);
                  break;
               case 7:
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(10);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(10);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 47)
                        kind = 47;
                     jjCheckNAdd(10);
                  }
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 7;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 7:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 47)
                        kind = 47;
                     jjCheckNAdd(10);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 3:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 47)
                        kind = 47;
                     jjCheckNAdd(10);
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 1:
                  if (curChar == 101 && kind > 34)
                     kind = 34;
                  break;
               case 2:
                  if (curChar == 117)
                     jjCheckNAdd(1);
                  break;
               case 5:
                  if (curChar == 115)
                     jjCheckNAdd(1);
                  break;
               case 6:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 8:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 47)
                     kind = 47;
                  jjCheckNAdd(10);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 11 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, "\53", "\55", "\52", "\57", "\50", "\51", "\12", 
"\154\145\164", "\151\156", "\145\156\144", "\54", "\176", "\75\75", "\46\46", "\174\174", 
"\76", "\74", "\76\75", "\74\75", "\75", "\156\145\167", "\73", "\72\75", "\41", 
"\151\146", "\164\150\145\156", "\145\154\163\145", "\167\150\151\154\145", "\144\157", 
null, "\146\165\156", "\55\76", "\72", "\151\156\164", "\142\157\157\154", 
"\162\145\146", "\163\164\162\151\156\147", "\160\162\151\156\164\154\156", "\173", "\175", 
"\42", "\56", null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffffffffff1L, 
};
static final long[] jjtoSkip = {
   0xeL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[11];
static private final int[] jjstateSet = new int[22];
static protected char curChar;
/** Constructor. */
public ParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 11; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002200L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
