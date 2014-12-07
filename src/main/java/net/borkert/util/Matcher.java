package net.borkert.util;

public interface Matcher {
  public enum Mode {IS_DEFINED, IS_EQUAL_NUMBER, IS_GREATER_NUMBER, IS_GREATER_OR_EQUAL_NUMBER, IS_LOWER_NUMBER, IS_LOWER_OR_EQUAL_NUMBER, IS_EQUAL_STRING, DOES_MATCH_REGEX, DOES_MATCH_DOS_PATTERN}

  public boolean valueMatchesCondition(String value, String condition);
}
