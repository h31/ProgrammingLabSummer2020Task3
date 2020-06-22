package sample;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;



public class Test2048 {

    @Test
    public void rotateTest() {
        int[][] a = new int[][] {{2, 4, 4, 0},
                {0, 0, 2, 2},
                {4, 4, 2, 2},
                {2, 2, 0, 4}};
        int[][] rotate1 = new int[][] {{0, 2, 2, 4},
                {4, 2, 2, 0},
                {4, 0, 4, 2},
                {2, 0, 4, 2}};
        int[][] rotate2 = new int[][] {{4, 0, 2, 2},
                {2, 2, 4, 4},
                {2, 2, 0, 0},
                {0, 4, 4, 2}};
        int[][] rotate3 = new int[][] {{2, 4, 0, 2},
                {2, 4, 0, 4},
                {0, 2, 2, 4},
                {4, 2, 2, 0}};

        Assertions.assertTrue(java.util.Arrays.deepEquals(rotate1, FieldDrawer.rotate(a)));
        Assertions.assertTrue(java.util.Arrays.deepEquals(rotate2, FieldDrawer.rotate(FieldDrawer.rotate(a))));
        Assertions.assertTrue(java.util.Arrays.deepEquals(rotate3,
                FieldDrawer.rotate(FieldDrawer.rotate(FieldDrawer.rotate(a)))));
        Assertions.assertTrue(java.util.Arrays.deepEquals(a,
                FieldDrawer.rotate(FieldDrawer.rotate(FieldDrawer.rotate(FieldDrawer.rotate(a))))));
    }

    @Test
    public void possibleTest() {
        int[][] a = new int[][] {{2, 4, 4, 0},
                {0, 0, 2, 2},
                {4, 4, 2, 2},
                {2, 2, 0, 4}};
        int[][] b = new int[][] {{2, 4, 4, 2},
                {8, 4, 2, 2},
                {4, 4, 2, 2},
                {2, 2, 8, 4}};
        int[][] c = new int[][] {{2, 4, 2, 4},
                {2, 4, 8, 2},
                {4, 2, 4, 8},
                {2, 4, 2, 4}};
        int[][] d = new int[][] {{2, 4, 8, 4},
                {16, 64, 16, 2},
                {32, 16, 4, 8},
                {2, 8, 2, 4}};
        Assertions.assertTrue(FieldDrawer.possible(a));
        Assertions.assertTrue(FieldDrawer.possible(b));
        Assertions.assertTrue(FieldDrawer.possible(c));
        Assertions.assertFalse(FieldDrawer.possible(d));
    }
}
