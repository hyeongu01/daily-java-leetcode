package algorithems;

public class Gcd {

    public static void main(String[] args) {
        System.out.println(func(25, 30));
        System.out.println(func(888, 220));
        System.out.println(recursiveFunc(25, 30));
        System.out.println(recursiveFunc(888, 220));
    }

    public static int func(int a, int b) {
        // 큰 수를 a 로 이동
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static int recursiveFunc(int a, int b) {
        return b == 0 ? a : recursiveFunc(b, a % b);
    }
}
