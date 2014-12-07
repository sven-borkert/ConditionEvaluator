package net.borkert.util.matchers;

public class NumericGreaterOrEqualThan extends NumericMatcher {

  @Override
  public boolean valueMatchesCondition(double value, double term) {
    return value >= term;
  }
}
