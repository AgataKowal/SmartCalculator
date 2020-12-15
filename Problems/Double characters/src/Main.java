import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char current;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            sb.append(current).append(current);
        }
        System.out.println(sb.toString());
    }
}