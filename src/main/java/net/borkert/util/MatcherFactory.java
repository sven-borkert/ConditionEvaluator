package net.borkert.util;

import net.borkert.util.matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatcherFactory {

  private static Logger log = LogManager.getLogger(MatcherFactory.class);

  public static Matcher getMatcher(String mode) {
    Matcher.Mode m = null;
    try {
      m = Matcher.Mode.valueOf(mode);
    } catch (Exception ex) {
      m = null;
    }
    if (m == null && mode != null) {
      if (mode.equalsIgnoreCase(">")) {
        m = Matcher.Mode.IS_GREATER_NUMBER;
      }
      if (mode.equalsIgnoreCase("<")) {
        m = Matcher.Mode.IS_LOWER_NUMBER;
      }
      if (mode.equalsIgnoreCase(">=")) {
        m = Matcher.Mode.IS_GREATER_OR_EQUAL_NUMBER;
      }
      if (mode.equalsIgnoreCase("<=")) {
        m = Matcher.Mode.IS_LOWER_OR_EQUAL_NUMBER;
      }
      if (mode.equalsIgnoreCase("GT")) {
        m = Matcher.Mode.IS_GREATER_NUMBER;
      }
      if (mode.equalsIgnoreCase("LT")) {
        m = Matcher.Mode.IS_LOWER_NUMBER;
      }
      if (mode.equalsIgnoreCase("DEFINED")) {
        m = Matcher.Mode.IS_DEFINED;
      }
      if (mode.equalsIgnoreCase("REGEX")) {
        m = Matcher.Mode.DOES_MATCH_REGEX;
      }
      if (mode.equalsIgnoreCase("=~")) {
        m = Matcher.Mode.DOES_MATCH_REGEX;
      }
      if (mode.equalsIgnoreCase("DOS")) {
        m = Matcher.Mode.DOES_MATCH_DOS_PATTERN;
      }
      if (mode.equalsIgnoreCase("=")) {
        m = Matcher.Mode.IS_EQUAL_NUMBER;
      }
      if (mode.equalsIgnoreCase("==")) {
        m = Matcher.Mode.IS_EQUAL_NUMBER;
      }
      if (mode.equalsIgnoreCase("EQ")) {
        m = Matcher.Mode.IS_EQUAL_STRING;
      }
    }
    return getMatcher(m);
  }

  public static Matcher getMatcher(Matcher.Mode mode) {
    if (mode == null) {
      log.warn("Matcher type is null, will never match");
      return new NeverMatching();
    }
    if (mode == Matcher.Mode.IS_DEFINED) {
      return new IsDefined();
    } else if (mode == Matcher.Mode.IS_EQUAL_STRING) {
      return new StringsEqual();
    } else if (mode == Matcher.Mode.DOES_MATCH_REGEX) {
      return new MatchesRegex();
    } else if (mode == Matcher.Mode.DOES_MATCH_DOS_PATTERN) {
      return new MatchesDosPattern();
    } else if (mode == Matcher.Mode.IS_EQUAL_NUMBER) {
      return new NumericEqual();
    } else if (mode == Matcher.Mode.IS_GREATER_NUMBER) {
      return new NumericGreaterThan();
    } else if (mode == Matcher.Mode.IS_LOWER_NUMBER) {
      return new NumericLowerThan();
    }
    if (mode == Matcher.Mode.IS_GREATER_OR_EQUAL_NUMBER) {
      return new NumericGreaterOrEqualThan();
    } else if (mode == Matcher.Mode.IS_LOWER_OR_EQUAL_NUMBER) {
      return new NumericLowerOrEqualThan();
    }
    log.warn("Matcher type is unknown, will never match");
    return new NeverMatching();
  }

}
