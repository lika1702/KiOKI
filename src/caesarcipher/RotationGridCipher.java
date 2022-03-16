package caesarcipher;

public class RotationGridCipher {

    public String encrypt(String original) {
        String[][] cipherMatrix = new String[4][4];
        original = prepareText(original, 4);
        for (int k = 0; k < 4; k++) {
            if (4 * k != 16) {
                cipherMatrix[0][0] = String.valueOf(original.charAt(4 * k));
                cipherMatrix[1][3] = String.valueOf(original.charAt(4 * k + 1));
                cipherMatrix[2][2] = String.valueOf(original.charAt(4 * k + 3));
                cipherMatrix[3][1] = String.valueOf(original.charAt(4 * k + 2));
            }
            cipherMatrix = rotate(cipherMatrix, 4);
        }
        return Mathematical.readMatrixByRow(cipherMatrix, 4, 4);
    }

    public String decrypt(String ciphertext) {
        String[][] cipherMatrix = fillMatrix(ciphertext, 4);
        String original = "";
        for (int k = 0; k < 4; k++) {
            if (4 * k != 16) {
                original += cipherMatrix[0][0];
                original += cipherMatrix[1][3];
                original += cipherMatrix[3][1];
                original += cipherMatrix[2][2];
            }
            cipherMatrix = rotate(cipherMatrix, 4);
        }
        original = original.replaceAll("&", "");
        return original;
    }

    private String prepareText(String text, int size) {
        if (text.length() % (size * size) == 0) {
            return text;
        }
        int countSymb = size * size - text.length() % (size * size);
        for (int i = 0; i < countSymb; i++) {
            text += "&";
        }
        return text;
    }

    private String[][] rotate(String[][] matrix, int size) {
        String[][] newArray = new String[size][size];
        for (int j = 0, n = 0; j < size; j++, n++) {
            for (int i = size - 1, m = 0; i >= 0; i--, m++) {
                newArray[n][m] = matrix[i][j];
            }
        }
        return newArray;
    }

    private String[][] fillMatrix(String str, int size) {
        String[][] cipherMatrix = new String[size][size];
        for (int i = 0, elem = 0; i < size; i++) {
            for (int j = 0; j < size; j++, elem++) {
                cipherMatrix[i][j] = String.valueOf(str.charAt(elem));
            }
        }
        return cipherMatrix;
    }
}
