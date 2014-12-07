package net.borkert.util.matchers;

import net.borkert.util.Matcher;

public class StringsEqual implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {
    if (value == null || condition == null) {
      return false;
    }
    return value.equals(condition);
  }

}
