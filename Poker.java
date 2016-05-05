import java.util.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: mickeys
 * Date: Jul 20, 2015
 * Time: 5:17:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Poker {

    String s = gett();
    String s1= s.toString();

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
    String gett() {
        return "ff";
    }
    static void sortMe(String[] spl) {
        Arrays.sort(spl, new Comparator() {
                //'T','J','Q','K','A'
            public int compare(Object o1, Object o2) {
                return Poker.compare( String.valueOf(o1).charAt(0), String.valueOf(o2).charAt(0));
            }
        });

    }

    public static void main (String [] argv) {
        print("hello world");

        Boolean b = false;
        print (b == Boolean.FALSE);
        System.exit(0);

        File file = new File("C:\\Users\\mickeys\\Downloads\\p054_poker.txt");
        List<String> allHands = new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file) );
            String str = reader.readLine();
            while (str != null) {
                allHands.add(str);
                str = reader.readLine();
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        int n = 0;
        for (String s : allHands) {
            String hand1 = s.substring(0, 15);
            String hand2 = s.substring(15,29);
            if (isHand1Win(hand1, hand2)) {
                n++;
                print(hand1 + " : " + hand2 + " - hand1 wins!");
            }
            else {
                print(hand1 + " : " + hand2 + " - hand2 wins!");
            }
        }
        print(" n = " + n);
//        print(getMaxSeq("4S 4S 4S 4S JS"));
//        print(getMaxSeq("TS 4S 4S 4S JS"));
//        print(getMaxSeq("4S 3S 4S 4S JS"));
//        print(getMaxSeq("4S 3S TS 4S JS"));
//        print(getMaxSeq("4S TS TS 4S TS"));
//        print(getMaxSeq("4S TS 6S 4S TS"));

//        String[] ss = "AS TS KS QS JS".split(" ");
//
//        print(ss); //false
//        sortMe(ss);
//        print(ss); //false
//
//
//

//        print(isStraightFlush("AS TS KS QS JS"));
//        print(isStraightFlush("1S 2S 3S 4S 5S"));
//        print(isStraightFlush("5S 6S 4S 7S 8S"));
//        print(isStraightFlush("8S 9S TS JS QS"));
//        print(isStraightFlush("8S 9S TS JS 4S"));
//        print(isStraightFlush("8S 9S TD JS QS"));
//        print(isStraightFlush("1S 2S 3S 4S 5S"));
//        print(isStraightFlush("4S 5S 6S 7S 8S"));
//        print(isStraightFlush("8S 9S TS JS QS"));
//        print(isStraightFlush("8S 9S TS JS 4S"));
//        print(isStraightFlush("8S 9S TD JS QS"));
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

        print("Finished");
    }

    static boolean isHand1Win(String hand1, String hand2 ) {
        if (isRoyalFlush(hand1))
            return true;
        if (isRoyalFlush(hand2))
            return false;
        if (isStraightFlush(hand1))
            return true;
        if (isStraightFlush(hand2))
            return false;
        if (getMaxSeq(hand1)[0] == 4 || getMaxSeq(hand1)[1] == 4)
            return true;
        if (getMaxSeq(hand2)[0] == 4 || getMaxSeq(hand2)[1] == 4)
            return false;
        if ((getMaxSeq(hand1)[0] == 3 && getMaxSeq(hand1)[1] == 2) ||
            (getMaxSeq(hand1)[1] == 3 && getMaxSeq(hand1)[0] == 2))
            return true;
        if ((getMaxSeq(hand2)[0] == 3 && getMaxSeq(hand2)[1] == 2) ||
            (getMaxSeq(hand2)[1] == 3 && getMaxSeq(hand2)[0] == 2))
            return true;
        if (isFlush(hand1))
           return true;
        if (isFlush(hand2))
            return false;
        if (isStraight(hand1))
            return true;
        if (isStraight(hand1))
            return false;
        if (getMaxSeq(hand1)[0] == 3 || getMaxSeq(hand1)[1] == 3)
            return true;
        if (getMaxSeq(hand2)[0] == 3 || getMaxSeq(hand2)[1] == 3)
            return false;
        if (getMaxSeq(hand1)[0] == 2 && getMaxSeq(hand1)[1] == 2)
            return true;
        if (getMaxSeq(hand2)[0] == 2 && getMaxSeq(hand2)[1] == 2)
            return false;
        if (getMaxSeq(hand1)[0] == 2 || getMaxSeq(hand1)[1] == 2)
            return true;
        if (getMaxSeq(hand2)[0] == 2 || getMaxSeq(hand2)[1] == 2)
            return false;

        return (compare(getHighestCard(hand1), getHighestCard(hand2)) > 0);
    }

    static char getHighestCard (String hand) {
        String spl[] = hand.split(" ");
        char max = spl[0].charAt(0);
        for (int i = 1; i <spl.length ; i++) {
            if (compare(spl[i].charAt(0),max) > 0)
                max = spl[i].charAt(0);
        }
        return max;
    }

    //3S 4S 5S 6S 6D
    static int[] getMaxSeq(String hand) {
        String spl[] = hand.split(" ");
        sortMe(spl);
        int num1 = 1;
        int num2 = 1;
        char x = spl[0].charAt(0);
        for (int i = 1; i <spl.length ; i++) {
            char nextX = spl[i].charAt(0);
            if (x == nextX) {
                num1++;
//                if (num1 >= n1)
//                    return true;
            }
            else {
                if (num1 > 1)
                    num2 = num1;
                num1 = 1;
            }

            x= nextX;
        }
        return new int[] {num1, num2};
        //return (num1 >= n1);
    }

    static boolean isStraightFlush(String hand) {

        return (isFlush(hand) && isStraight(hand));

    }

    //3S 4S 5S 6S 7S
    static boolean isStraight(String hand) {
        String spl[] = hand.split(" ");

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
        String spl[] = hand.split(" ");
        sortMe(spl);

        return (isFlush(hand) &&
                spl[0].charAt(0) == 'T' &&
                spl[1].charAt(0) == 'J' &&
                spl[2].charAt(0) == 'Q' &&
                spl[3].charAt(0) == 'K' &&
                spl[4].charAt(0) == 'A'
        );

    }

    static boolean isFlush(String hand) {
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


    public static void print(Object s) {
        if (s.getClass().isArray()) {
            System.out.println(Arrays.toString((int[]) s));
        }
        else {
            System.out.println(s);
        }
    }

}
