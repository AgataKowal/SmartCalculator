import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().trim();
        Deque<String> stack = new ArrayDeque<>();
        String current;
        String popped;
        boolean isBalanced = true;
        try {
            for (int i = 0; i < input.length(); i++) {
                current = String.valueOf(input.charAt(i));
                if (current.matches("[({\\[]")) {
                    stack.push(current);
                }
                if (current.matches("[)}\\]]")) {
                    popped = stack.pop();
                    if ("(".equals(popped) && !")".equals(current)) {
                        isBalanced = false;
                    } else if ("{".equals(popped) && !"}".equals(current)) {
                        isBalanced = false;
                    } else if ("[".equals(popped) && !"]".equals(current)) {
                        isBalanced = false;
                    }
                }
            }
        } catch (NoSuchElementException e) {
            isBalanced = false;
        }
        if (!stack.isEmpty()) {
            isBalanced = false;
        }

        System.out.println(isBalanced);
    }
}