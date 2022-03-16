package caesarcipher;

public class KeyPhraseCipher {

    public String encrypt(String original, String keyWord) {
        int[] sequence = generateSequence(keyWord);
        int rowCount = countRow(original, keyWord);
        String ciphertext = new String();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < sequence.length; j++) {
                if (sequence[j] - 1 < original.length()) {
                    ciphertext += original.charAt(sequence[j] - 1);
                }
            }
        }
        return ciphertext;
    }

    public String decrypt(String ciphertext, String keyWord) {
        int[] sequence = generateSequence(keyWord);
        int rowCount = countRow(ciphertext, keyWord);
        String[] original = new String[ciphertext.length()];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < sequence.length; j++) {
                if (j < ciphertext.length() && sequence[j] - 1 + i * sequence.length < ciphertext.length()) {
                    original[sequence[j] - 1 + i * sequence.length] = String.valueOf(ciphertext.charAt(j));
                }
            }
        }
        return toString(original);
    }

    private int[] generateSequence(String keyWord) {
        int[] sequence = new int[keyWord.length()];
        int symbol = 1;
        for (int c = 0; c <= 255; c++) {
            for (int i = 0; i < keyWord.length(); i++) {
                if (keyWord.charAt(i) == c) {
                    sequence[i] = symbol;
                    symbol++;
                }
            }
        }
        return sequence;
    }

    private int countRow(String original, String keyWord) {
        if (original.length() % keyWord.length() == 0) {
            return original.length() / keyWord.length();
        }
        return original.length() / keyWord.length() + 1;
    }

    private String toString(String[] mas) {
        String str = "";
        for (String symb : mas) {
            str += symb;
        }
        return str;
    }
}
