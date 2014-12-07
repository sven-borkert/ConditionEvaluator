package net.borkert.util.matchers;

public class NumericGreaterThan extends NumericMatcher {

  @Override
  public boolean valueMatchesCondition(double value, double term) {
    return value > term;
  }
}
