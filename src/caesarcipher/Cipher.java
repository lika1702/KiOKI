package caesarcipher;

public class Cipher {

    public final int ENG = 26;
    public final int RUS = 32;

    public String encryptSimple(String original, int key, int lang) {
        String result = "";
        for (String symb : original.split("")) {
            int firstSymb = chooseFirstSymbol(symb, lang);
            int curPosition = symb.charAt(0) - firstSymb;
            int newPosition = (curPosition + key) % lang;
            result += String.valueOf((char)(firstSymb + newPosition));
        }
        return result;
    }

    public String encryptHard(String original, int key, int lang) {
        String result = new String();
        for (String symb : original.split("")) {
            int firstSymb = chooseFirstSymbol(symb, lang);
            int curPosition = symb.charAt(0) - firstSymb;
            int newPosition = (curPosition * key) % lang;
            result += (char) (firstSymb + newPosition);
        }
        return result;
    }

    public String decryptSimple(String ciphertext, int key, int lang) {
        String original = new String();
        for (String symb : ciphertext.split("")) {
            int firstSymb = chooseFirstSymbol(symb, lang);
            int curPosition = symb.charAt(0) - firstSymb;
            int newPosition = (curPosition + lang - key) % lang;
            original += (char) (firstSymb + newPosition);
        }
        return original;
    }

    public String decryptHard(String ciphertext, int key, int lang) {
        String original = new String();
        int decryptKey = findKey(key, lang);
        for (String symb : ciphertext.split("")) {
            int firstSymb = chooseFirstSymbol(symb, lang);
            int curPosition = symb.charAt(0) - firstSymb;
            int newPosition = (curPosition * decryptKey) % lang;
            original += (char) (firstSymb + newPosition);
        }
        return original;
    }

    private int chooseFirstSymbol(String symbol, int lang) {
        if (lang == 26) {
            return symbol.toUpperCase().equals(symbol) ? 'A' : 'a';
        } else {
            return symbol.toUpperCase().equals(symbol) ? 'А' : 'а';
        }
    }

    private int findKey(int key, int lang) {
        int decryptKey = 0;
        while (true) {
            if ((key * decryptKey) % lang == 1 && Mathematical.findGCD(key, decryptKey) == 1) {
                return decryptKey;
            }
            decryptKey++;
        }
    }

}
