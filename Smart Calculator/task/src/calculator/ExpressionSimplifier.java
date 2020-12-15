package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ExpressionSimplifier {

    public static final String MULTIPLE_PLUS_REGEX = "\\+{2,}";
    public static final String MULTIPLE_MINUS_REGEX = "-{2,}";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String DOUBLE_MINUS = "--";

    public static final String LATIN_LETTERS_REGEX = "[A-Za-z]+";
    public static final String NUMBERS_REGEX = "-?+[\\d]+";


    public String[] simplify(String input) {
        input = removeExtraPluses(input);
        input = removeExtraMinuses(input);
        return splitTheData(input);
    }

    private String removeExtraPluses(String input) {
        return input.trim().replaceAll(MULTIPLE_PLUS_REGEX, PLUS);
    }

    private String removeExtraMinuses(String input) {
        String cleanedUpMinuses = input;
        int start = input.indexOf(DOUBLE_MINUS);
        int index = start;
        int counter = 0;
        while (start != -1) {
            if (cleanedUpMinuses.charAt(index) == '-') {
                counter += 1;
                index += 1;
            } else {
                if (counter % 2 == 0) {
                    cleanedUpMinuses = cleanedUpMinuses.replaceFirst(MULTIPLE_MINUS_REGEX, PLUS);
                } else {
                    cleanedUpMinuses = cleanedUpMinuses.replaceFirst(MULTIPLE_MINUS_REGEX, MINUS);
                }
                counter = 0;
                start = cleanedUpMinuses.indexOf(DOUBLE_MINUS);
                index = start;
            }
        }
        return cleanedUpMinuses;
    }

    private String[] splitTheData(String input) {
        String[] members = input.trim().replaceAll(" ", "").split("");
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        String previous = "";
        String current;
        for (String member : members) {
            current = member;
            if (isLetter(current)) {
                if (isLetter(previous)) {
                    sb.append(current);
                } else {
                    sb = new StringBuilder(current);
                }
            } else if (isNumber(current)) {
                if (isNumber(previous)) {
                    sb.append(current);
                } else {
                    sb = new StringBuilder(current);
                }
            } else {
                if (isLetter(previous) || isNumber(previous)) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                stack.push(current);
            }
            previous = current;
        }
        if (!"".equals(sb.toString())) {
            stack.push(sb.toString());
        }
        Iterator<String> iterator = stack.descendingIterator();
        String[] output = new String[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            output[i] = iterator.next();
        }
        return output;
    }

    private boolean isLetter(String current) {
        return current.matches(LATIN_LETTERS_REGEX);
    }

    private boolean isNumber(String current) {
        return current.matches(NUMBERS_REGEX);
    }
}
