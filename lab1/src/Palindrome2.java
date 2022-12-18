import java.util.Scanner;

public class Palindrome2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your string to check for palindrome>> ");
        String f = scanner.nextLine();
        String[] words = f.split(" ");
        for (String s : words)

        if (isPalindrome(s)){
            System.out.println(s + " is palindrome");
        }

    }


    public static String reverseString(String s) {
        String res = "";
        int length = s.length();
        for (int i = length-1; i >= 0; i--) {
            res = res + s.charAt(i);
        }
        return res;
    }


    public static boolean isPalindrome(String s) {
        boolean check = true;
        if (!s.equals(reverseString(s))) {
            check = false;
            System.out.println(s + " NOT palindrome");
        }
        return check;
    }

}