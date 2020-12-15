import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        int counter = 1;
        int max = 1;
        for (int i = 0; i < size - 1; i++) {
            if (numbers[i] < numbers[i + 1]) {
                counter += 1;
            } else {
                if (counter > max) {
                    max = counter;
                }
                counter = 1;
            }
        }
        if (counter > max) {
            max = counter;
        }
        System.out.println(max);
    }
}