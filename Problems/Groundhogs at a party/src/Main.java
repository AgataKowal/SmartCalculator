import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cups = scanner.nextInt();
        boolean isWeekend = scanner.nextBoolean();
        boolean answer = false;
        if (isWeekend && cups > 14 && cups < 26) {
            answer = true;
        }
        if (!isWeekend && cups > 9 && cups < 21) {
            answer = true;
        }
        System.out.println(answer);
    }
}