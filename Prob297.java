package euler;

import java.util.*;

public class Prob297 {
//    private static final long MAX = 99999999999999999L;

//    private static final long LAST =  99194853094755497L;
    private static final long LAST =  34L;

    private static final long MAX = 100L;
//    private static final long MAX = 805146905244503L;
    static List<Long> allFibs = new ArrayList<>();
    static List<Long> allZecks = new ArrayList<>();

    static Map<Long, Long> map = new TreeMap<>();
    static Map<Long, Long> mapPrev = new HashMap<>();

    static Map<Long, Long> zecksMap = new HashMap<>();

    public static long last = 1;

    public static final long NUM = 92;

    public static void main(String[] args) {
        calcFibs();
        allZecks.add(1L);
        allZecks.add(1L);
        allZecks.add(3L);
        for (int i = 3; i < allFibs.size(); i++) {
                long l = allZecks.get(i-1) + allZecks.get(i-2) + allFibs.get(i-3);
            allZecks.add(l);
//            if (allFibs.get(i) >= LAST)
//                break;
        }
        long sum = 0;
        zecksMap.put(1L, 1L);
        for (int i = 0; i < allZecks.size() - 1; i++) {
//            if (allFibs.get(i) >= LAST)
//                break;
            sum+= allZecks.get(i);

            zecksMap.put(allFibs.get(i+1), sum + 1);
        }

        long sum2 = 0;
        long num = 100_000_000_000_000_000L - 1L;
//        long num = 1_000_000L - 1L;
//        long num = 99L;
        System.out.println(num);
        for (int i = allFibs.size() - 1; i > 0 ; i--) {
            long ll = zecksMap.get((allFibs.get(i)));
            if (ll < 0)
                continue;
            if (num - allFibs.get(i) > 0) {
                sum2 += ll;
                long diff = num - allFibs.get(i);
                sum2 += diff;
                if (zecksMap.get(diff) != null) {
                    sum2 += zecksMap.get(diff);
                    System.out.println("Found - " + sum2);
                    System.exit(1);
                } else {
                    num = diff;
                }
            }
        }



        System.out.println(allFibs);
        System.out.println(allZecks);
        System.out.println(zecksMap);
//        System.out.println(zeck1(6));
        if (1 == 1)
            System.exit(1);
//        System.out.println(mapPrev.size());
//        System.out.println(zeck1(14));
//        System.out.println(zeck1(100));

        int sum1 = 0;

        System.out.println("Starting.. ");
        for (long i = 1; i <= MAX; i++) {
//            if (sum < 0 ) {
//                System.out.println("Not again");
//                break;
//            }
            sum1 += zeck1(i);
            System.out.println(i + ": " +sum1);
        }
//        System.out.println(map);
//        printMap(map);
        System.out.println(sum1);
    }

    private static void calcFibs() {
        long a0 = 1;
        long a1 = 1;
        long oldA = 1;

        for (long i = 0; i < NUM; i++) {
            allFibs.add(a0);
            map.put(a0, 1L);
//            for (long j = a1+1; j < a0; j++) {
//                mapPrev.put(j, a1);
//            }
            a1 = a0;
            a0 += oldA;
            oldA = a1;
            if (a0 < 0) {
                System.out.println("whoops " + i);
                break;
            }
        }

    }

    private static long zeck(long n) {
        long originN = n;
        long count = 0;
        ListIterator<Long> iter = (ListIterator<Long>) allFibs.listIterator();
        while (iter.hasNext()) {
            Long cur = iter.next();
            if (cur > n) {
//                cur = iter.previous();
//                n -= cur;
//                count++;
                while (iter.hasPrevious()) {
                    cur = iter.previous();
                    if (cur <= n) {
                        n -= cur;
                        count++;
                    }
                    if (n == 0) {
                        map.put(originN, count);
                        return count;
                    }
                }
            }
        }
        return count;
    }

    private static long zeck1(long n) {
//        if (1 == 1) return 1;
        long originN = n;
        long count = 1;
        long oldCur = 0L, cur = 0L;
        if (allFibs.contains(n)) {
            last = n;
            return 1;
        }
        n -= last;
        count = map.get(n) + 1;
        map.put(originN, count);
        return count;


    }

    private  static void printMap(Map<Long, Long> map) {
        for (Map.Entry<Long, Long> e : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < e.getValue(); i++) {
                sb.append("   ");
            }
            System.out.println(sb.toString() + e.getKey() + " = " + e.getValue());
        }
    }
}
//9999999   92359637
//999999    7894453
//99999     658203

//1426232787182267081
//6230210745864024
//2314682907575685