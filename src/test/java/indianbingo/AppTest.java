package indianbingo;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest {

    private Scanner scanner;
    private final String NEW_LINE = "\n";

    /**
     * Test enter "Q"
     * App.initialize(scanner) should return false.
     */
    @Test
    public void shouldQuit() {
        String data = Util.QUIT + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        assertThat(App.initialize(scanner)).isFalse();
    }

    /**
     * Test enter invalid number range
     * App.initialize(scanner) should return false because 91 is not valid number (limit is 90).
     * <p>
     * >> Enter the number range(1-n) : Default to 90  --> 91
     */
    @Test
    public void shouldQuitWithInvalidRangeInput() {
        String data = (Util.MAX_NUM + 1) + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        assertThat(App.initialize(scanner)).isFalse();
    }

    /**
     * Test enter invalid players number:
     * App.initialize(scanner) should return false because 10000 is not in valid player range (limit is 9999).
     * <p>
     * >> Enter the number range(1-n) : Default to 90  --> 90
     * >> Enter Number of players playing the game:    --> 10000
     */
    @Test
    public void shouldQuitWithInvalidPlayerInput() {
        String data = Util.MAX_NUM + NEW_LINE + (Util.MAX_PLAYERS + 1) + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        assertThat(App.initialize(scanner)).isFalse();
    }

    /**
     * Test enter invalid ticket number
     * App.initialize(scanner) should return false because 101 is not in valid row/col range (limit is 100).
     * <p>
     * >> Enter the number range(1-n) : Default to 90                    --> 90
     * >> Enter Number of players playing the game:                      --> 3
     * >> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns):  --> 101X101
     */
    @Test
    public void shouldQuitWithInvalidTicketInput() {
        String data = Util.MAX_NUM + NEW_LINE + 3 + NEW_LINE + "101X101" + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        assertThat(App.initialize(scanner)).isFalse();
    }

    /**
     * Test enter invalid numbersPerRow
     * App.initialize(scanner) should return false because 11 is not in valid numbersPerRow range since it's > col number (10).
     * <p>
     * >> Enter the number range(1-n) : Default to 90                    --> 90
     * >> Enter Number of players playing the game:                      --> 3
     * >> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns):  --> 3X10
     * >> Enter numbers per row. Default to 5:                           --> 11
     */
    @Test
    public void shouldQuitWithInvalidNumbersPerRowInput() {
        String data = Util.MAX_NUM + NEW_LINE + 3 + NEW_LINE + "3X10" + NEW_LINE + "11" + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        assertThat(App.initialize(scanner)).isFalse();
    }

    /**
     * Test enter valid input
     * App.initialize(scanner) should return true with correct GameData.
     * <p>
     * >> Enter the number range(1-n) : Default to 90                    --> ENTER_KEY(90)
     * >> Enter Number of players playing the game:                      --> 3
     * >> Enter Ticket Size : Default to 3X10 ( 3 rows and 10 columns):  --> ENTER_KEY(3X10)
     * >> Enter numbers per row. Default to 5: 11                        --> ENTER_KEY(5)
     */
    @Test
    public void shouldInitialize() {
        String data = NEW_LINE + 3 + NEW_LINE + NEW_LINE + NEW_LINE;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        assertThat(App.initialize(scanner)).isTrue();
        assertThat(App.gameData.range()).isEqualTo(Util.MAX_NUM);
        assertThat(App.gameData.players()).isEqualTo(3);
        assertThat(App.gameData.ticketInfo().row()).isEqualTo(Util.DEFAULT_ROW_SIZE);
        assertThat(App.gameData.ticketInfo().col()).isEqualTo(Util.DEFAULT_COL_SIZE);
        assertThat(App.gameData.ticketInfo().numberPerRow()).isEqualTo(Util.DEFAULT_NUMBER_PER_ROW_SIZE);
    }

}
