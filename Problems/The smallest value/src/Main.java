import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger m = new BigInteger(scanner.next());
        long n = 1;
        BigInteger result = BigInteger.valueOf(n);
        while (result.compareTo(m) < 0) {
            n += 1;
            result = result.multiply(BigInteger.valueOf(n));
        }
        System.out.println(n);
    }
}