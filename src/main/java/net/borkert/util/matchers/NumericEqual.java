package net.borkert.util.matchers;

public class NumericEqual extends NumericMatcher {

  @Override
  public boolean valueMatchesCondition(double value, double condition) {
    return value == condition;
  }

}
