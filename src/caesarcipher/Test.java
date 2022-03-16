package caesarcipher;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int choice = 0;
        Scanner in = new Scanner(System.in, "cp1251");
        Cipher cipher = new Cipher();
        RailFenceCipher railFenceCipher = new RailFenceCipher();
        RotationGridCipher rotationGridCipher = new RotationGridCipher();
        KeyPhraseCipher keyPhraseCipher = new KeyPhraseCipher();
        do {
            String original;
            String ciphertext;
            String decryption;
            int key;
            switch (choice) {
                case 1:
                    original = enterOriginalText(in);
                    key = enterKey(in);
                    System.out.println(original);
                    ciphertext = railFenceCipher.encrypt(original, key);
                    decryption = railFenceCipher.decrypt(ciphertext, key);
                    System.out.println("Ciphertext: " + ciphertext);
                    System.out.println("Original text: " + decryption);
                    break;
                case 2:
                    System.out.println("Enter a key phrase: ");
                    String keyWord = in.nextLine();
                    original = enterOriginalText(in);
                    ciphertext = keyPhraseCipher.encrypt(original, keyWord);
                    decryption = keyPhraseCipher.decrypt(ciphertext, keyWord);
                    System.out.println("Ciphertext: " + ciphertext);
                    System.out.println("Original text: " + decryption);
                    break;
                case 3:
                    System.out.println("Enter original text: ");
                    original = in.nextLine();
                    ciphertext = rotationGridCipher.encrypt(original);
                    decryption = rotationGridCipher.decrypt(ciphertext);
                    System.out.println("Ciphertext: " + ciphertext);
                    System.out.println("Original text: " + decryption);
                    break;
                case 4:
                    int variant = 0;
                    do {
                        switch (variant) {
                            case 1:
                                original = enterOriginalText(in);
                                key = enterKey(in);
                                System.out.println(original);
                                ciphertext = cipher.encryptSimple(original, key, cipher.RUS);
                                decryption = cipher.decryptSimple(ciphertext, key, cipher.RUS);
                                System.out.println("Ciphertext: " + ciphertext);
                                System.out.println("Original text: " + decryption);
                                break;
                            case 2:
                                original = enterOriginalText(in);
                                key = enterKey(in);
                                ciphertext = cipher.encryptSimple(original, key, cipher.ENG);
                                decryption = cipher.decryptSimple(ciphertext, key, cipher.ENG);
                                System.out.println("Ciphertext: " + ciphertext);
                                System.out.println("Original text: " + decryption);
                                break;
                        }
                        printCaesarMenu();
                        variant = enterAnswer(in);
                    } while (variant != 0);
                    break;
                case 5:
                    int variant_2 = 0;
                    do {
                        switch (variant_2) {
                            case 1:
                                original = enterOriginalText(in);
                                key = enterKeyHardAlgorithm(in, cipher.RUS);
                                System.out.println(original);
                                ciphertext = cipher.encryptHard(original, key, cipher.RUS);
                                decryption = cipher.decryptHard(ciphertext, key, cipher.RUS);
                                System.out.println("Ciphertext: " + ciphertext);
                                System.out.println("Original text: " + decryption);
                                break;
                            case 2:
                                original = enterOriginalText(in);
                                key = enterKeyHardAlgorithm(in, cipher.ENG);
                                ciphertext = cipher.encryptHard(original, key, cipher.ENG);
                                decryption = cipher.decryptHard(ciphertext, key, cipher.ENG);
                                System.out.println("Ciphertext: " + ciphertext);
                                System.out.println("Original text: " + decryption);
                                break;
                        }
                        printCaesarMenu();
                        variant_2 = enterAnswer(in);
                    } while (variant_2 != 0);
                    break;
            }
            printMenu();
            choice = enterAnswer(in);
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("""
                           Choose method:
                           1. Rail Fence Cipher
                           2. Cipher 'Key phrase'
                           3. Rotation grid
                           4. Caesar cipher
                           5. Caesar hard cipher""");
    }

    private static void printCaesarMenu() {
        System.out.println("""
                           Choose variant:
                           1. Encrypt and decrypt russian text
                           2. Encrypt and decrypt english text
                           0. Return""");
    }

    private static int enterAnswer(Scanner in) {
        while (true) {
            System.out.print("Your choice? ");
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Your enter is not valid point of menu\nTry again");
            }
        }
    }

    private static int enterKey(Scanner in) {
        while (true) {
            System.out.print("Enter key: ");
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Your enter is not valid key\nTry again");
            }
        }
    }

    private static int enterKeyHardAlgorithm(Scanner in, int lang) {
        int key;
        while (true) {
            System.out.print("Enter key: ");
            try {
                key = Integer.parseInt(in.nextLine());
                if (Mathematical.findGCD(key, lang) != 1) {
                    throw new NumberFormatException();
                }
                return key;
            } catch (NumberFormatException ex) {
                System.out.println("Your enter is not valid key for this algorithm\nTry again");
            }
        }
    }

    private static String enterOriginalText(Scanner in) {
        String text;
        while (true) {
            System.out.print("Enter original text: ");
            text = in.nextLine();
            if (text.length() > 20) {
                return text;
            }
            System.out.println("Text must be more than 20 characters");
        }
    }

}
