import java.io.*;
import java.util.Arrays;

class Field {
    private final static int size = 3;
    static int[][] field = new int[size + 1][size + 1];
    private static int[][] fieldOld = {{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    static Integer score = 0;


    private static void blockRandom() {
        int a = 0;
        int b = 4;
        int i = a + (int) (Math.random() * b);
        int j = a + (int) (Math.random() * b);
        while (field[i][j] != 0) {
            i = a + (int) (Math.random() * b);
            j = a + (int) (Math.random() * b);
        }
        field[i][j] = 2;
    }


    static void left() {
        for (int j = 0; j <= size; j++) {
            for (int k = 0; k < size - 1; k++) {
                for (int i = 0; i < size; i++) {
                    if (field[i][j] == 0) {
                        field[i][j] = field[i + 1][j];
                        field[i + 1][j] = 0;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (field[i][j] == field[i + 1][j]) {
                    field[i][j] += field[i + 1][j];
                    field[i + 1][j] = 0;
                    score += field[i][j];
                    i++;
                }
            }
            for (int i = 0; i < size; i++) {
                if (field[i][j] == 0) {
                    field[i][j] = field[i + 1][j];
                    field[i + 1][j] = 0;
                }
            }
        }
        conditionBlock();
    }

    static void right() {
        for (int j = 0; j <= size; j++) {
            for (int k = 0; k < size - 1; k++) {
                for (int i = size; i > 0; i--) {
                    if (field[i][j] == 0) {
                        field[i][j] = field[i - 1][j];
                        field[i - 1][j] = 0;
                    }
                }
            }
            for (int i = size; i > 0; i--) {
                if (field[i][j] == field[i - 1][j]) {
                    field[i][j] += field[i - 1][j];
                    field[i - 1][j] = 0;
                    score += field[i][j];
                    i--;
                }
            }
            for (int i = size; i > 0; i--) {
                if (field[i][j] == 0) {
                    field[i][j] = field[i - 1][j];
                    field[i - 1][j] = 0;
                }
            }
        }
        conditionBlock();
    }

    static void up() {
        for (int i = 0; i <= size; i++) {
            for (int k = 0; k < size - 1; k++) {
                for (int j = 0; j < size; j++) {
                    if (field[i][j] == 0) {
                        field[i][j] = field[i][j + 1];
                        field[i][j + 1] = 0;
                    }
                }
            }
            for (int j = 0; j < size; j++) {
                if (field[i][j] == field[i][j + 1]) {
                    field[i][j] += field[i][j + 1];
                    field[i][j + 1] = 0;
                    score += field[i][j];
                    j++;
                }
            }
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 0) {
                    field[i][j] = field[i][j + 1];
                    field[i][j + 1] = 0;
                }
            }
        }
        conditionBlock();
    }

    static void down() {
        for (int i = 0; i <= size; i++) {
            for (int k = 0; k < size - 1; k++) {
                for (int j = size; j > 0; j--) {
                    if (field[i][j] == 0) {
                        field[i][j] = field[i][j - 1];
                        field[i][j - 1] = 0;
                    }
                }
            }
            for (int j = size; j > 0; j--) {
                if (field[i][j] == field[i][j - 1]) {
                    field[i][j] += field[i][j - 1];
                    field[i][j - 1] = 0;
                    score += field[i][j];
                    j--;
                }
            }
            for (int j = size; j > 0; j--) {
                if (field[i][j] == 0) {
                    field[i][j] = field[i][j - 1];
                    field[i][j - 1] = 0;
                }
            }
        }
        conditionBlock();
    }

    private static void conditionBlock() {
        if (!Arrays.deepEquals(fieldOld, field)) {
            blockRandom();
            for (int i = 0; i <= size; i++)
                System.arraycopy(field[i], 0, fieldOld[i], 0, size + 1);
        }
    }

    static boolean winCheck(int[][] field) {
        for (int i = 0; i <= size; i++)
            for (int j = 0; j <= size; j++)
                if (field[i][j] == 2048) {
                    return true;
                }
        return false;
    }

    static int reader() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input/record.txt"));
        int record;
        record = Integer.parseInt(reader.readLine());
        reader.close();
        return record;
    }

    static void writer(int record) throws IOException {
        File file = new File("input/record.txt");
        file.delete();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(String.valueOf(record));
        writer.close();
    }
}