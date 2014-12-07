ConditionEvaluator
==================

A Java library to evaluate conditions in strings

Evaluates conditions an strings and returns if they are true or false.

## Examples

### Basic boolean
    "((true || false) && true) && (false || true)" is true
    "((true || false) && true) && (false || false)" is false

### Numeric comparison
    "1==1 && 2==2" is true
    "5*2==2+2+1*2 && 1*2==2" true

### String comparison
    "Hello.txt =~ [leoH]{5}\\.txt" is true
    "\"Hello.txt\" DOS \"*.txt\"") is true

### Strings in quotation marks
    "\"Test with blanks\" EQ \"Test with blanks\"" is true
    "\"Test with blanks\" EQ \"Test with blanks \"" is false
    "'Test with blanks' EQ 'Test with blanks '" is false
    "'Test with blanks' EQ 'Test with blanks'" is true

## Available condition types
* &gt; or GT (Numeric greater)
* &lt; or LT (Numeric lower)
* &gt;= (Numeric greater or equal)
* &lt;= (Numeric lower of equal)
* DEFINED
* =~ or REGEX (Regular expression matching)
* DOS (Dos-style matching *.txt, ???.txt)
* == (Numeric equal)
* EQ (String equal)

## Math operations
* `+` (Add)
* `-` (Subtract)
* `*` (Multiply)
* `/` (Divide)

## Usage
    assertFalse(ConditionEvaluator.evaluate("\"Test with blanks\" EQ \"Test with blanks \""));
    assertTrue(ConditionEvaluator.evaluate("\"Test with blanks\" EQ \"Test with blanks\""));

