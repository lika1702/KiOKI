package caesarcipher;

public class RailFenceCipher {

    public String encrypt(String original, int key) {
        String[][] matrix = new String[key][original.length()];
        for (int row = 0; row < key; row++) {
            for (int column = row; column < original.length(); column = column + 2 * (key - 1)) {
                insert(matrix, row, column, original.charAt(column));
                int symm = column + 2 * (key - 1 - row);
                if (row != 0 && row != key - 1 && symm < original.length()) {
                    insert(matrix, row, symm, original.charAt(symm));
                }
            }
        }
        return Mathematical.readMatrixByRow(matrix, key, original.length());
    }
    
    public String decrypt(String ciphertext, int key) {
        String[][] matrix = new String[key][ciphertext.length()];
        for (int row = 0, i = 0; row < key; row++) {
            for (int column = row; column < ciphertext.length(); column = column + 2 * (key - 1), i++) {
                insert(matrix, row, column, ciphertext.charAt(i));
                int symm = column + 2 * (key - 1 - row);
                if (row != 0 && row != key - 1 && symm < ciphertext.length()) {
                    insert(matrix, row, symm, ciphertext.charAt(++i));
                }
            }
        }
        return Mathematical.readMatrixByColumn(matrix, key, ciphertext.length());
    }

    private void insert(String[][] matrix, int row, int column, char symb) {
        String character = new String();
        character += symb;
        matrix[row][column] = character;
    }
}
