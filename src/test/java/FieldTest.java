import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
   private int[][] fieldTest = new int[4][4];
 private Field field = new Field();

    @Test
    void left() {
       Field.field = new int[][] {{2, 4, 32, 128}, {0, 4, 0, 0}, {0, 8, 32, 0}, {2, 4, 0, 128}};
       fieldTest = new int[][] {{4, 8, 64, 256}, {0, 8, 0, 0}, {0, 4, 0, 0}, {0, 0, 0, 0}};
       field.left();
       assertArrayEquals(fieldTest, Field.field);
    }

    @Test
    void right() {
       Field.field = new int[][] {{2, 4, 32, 128}, {0, 4, 0, 0}, {0, 8, 32, 0}, {2, 4, 0, 128}};
       fieldTest = new int[][] {{0, 0, 0, 0}, {0, 8, 0, 0}, {0, 8, 0, 0}, {4, 4, 64, 256}};
       field.right();
       assertArrayEquals(fieldTest, Field.field);
    }

    @Test
    void up() {
       Field.field = new int[][] {{2, 0, 0, 2}, {0, 4, 4, 0}, {0, 8, 32, 0}, {2, 128, 0, 128}};
       fieldTest = new int[][] {{4, 0, 0, 0}, {8, 0, 0, 0}, {8, 32, 0, 0}, {2, 256, 0, 0}};
       field.up();
       assertArrayEquals(fieldTest, Field.field);
    }

    @Test
    void down() {
       Field.field = new int[][] {{2, 0, 0, 2}, {0, 4, 4, 0}, {0, 8, 32, 0}, {2, 128, 0, 128}};
       fieldTest = new int[][] {{0, 0, 0, 4}, {0, 0, 0, 8}, {0, 0, 8, 32}, {0, 0, 2, 256}};
       field.down();
       assertArrayEquals(fieldTest, Field.field);
    }

    @Test
    void winCheck() {
       Field.field = new int[][] {{2, 2048, 0, 2}, {0, 4, 4, 0}, {0, 8, 32, 0}, {2, 128, 0, 128}};
       fieldTest = new int[][] {{0, 0, 0, 4}, {0, 0, 0, 8}, {0, 0, 8, 32}, {0, 0, 2, 256}};
       assertFalse(field.winCheck(fieldTest));
       assertTrue(field.winCheck(Field.field));
    }

}