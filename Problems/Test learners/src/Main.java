import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        String output;
        switch (input) {
            case 1:
                output = "Yes!";
                break;
            case 2:
            case 3:
            case 4:
                output = "No!";
                break;
            default:
                output = "Unknown number";
        }
        System.out.println(output);
    }
}