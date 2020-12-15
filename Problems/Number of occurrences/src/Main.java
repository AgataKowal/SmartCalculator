import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        String substring = scanner.nextLine().trim();
        int index = 0;
        int counter = 0;
        while (index != -1) {
            index = input.indexOf(substring);
            if (index != -1) {
                counter += 1;
                input = input.replaceFirst(substring, "");
            }
        }
        System.out.println(counter);
    }
}