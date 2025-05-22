
import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    void test() {
        Point p1 = new Point(0, 5);
        Point p2 = new Point(0, 10);
        int result = Point.compareByAndThenY.compare(p1, p2);
        Assertions.assertTrue(result < 0);
    }

    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final static Comparator<Point> compareByAndThenY = Comparator.comparing(Point::getX).thenComparing(Point::getY);

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


    }
}
