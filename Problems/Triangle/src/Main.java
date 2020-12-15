import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        String answer = "NO";
        if (a + b > c && a + c > b && b + c > a) {
            answer = "YES";
        }
        System.out.println(answer);
    }
}