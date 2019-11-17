import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PasswordOperations {

    private static char[] numbers = new char[10];
    private static  char[] capitalLetters = new char[26];
    private static char[] smallLetters = new char[26];
    private static char[] specialChars = new char[32];
    private static char[] allAsciiPassChars = new char[94];
    //private static char[] notAllowedAsciiChars = new char[163];
    static String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*(" + Arrays.toString(getSpecialChars())+"))(?=\\S+$).{8,16}";

    public static String generateRandomPass(){
        StringBuilder sb = new StringBuilder();
        List<Character> chars = new ArrayList<>();

        int randomIndexNumber = ThreadLocalRandom.current().nextInt(getPassNumbers().length);
        chars.add(getPassNumbers()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getCapitalLetters().length);
        chars.add(getCapitalLetters()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getSmallLetters().length);
        chars.add(getSmallLetters()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getSpecialChars().length);
        chars.add(getSpecialChars()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getPassNumbers().length);
        chars.add(getPassNumbers()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getCapitalLetters().length);
        chars.add(getCapitalLetters()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getSmallLetters().length);
        chars.add(getSmallLetters()[randomIndexNumber]);

        randomIndexNumber = ThreadLocalRandom.current().nextInt(getSpecialChars().length);
        chars.add(getSpecialChars()[randomIndexNumber]);

        Collections.shuffle(chars);

        for(Character c: chars){
            sb.append(c);
        }

        return sb.toString();
    }

    public static boolean isStringOk(String s){
        return s.matches(pattern);
    }

    private static char[] getAllAsciiPassChars(){
        int j = 0;
        for (char i = '!'; i < 127 ; i++) {
            allAsciiPassChars[j] = i;
            j++;
        }
        return allAsciiPassChars;
    }

    private static char[] getCapitalLetters(){
        int j = 0;
        for(int i = 32; i<58;i++){
            capitalLetters[j] = getAllAsciiPassChars()[i];
            j++;
        }
        return capitalLetters;
    }

    private static char[] getPassNumbers(){
        int j = 0;
        for(int i = 15;i<25;i++){
            numbers[j] = getAllAsciiPassChars()[i];
            j++;
        }
        return numbers;
    }

    private static char[] getSmallLetters(){
        int j = 0;
        for (int i = 64; i < 90; i++) {
            smallLetters[j] = getAllAsciiPassChars()[i];
            j++;
        }
        return smallLetters;
    }

    private static char[] getSpecialChars(){
        int j = 0;
        for(int i = 0;i<15;i++){
            specialChars[j] = getAllAsciiPassChars()[i];
            j++;
        }
        for(int i = 25;i<32;i++){
            specialChars[j] = getAllAsciiPassChars()[i];
            j++;
        }
        for(int i = 58;i<64;i++){
            specialChars[j] = getAllAsciiPassChars()[i];
            j++;
        }
        for(int i = 90;i<94;i++){
            specialChars[j] = getAllAsciiPassChars()[i];
            j++;
        }
        return specialChars;
    }

    //-------------RANDOMNESS TESTING PART------------------------------

    public static void randomTestNumbers(){
        int[] numCounters = new int[10];
        int num;

        //inicjalizacja tablicy
        for(int i = 0; i<10;i++){
            numCounters[i] = 0;
        }

        for(long i = 0; i < 1000; i++){
            num = ThreadLocalRandom.current().nextInt(getPassNumbers().length);
            numCounters[num]++;
        }

        for(int i = 0; i<10; i++){
            System.out.println(i+ ": " +numCounters[i]);
        }
    }

    public static void randomTestCapitalLetters(){
        int[] numCounters = new int[26];
        int num;
        //inicjalizacja tablicy
        for(int i = 0; i<26;i++){
            numCounters[i] = 0;
        }

        for(long i = 0; i < 1000; i++){
            num = ThreadLocalRandom.current().nextInt(getCapitalLetters().length);
            numCounters[num]++;
        }

        for(int i = 0; i<26; i++){
            System.out.println(getCapitalLetters()[i]+ ": " +numCounters[i]);
        }
    }

    public static void randomTestSmallLetters(){
        int[] numCounters = new int[26];
        int num;

        //inicjalizacja tablicy
        for(int i = 0; i<26;i++){
            numCounters[i] = 0;
        }

        for(long i = 0; i < 1000; i++){
            num = ThreadLocalRandom.current().nextInt(getSmallLetters().length);
            numCounters[num]++;
        }

        for(int i = 0; i<26; i++){
            System.out.println(getSmallLetters()[i]+ ": " +numCounters[i]);
        }
    }

    public static void randomTestSpecialChars(){
        int[] numCounters = new int[getSpecialChars().length];
        int num;

        //inicjalizacja tablicy
        for(int i = 0; i<getSpecialChars().length;i++){
            numCounters[i] = 0;
        }

        for(long i = 0; i < 1000; i++){
            num = ThreadLocalRandom.current().nextInt(getSpecialChars().length);
            numCounters[num]++;
        }

        for(int i = 0; i<getSpecialChars().length; i++){
            System.out.println(getSpecialChars()[i]+ ": " +numCounters[i]);
        }
    }

}
