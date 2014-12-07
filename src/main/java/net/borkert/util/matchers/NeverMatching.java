package net.borkert.util.matchers;

import net.borkert.util.Matcher;

public class NeverMatching implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {
    return false;
  }

}
