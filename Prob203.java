package euler;

import java.math.BigInteger;
import java.util.*;

public class Prob203 {
    static Set<Long> set = new TreeSet<>();// new HashSet<>();
    static int NUM = 51;

    public static void main(String[] args) {
        System.out.println(fact(7));

        int[]squares = new int[5000];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = (i + 2) * (i + 2);
        }
        System.out.println(Arrays.toString(squares));
        for (int i = 0 ; i < NUM; i++) {
            binom(i);
//            System.out.println(i + ":" + fact(i));
        }
        System.out.println(set);

        Iterator<Long> iter = set.iterator();
        while (iter.hasNext()) {
            Long lng = iter.next();
            for (int sq : squares) {
                if (lng % sq == 0) {
                    iter.remove();
                    break;
                }
                if (sq > lng)
                    break;
            }
        }

        System.out.println(set);

        long sum = 0;
        iter = set.iterator();
        while (iter.hasNext()) {
            Long lng = iter.next();
            sum += lng;
        }
        System.out.println("sum = " + sum);
        System.out.println(Long.MAX_VALUE);
    }
//184671130
//184671181
    private static BigInteger fact(int n) {
//        if (n > 20) {
            BigInteger bigInteger = new BigInteger("1");
            for (int i = 1; i <= n; i++) {
                bigInteger = bigInteger.multiply(new BigInteger("" + i));
            }
            return bigInteger;
//        }


//        long t = 1;
//        for (long i = 1; i <= n; i++) {
//            t *= i;
//        }
//        return  t;
    }

    private  static void binom(int n) {
        for (int k = 0; k < n; k++) {
            long cur =  fact(n).divide(fact(k).multiply(fact(n - k))).longValue();
            set.add(cur);
        }
    }
}
