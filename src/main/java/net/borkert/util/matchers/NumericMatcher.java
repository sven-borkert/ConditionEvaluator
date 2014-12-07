package net.borkert.util.matchers;

import net.borkert.util.Matcher;

public abstract class NumericMatcher implements Matcher {

  @Override
  public boolean valueMatchesCondition(String value, String condition) {

    if (value == null || condition == null) {
      return false;
    }

    double numericValue;
    double numericTerm;

    try {
      numericValue = Double.parseDouble(value);
      numericTerm = Double.parseDouble(condition);
      return valueMatchesCondition(numericValue, numericTerm);
    } catch (NumberFormatException ex) {
      return false;
    }

  }

  public abstract boolean valueMatchesCondition(double value, double term);

}
