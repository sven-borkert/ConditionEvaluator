package net.borkert.util.matchers;

import net.borkert.util.Matcher;

public class MatchesDosPattern implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {
    if (value == null || condition == null) {
      return false;
    }
    return value.matches(condition.replaceAll("([\\^\\\\\\$\\+\\(\\)\\[\\]\\|\\.])", "\\\\$1").replaceAll("\\*", ".+").replaceAll("\\?", "."));
  }

}
