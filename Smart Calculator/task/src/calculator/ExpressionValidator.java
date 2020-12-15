package calculator;



public class ExpressionValidator {

    public static final String INVALID_IDENTIFIER = "Invalid identifier";
    public static final String INVALID_ASSIGNMENT = "Invalid assignment";
    public static final String INVALID_EXPRESSION = "Invalid expression";

    public static final String ASSIGNMENT_DELIMITER_REGEX = "\\s*=\\s*";
    public static final String LATIN_LETTERS_REGEX = "[A-Za-z]+";
    public static final String NUMBERS_REGEX = "-?+[\\d]+";

    public boolean isValid(String input, Type type) {
        switch(type){
            case ASSIGNMENT:
                return isValidVariableAssignment(input);
            case EXPRESSION:
                return validateParentheses(input) && isValidExpression(input);
        }
        return false;
    }

    private boolean isValidVariableAssignment(String input) {
        boolean isValid = false;
        if (input.contains("=")) {
            String[] variables = input.trim().split(ASSIGNMENT_DELIMITER_REGEX);
            String name = variables[0];
            String nameOrValue = variables[1];
            if (variables.length == 2) {
                if (name.matches(LATIN_LETTERS_REGEX)) {
                    if (nameOrValue.matches(NUMBERS_REGEX) || nameOrValue.matches(LATIN_LETTERS_REGEX)) {
                        isValid = true;
                    } else {
                        System.out.println(INVALID_IDENTIFIER);
                    }
                } else {
                    System.out.println(INVALID_IDENTIFIER);
                }
            } else {
                System.out.println(INVALID_ASSIGNMENT);
            }
        } else {
            isValid = true;
        }
        return isValid;
    }

    private boolean validateParentheses(String input) {
        int openingCounter = 0;
        int closingCounter = 0;
        boolean hasValidParentheses = true;
        String current;
        for (int i = 0; i < input.length(); i++) {
            current = String.valueOf(input.charAt(i));
            if ("(".equals(current)) {
                openingCounter += 1;
            }
            if (")".equals(current)) {
                closingCounter += 1;
            }
            if (openingCounter == 0 && closingCounter > 0) {
                hasValidParentheses = false;
                System.out.println(INVALID_EXPRESSION);
            }
        }
        if (openingCounter != closingCounter) {
            hasValidParentheses = false;
            System.out.println(INVALID_EXPRESSION);
        }
        return hasValidParentheses;
    }

    private boolean isValidExpression(String input) {
        boolean isValidExpression = !input.matches("(.*)((/{2,})|(\\*{2,})|(\\^{2,}))(.*)");
        if (!isValidExpression) {
            System.out.println(INVALID_EXPRESSION);
        }
        return isValidExpression;
    }
}
