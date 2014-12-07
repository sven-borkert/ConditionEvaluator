package net.borkert.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConditionEvaluator {

  private static Logger log = LogManager.getLogger(ConditionEvaluator.class);
  private static final Pattern comparisonPattern = Pattern.compile("(.*)(<=|>=|<|>|==|DOS|EQ|eq|=~)(.*)");
  private static final Pattern mathPattern = Pattern.compile("(.*)([\\+\\-\\*\\/]{1})(.*)");

  public static boolean evaluate(String input) {

    log.debug("evaluateLogic(" + input + ")");

    char[] inputChars = input.toCharArray();
    StringBuilder result = new StringBuilder();
    StringBuilder buffer = new StringBuilder();
    int braceCounter = 0;

    for (int i = 0; i < inputChars.length; i++) {
      if (inputChars[i] == '(') {
        braceCounter++;
        continue;
      }
      if (inputChars[i] == ')') {
        braceCounter--;
        if (braceCounter == 0) {
          result.append(evaluate(buffer.toString()));
          buffer = new StringBuilder();
        }
        continue;
      }
      if (braceCounter > 0) {
        buffer.append(inputChars[i]);
      } else {
        result.append(inputChars[i]);
      }
    }
    String[] partsOr = result.toString().split("\\|\\|");
    if (partsOr.length == 1) {
      // Keine oder parts (mehr)
      String[] partsAnd = result.toString().split("&&");
      for (String part : partsAnd) {
        if (!isPartTrue(part)) {
          return false;
        }
      }
    } else {
      // Oder parts
      for (String part : partsOr) {
        if (evaluate(part.trim())) {
          return true;
        }
      }
      return false;
    }
    return true;
  }

  private static boolean isPartTrue(String input) {
    log.trace("isPartTrue(" + input.trim() + ")");
    if (comparisonPattern.matcher(input).matches()) {
      return evaluateComparison(input);
    } else {
      if (mathPattern.matcher(input).matches()) {
        throw new ConditionSyntax();
      }
    }
    String trimmedInput = input.trim();
    if (trimmedInput.equalsIgnoreCase("1")) {
      return true;
    }
    if (trimmedInput.equalsIgnoreCase("0")) {
      return false;
    }
    return Boolean.valueOf(trimmedInput);
  }

  private static boolean evaluateComparison(String input) {
    Matcher m = comparisonPattern.matcher(input);
    if (m.find()) {
      String part1 = m.group(1);
      if (mathPattern.matcher(part1).matches()) {
        part1 = calculate(part1);
      }
      String op = m.group(2);
      String part2 = m.group(3);
      if (mathPattern.matcher(part2).matches()) {
        part2 = calculate(part2);
      }
      part1 = part1.trim().replaceAll("[\"\']", "");
      part2 = part2.trim().replaceAll("[\"\']", "");
      log.debug("Comparison: " + part1 + " " + op + " " + part2);
      boolean result = MatcherFactory.getMatcher(op).valueMatchesCondition(part1, part2);
      log.debug("Returning: " + result);
      return result;
    }
    return false;
  }

  private static String calculate(String input) {
    log.debug("calculate(" + input.trim() + ")");
    char[] inputChars = input.toCharArray();
    StringBuilder buffer = new StringBuilder();
    char activeOperator = 'x';
    int result = 0;
    try {
      for (int i = 0; i < inputChars.length; i++) {
        if (inputChars[i] == '"' || inputChars[i] == '\'') {
          return input;
        }
        if (inputChars[i] == '+' || inputChars[i] == '-' || inputChars[i] == '*' || inputChars[i] == '/' || i == inputChars.length - 1) {
          if (i == inputChars.length - 1) {
            buffer.append(inputChars[i]);
          }
          if (buffer.length() == 0) {
            throw new ConditionSyntax();
          }
          int n = Integer.parseInt(buffer.toString().trim());
          if (activeOperator == 'x') {
            result = n;
          }
          if (activeOperator == '+') {
            result += n;
          }
          if (activeOperator == '-') {
            result -= n;
          }
          if (activeOperator == '*') {
            result *= n;
          }
          if (activeOperator == '/') {
            result /= n;
          }
          buffer = new StringBuilder();
          activeOperator = inputChars[i];
          continue;
        }
        buffer.append(inputChars[i]);
      }
    } catch (NumberFormatException ex) {
      return input;
    }

    return Integer.toString(result);
  }

  public static class ConditionSyntax extends RuntimeException {
  }

}
