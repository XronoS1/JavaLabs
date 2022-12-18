import java.util.Scanner;

public class Primes2 {

    public static void main(String[] args) {
        boolean s = true;
        Scanner sc = new Scanner(System.in);

        while (s) {  // зацикливание ввода, так как s True
            System.out.print("Enter your number >> ");
            int n = sc.nextInt(); // ввод пользователем числа
            if (n==0){ // выход из программы(цикла)
                break;
            }

            if (IsPrime(n)) {
                System.out.println("Number " + n + " is prime");
            } else {
                System.out.println("Number " + n + " is not prime");
            }

        }



    }
    public static boolean IsPrime(int n) {
        boolean check = true;
        for(int j = 2; j < n; j++) {
            if(n % j == 0) {
                check = false;
                break;
            }
        }
        return check;
    }

}
