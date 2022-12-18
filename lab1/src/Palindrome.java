public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i].toLowerCase();
                System.out.println(isPalindrome(s));
        }


    }


    public static String reverseString(String s) {  //создание обратного слова для дальнейшей сверки на палиндром
        String res = "";
        int length = s.length();
        for (int i = length-1; i >= 0; i--) {
            res = res + s.charAt(i);
        }
        return res;
    }


    public static boolean isPalindrome(String s) { // метод проверки на палиндром
        boolean check = true;
        if (!s.equals(reverseString(s))) {
            check = false;
            System.out.println(s + " is NOT palindrome");
        }
        else if (s.equals(reverseString(s))){
                check = true;
                System.out.println(s + " is palindrome");
        }
        return check;
    }
}
