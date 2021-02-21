package euler;

import java.util.*;

public class Prob201 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1 ; i < 101 ; i++ ) {
            sum += i * i;
        }
        System.out.println(sum);

        System.out.println(perm( new int[] {1,2,3,4,5},2));
    }

    static int[] FINAL_ARR = new int[100];
    static int idx = 0;

    private static int perm(int[] arr , int n) {
        int sum = 0;
        if (n == 0)
            return 0;
        int[]b = new int[arr.length - 1];
        for (int i = 0 ; i < arr.length - n ; i++) {
            System.arraycopy(arr, i+1, b,0, arr.length - i - 1);
            return arr[i] + perm(b, n-1);
        }
        return  sum;
    }

    private static List perm1(int[] arr , int n) {
        List<Integer> list = null;
        int sum = 0;
        if (n == 0)
            return new ArrayList();
        int[]b = new int[arr.length - 1];
        for (int i = 0 ; i < arr.length - n ; i++) {
            System.arraycopy(arr, i+1, b,0, arr.length - i - 1);
            list = perm1(b, n-1);
            list.add(arr[i]);
        }
        return  list;
    }
}


//338350