package net.borkert.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConditionEvaluatorTest {

  @Test
  public void booleanBasics() {
    assertTrue(Boolean.valueOf("true"));
    assertTrue(!Boolean.valueOf(" true"));
    assertTrue(!Boolean.valueOf("false"));
    assertTrue(!Boolean.valueOf(" false"));
  }

  @Test
  public void canEvaluateLogicConditions() {
    assertTrue(ConditionEvaluator.evaluate("1 && (0 || 1)"));
    assertTrue(ConditionEvaluator.evaluate("true && (false || true)"));
    assertFalse(ConditionEvaluator.evaluate("false && (false || true)"));
    assertTrue(ConditionEvaluator.evaluate("false && false || true"));
    assertFalse(ConditionEvaluator.evaluate("true && (false || false)"));
    assertFalse(ConditionEvaluator.evaluate("(true && false) && (false || true)"));
    assertTrue(ConditionEvaluator.evaluate("((true || false) && true) && (false || true)"));
    assertFalse(ConditionEvaluator.evaluate("((true || false) && true) && (false || false)"));
  }

  @Test
  public void canEvaluateCompareConditions() {
    assertFalse(ConditionEvaluator.evaluate("true==true"));
    assertFalse(ConditionEvaluator.evaluate("1=1 && 2=2"));
    assertTrue(ConditionEvaluator.evaluate("1==1 && 2==2"));
    assertTrue(ConditionEvaluator.evaluate("1==2 || 2<3"));
    assertFalse(ConditionEvaluator.evaluate("1==2 && 2<3"));
    assertFalse(ConditionEvaluator.evaluate("1==2>x && 2<3"));
    assertTrue(ConditionEvaluator.evaluate("1==2>x || 2<3"));
    assertFalse(ConditionEvaluator.evaluate("true && (1<2 && 3<4 && 10>1 && (1==2 || 1==2))"));
    assertTrue(ConditionEvaluator.evaluate("Hello eq Hello"));
    assertTrue(ConditionEvaluator.evaluate("Hello.txt =~ [leoH]{5}\\.txt"));
    assertTrue(ConditionEvaluator.evaluate("50 < 100"));
    assertTrue(ConditionEvaluator.evaluate("100 <= 100"));
    assertTrue(ConditionEvaluator.evaluate("\"Hello.txt\" DOS \"*.txt\""));
    assertFalse(ConditionEvaluator.evaluate("\"Test with blanks\" EQ \"Test with blanks \""));
    assertTrue(ConditionEvaluator.evaluate("\"Test with blanks\" EQ \"Test with blanks\""));
    assertFalse(ConditionEvaluator.evaluate("'Test with blanks' EQ 'Test with blanks '"));
    assertTrue(ConditionEvaluator.evaluate("'Test with blanks' EQ 'Test with blanks'"));
  }

  @Test
  public void canPerformMath() {
    assertTrue(ConditionEvaluator.evaluate("5*2==2+2+1*2 && 1*2==2"));
    assertTrue(ConditionEvaluator.evaluate("5 * 2 == 2 + 2 + 1 * 2 && 1*2==2"));
    assertTrue(ConditionEvaluator.evaluate("     5 * 2 == 2 + 2 + 1 * 2 && 1*2==2"));
    assertTrue(ConditionEvaluator.evaluate("10*5==50"));
    assertTrue(ConditionEvaluator.evaluate("10*5/2==25"));
    assertTrue(ConditionEvaluator.evaluate("10*5/2-7==18"));
  }
}
