package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class VariablesHandler {

    public static final String LATIN_LETTERS_REGEX = "[A-Za-z]+";
    public static final String NUMBERS_REGEX = "-?+[\\d]+";
    public static final String ASSIGNMENT_DELIMITER_REGEX = "\\s*=\\s*";
    public static final String UNKNOWN_VARIABLE = "Unknown variable";

    private static final Map<String, BigInteger> variablesMap = new HashMap<>();

    public void printVariable(String input) {
        System.out.println(variablesMap.containsKey(input) ? variablesMap.get(input).toString() : UNKNOWN_VARIABLE);
    }

    public BigInteger resolveTheValue(String variableName) {
        return variablesMap.get(variableName) != null ? new BigInteger(variablesMap.get(variableName).toString()) : BigInteger.ZERO;
    }

    public String[] substituteValues(String[] postfix) {
        String[] postfixWithValues = new String[postfix.length];
        for (int i = 0; i < postfix.length; i++) {
            if (postfix[i].matches(LATIN_LETTERS_REGEX)) {
                postfixWithValues[i] = String.valueOf(resolveTheValue(postfix[i]));
            } else {
                postfixWithValues[i] = postfix[i];
            }
        }
        return postfixWithValues;
    }

    public void updateTheMap(String input) {
        String[] variables = input.trim().split(ASSIGNMENT_DELIMITER_REGEX);
        String name = variables[0].trim();
        String value = variables[1].trim();

        if (value.matches(NUMBERS_REGEX)) {
            variablesMap.put(name, new BigInteger(value));
        }
        if (value.matches(LATIN_LETTERS_REGEX)) {
            if (variablesMap.containsKey(value)) {
                variablesMap.put(name, variablesMap.get(value));
            } else {
                System.out.println(UNKNOWN_VARIABLE);
            }
        }
    }

}
