public class Primes {

    public static void main(String[] args) {
        for (int n = 3; n <= 100; n++){
            if(isPrime(n)){  // если число прошло проверку на простоту, то оно будет выведено
                System.out.println(n);
            }
            else{
                System.out.println(n + " не простое");
            }
        }
    }
        public static boolean isPrime(int n) {
            //boolean check = true;  // ввод булевой переменной переменной для функции проверки на простое число
            for(int j = 2; j < n; j++) { // i выступает делителем для диапазона чисел
                if(n % j == 0){ // если остаток от числа равен 0, то число не будет выводиться, так как оно не простое
                    return false;
                }
            }
            return true;
        }


}

