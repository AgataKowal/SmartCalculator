package calculator;

import java.math.BigInteger;
import java.util.*;


public class Calculator {

    public static final String NUMBERS_REGEX = "-?+[\\d]+";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        TypeResolver typeResolver = new TypeResolver();
        VariablesHandler handler = new VariablesHandler();
        ExpressionValidator validator = new ExpressionValidator();
        PostfixConverter converter = new PostfixConverter(new ExpressionSimplifier());

        Type type;
        boolean shouldExit = false;

        while (true) {
            type = typeResolver.resolve(input);
            switch (type) {
                case ASSIGNMENT:
                    if (validator.isValid(input, type)) {
                        handler.updateTheMap(input);
                    }
                    break;
                case COMMAND:
                    Command command = Command.valueOfCommand(input);
                    command.printDescription();
                    shouldExit = command.shouldExit();
                    break;
                case EMPTY:
                    break;
                case EXPRESSION:
                    if (validator.isValid(input, type)) {
                        String[] postfix = converter.convertToPostfix(input);
                        String[] postfixWithValues = handler.substituteValues(postfix);
                        BigInteger result = calculateExpression(postfixWithValues);
                        System.out.println(result.toString());
                    }
                    break;
                case NUMBER:
                    System.out.println(input);
                    break;
                case VARIABLE:
                    handler.printVariable(input);
                    break;
                case INVALID:
                    System.out.println("Invalid input");
                    break;
            }
            if (shouldExit) {
                break;
            }
            input = scanner.nextLine().trim();
        }
    }

    private static BigInteger calculateExpression(String[] input) {
        Deque<BigInteger> stack = new ArrayDeque<>();
        String current;
        BigInteger first;
        BigInteger second;
        for (String s : input) {
            current = s;
            if (isNumber(current)) {
                stack.push(new BigInteger(current));
            } else {
                first = stack.pop();
                second = stack.pop();
                switch (current) {
                    case "+":
                        stack.push(first.add(second));
                        break;
                    case "-":
                        stack.push(second.subtract(first));
                        break;
                    case "*":
                        stack.push(first.multiply(second));
                        break;
                    case "/":
                        stack.push(second.divide(first));
                        break;
                    case "^":
                        stack.push(second.pow(first.intValue()));
                        break;
                }
            }
        }
        return stack.pop();
    }

    private static boolean isNumber(String current) {
        return current.matches(NUMBERS_REGEX);
    }
}
