import java.util.*;
import java.math.BigDecimal;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: mickeys
 * Date: Jul 20, 2015
 * Time: 5:17:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EulerMain {

    static char[] cards = {'1','2','3','4','5','6','7','8','9','T','J','Q','K','A'};

    static int compare(char c1, char c2) {
        if (c1 == c2)
            return 0;
        for (int i = 0 ; i < cards.length ; i++) {
            if (cards[i] == c1)
                return -1;
            if (cards[i] == c2)
                return 1;
        }
        return 0;
    }

    static void sortMe(String[] spl) {
        Arrays.sort(spl, new Comparator() {
                //'T','J','Q','K','A'
            public int compare(Object o1, Object o2) {
                return EulerMain.compare( String.valueOf(o1).charAt(0), String.valueOf(o2).charAt(0));
            }
        });

    }

    public static void main (String [] argv) {
        print("hello world");
        long start = System.currentTimeMillis();
        prob131();
//        System.out.println(Math.sqrt(9) == (int)Math.sqrt(9));
//        System.out.println(Math.sqrt(8) == (int)Math.sqrt(8));
//        try {
//            removeDupFiles(argv[0]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //for (int i = 2; i < 100 ; i++)
//        if (isPrime(1000000))
//                print(1000000);
        //prob44();
        //prob31();
        //prob26();
//        for (int i = 1; i < 1000000 ; i++)
//                getFactors(i);
        //print(Arrays.toString( getFactors(i)));

        print("Finished in " + (System.currentTimeMillis() - start));
    }

    //3S 4S 5S 6S 7S
    static boolean isStraightFlush(String hand) {
        String spl[] = hand.split(" ");
        if (!allSameSuit(hand))
            return false;

        sortMe(spl);

        char x = spl[0].charAt(0);
        for (int i = 1;i < spl.length; i++) {
            char nextX = spl[i].charAt(0);

            if (Character.isDigit(x) && Character.isDigit(nextX) && nextX != (x + 1))
                return false;
            else if (Character.isDigit(x) && Character.isLetter(nextX) && (x != '9' || nextX != 'T'))
                return false;
            else if (Character.isLetter(x) && Character.isLetter(nextX)) {
                if (
                        (x == 'T' && nextX != 'J') ||
                        (x == 'J' && nextX != 'Q') ||
                        (x == 'Q' && nextX != 'K') ||
                        (x == 'K' && nextX != 'A')
                        )
                return false;
            }
            else if (Character.isLetter(x) && Character.isDigit(nextX))
                return false;

            x = spl[i].charAt(0);
        }
        return true;
    }

    //AS TS KS QS JS
    static boolean isRoyalFlush(String hand) {
//        if (hand.contains())
        String spl[] = hand.split(" ");
        sortMe(spl);

        char pack = spl[0].charAt(1);
        for (String s : spl) {
            char c1 = s.charAt(0);
            if (!isRoyal(c1))
                return false;
            char c2 = s.charAt(1);
            if (c2 != pack)
                return false;
        }
        return true;
    }

    static boolean allSameSuit(String hand) {
        String spl[] = hand.split(" ");
        char pack = spl[0].charAt(1);
        for (String s : spl) {
            char c2 = s.charAt(1);
            if (c2 != pack)
                return false;
        }
        return true;
    }

    static boolean isRoyal (char c) {
        return (Character.isLetter(c));
    }

    public static void  prob326() {
        int n = 10;
        int a[] = new int[n];
        for (int i = 1; i < n ;i++ ) {
            for (int k = 1; k < n-1 ;k++ ) {

            }
        }
    }


    public static void  removeDupFiles(String url) throws IOException {
        File file = new File(url);
        File[] files = file.listFiles();
            for (int i = 0 ; i<files.length ; i++) {
                for (int j = i+1 ; j<files.length ; j++) {
                    if (contentEquals(files[i], files[j])) {
                        files[j].delete();
                        print("Identical: " + files[i] + " = " + files [j]);
                    }
                }
            }

        //        FileUtils
    }

    public static boolean contentEquals(File file1, File file2) throws IOException {
        boolean file1Exists = file1.exists();
        if (file1Exists != file2.exists()) {
            return false;
        }

        if (!file1Exists) {
            // two not existing files are equal
            return true;
        }

        if (file1.isDirectory() || file2.isDirectory()) {
            // don't want to compare directory contents
            throw new IOException("Can't compare directories, only files");
        }

        if (file1.length() != file2.length()) {
            // lengths differ, cannot be equal
            return false;
        }

        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            // same file
            return true;
        }

        InputStream input1 = null;
        InputStream input2 = null;
        try {
            input1 = new FileInputStream(file1);
            input2 = new FileInputStream(file2);
            return _contentEquals(input1, input2);

        } finally {
            input1.close();
            input2.close();
        }
    }

    public static boolean _contentEquals(InputStream input1, InputStream input2)
            throws IOException {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }

        int ch = input1.read();
        while (-1 != ch) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }

        int ch2 = input2.read();
        return (ch2 == -1);
    }

    public void removeFiles(String url) {
        //String url = argv[0];   //c:\pics
        print(url);
        //go over all sub folder, for each sub folder,
        //rename all the files to include the folder name and then move them to the top folder
        boolean flag = true;
        File file = new File(url);
        while (flag) {
        File[] files = file.listFiles();
            flag = false;
            for (File subfile : files) {
                if (subfile.isDirectory()) {
                    for (File subsubfile : subfile.listFiles()) {
                        String dest = url + "\\" + subsubfile.getAbsolutePath().replace(url+"\\", "").replace("\\","_");
                            subsubfile.renameTo(new File(dest));
                        }
                    subfile.delete();
                    flag = true;
                }
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2 ; i <= n / 2; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void print(Object s) {
        if (s instanceof Object[]) {
            System.out.println(Arrays.asList(s));
        }
        else {
            System.out.println(s);
        }
    }

//    int[] arr = {200, 100, 50, 20, 10, 5, 2, 1};
//    static int idx = 0;
//    static int num;
//    public static int prob31(int n) {
//        if (n == 200) {
//            num++;
//            idx ++;
//            //prob31()
//        }
////        if (n > 200)
////            return;
//        if (n < 200)
//            return prob31(n + arr[idx]);
//
//    }

    //200 100 50 20 10 5 2 1
    public static void prob31() {
        int num = 0;
        int[] arr = {100, 50, 20, 10, 5, 2, 1};

        Stack<Integer> stack = new Stack<Integer>();
        int i,j,k;
        for (i = 0; i < arr.length; i++) {
            j = i;
            k = 100;
            int sum = 0;
            //while (j < arr.length){
            do {
              //  if (!stack.isEmpty())
              //      k = stack.peek();
                while (sum < 200){
                    sum += stack.push(arr[j]);
                    if (j < arr.length-1)
                         k = stack.peek();
                    //k++;
                }
                if (sum == 200) {
                    num++;
                    //print (stack);
                }

                int pop;
                do  {
                    pop = stack.pop();
                     if (stack.isEmpty())
                    break;
                    sum -= pop;
                } while (pop < k);

                boolean flag = false;
                while (j > 0 && arr[j-1] < k) {
                    j--;
                    flag = true;
                }
                if (stack.isEmpty())
                    break;
                k = stack.peek();
                //k--;
                if (j < arr.length-1 &&  !flag) {
                    j++;
                   // k++;
                }
//                else {
//                    sum -= stack.pop();
//                    j = k;
//                    k--;
//                }
            }
            while (!stack.isEmpty());
        }
        print(num + 1);
    }

//        Pentagonal numbers are generated by the formula, Pn=n(3n?1)/2. The first ten pentagonal numbers are:
//        1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
//        It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 ? 22 = 48, is not pentagonal.
//        Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and D = |Pk ? Pj| is minimised; what is the value of D?
    public static void prob44() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10000; i++)
            list.add(calcPentNr(i+1));

        for (int i = 0; i < 10000; i++)
            for (int j = i+1; j < 10000; j++)
            {
                if (list.contains(list.get(i) + list.get(j)) && list.contains(list.get(j) - list.get(i)))
                    print(list.get(j) - list.get(i));
            }
        //System.out.println(list);
    }
    public static int calcPentNr(int n) {
        return n*(3*n - 1) / 2;
    }

    public static int[] getFactors(int n) {
        int[] arr = new int[1000];
        int j = 0;
        for (int i = 1; i <= n ; i ++)
            if (n % i == 0)
                arr[j++] = i;
                //print (i);
        return arr;
    }

    public static void prob386() {
        int i;


    }

    public static void prob26() {
        BigDecimal d = new BigDecimal(2);
        int max = 0, max_d = 0;
        for (; d.intValue() < 1000; d = d.add(BigDecimal.ONE)) {
            BigDecimal res = BigDecimal.ONE.divide(d,5000, BigDecimal.ROUND_UP);
            String s = res.toString().substring(500, 4990);
            for (int n = 1; n <s.length() / 2; n++) {
                String str = s.substring(0, n);
                if (s.substring(n, 2 * n).equals(str)) {
                    //print (n);
              ///      print("d = " + d + ", n = " + n + " , " + res + " , " + s );
                    if (n > max) {
                        max = n;
                        max_d = d.intValue();
                    }
                    break;
                }
                if (n == s.length() / 2) {
                    print ("Oops " + d);
                }
                //if (s.indexOf(str, n+1))
            }
            //s.


        }
        print (max + " : " + max_d);

    }

    //3 7 109 673
    public static void prob60() {
        BigDecimal n = new BigDecimal(676);
        while (true) {
            n = n.add(BigDecimal.ONE);

            //if (isPrime(n.))
        }
    }

    public static void prob207() {
        int part = 0;
        int perf = 0;
        for (long i = 1; i < Long.MAX_VALUE; i++) {
            if (isPartitioned(i)) {
//                System.out.println(i + " is part.");
                part++;
                if (isPerfect(i)) {
//                    System.out.println(i + " is also perfect");
                    perf++;
                }
            }
            double res = (double)perf / part;
//            System.out.println("M (" + i + ") = " + perf + " / " + part + " which is: " + res);
            //System.out.println(i);
//            System.out.println(1.0/12345);
            if (res < 1.0/12345) {
                System.out.println("M = " + i);
                return;
            }

        }
//        System.out.println(" i = " + i + ", partitioned = " + isPartitioned(i) + ", perfect = " + isPerfect(i));

    }

    private static boolean isPartitioned(long k) {
        //return Math.sqrt(k * 4 + 1) == (int)Math.sqrt(k * 4 + 1);
        return isInt(Math.sqrt(k * 4 + 1));
    }

    private static boolean isPerfect(long k) {
        return isInt(Math.log((1+Math.sqrt(1 + 4 * k)) / 2) / Math.log(2));
    }

    private static boolean isInt(double d) {
        return d == (int)d;
    }

    public static void prob131() {
        int max = 100;
        List<Integer> primes = new ArrayList<Integer>();

//        int c = 0;
        for (int p = 2; p < max; p++) {
            if (isPrime(p)) {
                primes.add(p);
//                if (p > c) {
//                    System.out.println(p);
//                    c+= 10000;
//                }
            }
        }
        print (primes);
        System.out.println("Finished init");
        if (1==1) return;

        for (int k = 2; k <= 100 ; k++) {
            int k3 = k *k *k;
            for (int i = 0 ; i < primes.size() ; i++ ) {
                int p = primes.get(i);
                if (p >= k3) //2,3,5..
                    break;

            }
        }


//        print(primes);
    }
}
