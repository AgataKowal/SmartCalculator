package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeResolver {

    public Type resolve(String input) {
        if (isAssignment(input)) {
            return Type.ASSIGNMENT;
        } else if (isCommand(input)) {
            return Type.COMMAND;
        } else if (isEmpty(input)) {
            return Type.EMPTY;
        } else if (isExpression(input)) {
            return Type.EXPRESSION;
        } else if (isNumber(input)) {
            return Type.NUMBER;
        } else if (isVariable(input)) {
            return Type.VARIABLE;
        } else {
            return Type.INVALID;
        }
    }

    private boolean isAssignment(String input) {
        return input.contains("=");
    }

    private boolean isCommand(String input) {
        return input.matches("/\\w+");
    }

    private boolean isEmpty(String input) {
        return "".equals(input);
    }

    private boolean isExpression(String input) {
        Pattern pattern = Pattern.compile("[-+/^*]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private boolean isNumber(String input) {
        return input.matches("-?+[\\d]+");
    }

    private boolean isVariable(String input) {
        return input.matches("[A-Za-z]+");
    }
}
