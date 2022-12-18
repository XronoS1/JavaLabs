public class Palindrome3 {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i].toLowerCase();

            // Проверяем, является ли строка палиндромом
            // и сохраняем результат проверки в переменную result
            boolean result = isPalindrome(s);

            if (result) {
                System.out.println(s + " is palindrome");

            } else {
                // Тут то же самое, что и в предыдущем пункте, но вместо "да" в этом блоке у нас обратное - то есть "нет"
                System.out.println(s + " is NOT palindrome");
            }
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
        // Проверяем, равна ли строка отраженной строке
        if (s.equals(reverseString(s))) {
            return true;

        } else {
            return false;
        }
    }
}
