package caesarcipher;

public class Mathematical {

    public static int findGCD(int a, int b) {
        int temp;
        while (b != 0) {
            temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    public static String readMatrixByRow(String[][] matrix, int rows, int columns) {
        String str = new String();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] != null) {
                    str += matrix[i][j];
                }
            }
        }
        return str;
    }

    public static String readMatrixByColumn(String[][] matrix, int rows, int columns) {
        String str = new String();
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] != null) {
                    str += matrix[i][j];
                }
            }
        }
        return str;
    }
}
