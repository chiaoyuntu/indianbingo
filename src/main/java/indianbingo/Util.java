package indianbingo;

import java.util.*;

public class Util {

    public static final Integer DEFAULT_ROW_SIZE = 3;
    public static final Integer DEFAULT_COL_SIZE = 10;
    public static final Integer DEFAULT_NUMBER_PER_ROW_SIZE = 5;
    public static final Integer MAX_NUM = 90;
    public static final Integer MAX_PLAYERS = 9999;
    public static final Integer MAX_ROW_COL_SIZE = 100;
    public static final String NEXT = "N";
    public static final String QUIT = "Q";

    public static boolean isQuit(String input) {
        return input.equals(QUIT);
    }

    public static boolean isNext(String input) {
        return input.equals(NEXT);
    }

    public static boolean isValidRange(String input, Integer range) {
        if (Integer.parseInt(input) <= range && Integer.parseInt(input) > 0) {
            return true;
        } else {
            System.out.printf("Invalid range: #%s , range should between 1 to %d", input, range);
            return false;
        }
    }

    public static boolean isValidPlayerRange(String input) {
        return isValidRange(input, MAX_PLAYERS);
    }

    public static boolean isValidTicketRange(String input) {
        return isValidRange(input, MAX_ROW_COL_SIZE);
    }

    public static boolean isValidNumRange(String input) {
        return isValidRange(input, MAX_NUM);
    }

    public static Integer generateRandomNumber(int size) {
        Random r = new Random();

        return r.nextInt(size);
    }

    public static List<Integer> generateRandomNumber(int size, int max, int min) {
        Set<Integer> set = new HashSet<>();
        Random r = new Random();
        while (set.size() != size) {
            set.add(r.nextInt(max - min) + min);
        }

        return new ArrayList<>(set);
    }

}
