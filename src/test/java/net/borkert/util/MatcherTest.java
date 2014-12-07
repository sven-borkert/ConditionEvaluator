package net.borkert.util;

import net.borkert.util.matchers.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatcherTest {

  @Test
  public void testIsDefined() {
    IsDefined i = new IsDefined();
    assertTrue(i.valueMatchesCondition("", ""));
    assertFalse(i.valueMatchesCondition(null, ""));
  }

  @Test
  public void testStringsEqual() {
    Matcher e = MatcherFactory.getMatcher(Matcher.Mode.IS_EQUAL_STRING);
    assertFalse(e.valueMatchesCondition(null, null));
    assertTrue(e.valueMatchesCondition("a", "a"));
    assertTrue(e.valueMatchesCondition("12345", "12345"));
    assertTrue(e.valueMatchesCondition("öüä²³ÖÄÜ´`~€", "öüä²³ÖÄÜ´`~€"));
    assertFalse(e.valueMatchesCondition("a", "b"));
    assertFalse(e.valueMatchesCondition("a", "A"));
    assertFalse(e.valueMatchesCondition("a", "*"));
    assertFalse(e.valueMatchesCondition("a", "."));
  }

  @Test
  public void testMatchesDosPattern() {
    MatchesDosPattern m = new MatchesDosPattern();
    assertFalse(m.valueMatchesCondition(null, null));
    assertTrue(m.valueMatchesCondition("Test.txt", "*.txt"));
    assertTrue(m.valueMatchesCondition("Test.", "*."));
    assertFalse(m.valueMatchesCondition("Test.", "*.*"));
    assertTrue(m.valueMatchesCondition("Test.txt.bin", "*.txt.*"));
    assertTrue(m.valueMatchesCondition("Test.txt.bin", "*.txt.*"));
    assertTrue(m.valueMatchesCondition("Test.txt.1", "*.txt.?"));
    assertFalse(m.valueMatchesCondition("Test.txt.12", "*.txt.?"));
    assertTrue(m.valueMatchesCondition("Test.txt.12", "*.txt.??"));
    assertTrue(m.valueMatchesCondition("~Test.txt.12", "*.txt.??"));
    assertFalse(m.valueMatchesCondition("~Test.txt.12", "??txt.??"));
    assertTrue(m.valueMatchesCondition("~*txt.12", "??txt.??"));
  }

  @Test
  public void testMatchesRegex() {
    MatchesRegex m = new MatchesRegex();
    assertFalse(m.valueMatchesCondition(null, null));
    assertTrue(m.valueMatchesCondition("Test.txt", "........"));
    assertTrue(m.valueMatchesCondition("Test.txt", "T..t..x."));
    assertTrue(m.valueMatchesCondition("Test.txt.1", "T..t..x.\\.[1,2]"));
    assertFalse(m.valueMatchesCondition("Test.txt.3", "T..t..x.\\.[1,2]"));
  }

  @Test
  public void testNumericEqual() {
    NumericEqual n = new NumericEqual();
    assertFalse(n.valueMatchesCondition(null, null));
    assertTrue(n.valueMatchesCondition("1", "1"));
    assertTrue(n.valueMatchesCondition(1, 1));
    assertTrue(n.valueMatchesCondition("1.000001", "1.000001"));
    assertFalse(n.valueMatchesCondition("1.000002", "1.000001"));
    assertTrue(n.valueMatchesCondition("1.00000000000001", "1.00000000000001"));
    assertFalse(n.valueMatchesCondition("1.00000000000002", "1.00000000000001"));
    assertFalse(n.valueMatchesCondition("1.00000000000002", "A1"));
    assertFalse(n.valueMatchesCondition("", ""));
    assertFalse(n.valueMatchesCondition("", "0"));
    assertFalse(n.valueMatchesCondition("0", ""));
  }

  @Test
  public void testNumericGreaterThan() {
    NumericGreaterThan n = new NumericGreaterThan();
    assertFalse(n.valueMatchesCondition(null, null));
    assertTrue(n.valueMatchesCondition("2", "1"));
    assertFalse(n.valueMatchesCondition("1", "2"));
    assertTrue(n.valueMatchesCondition("1.00000000000002", "1.00000000000001"));
    assertFalse(n.valueMatchesCondition("1.00000000000001", "1.00000000000001"));
    assertFalse(n.valueMatchesCondition("", ""));
    assertFalse(n.valueMatchesCondition("", "0"));
    assertFalse(n.valueMatchesCondition("0", ""));
  }

  @Test
  public void testNumericLowerThan() {
    NumericLowerThan n = new NumericLowerThan();
    assertFalse(n.valueMatchesCondition(null, null));
    assertFalse(n.valueMatchesCondition("2", "1"));
    assertTrue(n.valueMatchesCondition("1", "2"));
    assertTrue(n.valueMatchesCondition("1.00000000000001", "1.00000000000002"));
    assertFalse(n.valueMatchesCondition("1.00000000000001", "1.00000000000001"));
    assertFalse(n.valueMatchesCondition("", ""));
    assertFalse(n.valueMatchesCondition("", "0"));
    assertFalse(n.valueMatchesCondition("0", ""));
  }

}
