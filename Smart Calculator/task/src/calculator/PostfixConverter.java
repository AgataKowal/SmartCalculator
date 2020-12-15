package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class PostfixConverter {

    private static final String LATIN_LETTERS_REGEX = "[A-Za-z]+";
    private static final String NUMBERS_REGEX = "-?+[\\d]+";
    private static final String OPERATORS_REGEX = "[-+/*^]";

    private static final Map<String, Integer> PRIORITIES =
            Map.of("(", 0, "+", 1, "-", 1, ")", 1, "*", 2, "/", 2, "^", 3);

    private final ExpressionSimplifier expressionSimplifier;

    public PostfixConverter(ExpressionSimplifier expressionSimplifier) {
        this.expressionSimplifier = expressionSimplifier;
    }

    public String[] convertToPostfix(String input) {
        String[] members = expressionSimplifier.simplify(input);
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        for (String member : members) {
            if (isNumber(member) || isVariable(member)) {
                result.append(member).append(" ");
            } else if (isOperator(member)) {
                if (!stack.isEmpty() && PRIORITIES.get(member) <= PRIORITIES.get(stack.peek())) {
                    String topOperand = stack.peek();
                    while (topOperand != null && PRIORITIES.get(member) <= PRIORITIES.get(topOperand)) {
                        result.append(topOperand).append(" ");
                        stack.poll();
                        topOperand = stack.peek();
                    }
                }
                stack.push(member);
            } else if ("(".equals(member)) {
                stack.push(member);
            } else if (")".equals(member)) {
                String temp = stack.poll();
                while (!"(".equals(temp)) {
                    result.append(temp).append(" ");
                    temp = stack.poll();
                }
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.poll()).append(" ");
        }
        return result.toString().trim().split(" ");
    }

    private boolean isOperator(String member) {
        return member.matches(OPERATORS_REGEX);
    }

    private boolean isVariable(String member) {
        return member.matches(LATIN_LETTERS_REGEX);
    }

    private boolean isNumber(String member) {
        return member.matches(NUMBERS_REGEX);
    }
}
