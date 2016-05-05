import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mickeys on 4/18/2016.
 */
public class Shapes {

    abstract public  class Shape {
            public Shape() {
                //Shape p = new Circle();
                System.out.println("in shape");
            }

            public List fun() throws Exception {
                return null;
            }
    }

        public  class Circle extends Shape {
            public Circle() {
                //super();
                System.out.println("in circle");
            }

            @Override
            public ArrayList fun() throws IOException {
                return null;
            }
        }

        public static void func() {
            try {
                System.out.println("Try 1");
                if (1==1)
                    throw new NullPointerException();
                //return;
                System.out.println("Try 2");
            }
            catch (Exception e) {
                System.out.println("Catch 1");
                if (1==1)
                    return;
                //System.exit(1);

                System.out.println("Catch 2");

            }
            finally {
                System.out.println("Finally 1");
                if (1==1)
                    return;

                System.out.println("Finally 2");
            }

        }

        public void tryInst() {
            Shape shape = new Circle();
        }

        public static void main(String[] s) {
            Shapes shapes = new Shapes();
            shapes.tryInst();

            Integer a = 3;
            Integer b = 3;
            System.out.println(a == b);
            func();
        }
}

