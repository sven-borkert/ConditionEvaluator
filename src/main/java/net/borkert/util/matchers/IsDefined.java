package net.borkert.util.matchers;

import net.borkert.util.Matcher;

public class IsDefined implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {
    return value != null;
  }

}
