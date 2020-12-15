import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int min = Integer.MAX_VALUE;
        int currentNumber;
        for (int i = 0; i < n; i++) {
            currentNumber = scanner.nextInt();
            if (currentNumber < min) {
                min = currentNumber;
            }
        }
        System.out.println(min);
    }
}