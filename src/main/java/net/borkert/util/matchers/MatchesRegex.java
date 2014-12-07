package net.borkert.util.matchers;

import net.borkert.util.Matcher;

import java.util.regex.PatternSyntaxException;

public class MatchesRegex implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {
    if (value == null || condition == null) {
      return false;
    }
    try {
      return value.matches(condition);
    } catch (PatternSyntaxException ex) {
      return false;
    }
  }

}
