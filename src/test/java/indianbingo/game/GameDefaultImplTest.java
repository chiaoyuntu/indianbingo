package indianbingo.game;

import indianbingo.dealer.Dealer;
import indianbingo.exception.FullBoardException;
import indianbingo.model.WinType;
import indianbingo.player.Player;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameDefaultImplTest {

    private GameDefaultImpl gameDefaultImpl;
    private final Dealer dealer = mock(Dealer.class);
    private final Player mockPlayer1 = mock(Player.class);
    private final Player mockPlayer2 = mock(Player.class);
    private final List<Player> players = Arrays.asList(mockPlayer1, mockPlayer2);
    private Scanner scanner;

    /**
     * Test the game should exist when Q is pressed
     *
     * Check
     * 1. Game status `exit` should be true
     */
    @Test
    public void shouldExit() {
        String data = "Q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        gameDefaultImpl = new GameDefaultImpl(dealer, players, scanner);

        gameDefaultImpl.run();
        assertThat(gameDefaultImpl.exit).isTrue();
    }

    /**
     * Test the game should exist when FullBoardException is throw
     *
     * Check
     * 1. Game status `exit` should be true
     */
    @Test
    public void shouldExitWhenFullBoard() {
        String data = "N\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        gameDefaultImpl = new GameDefaultImpl(dealer, players, scanner);

        when(dealer.getNextNumber()).thenThrow(FullBoardException.class);
        gameDefaultImpl.run();
        assertThat(gameDefaultImpl.exit).isTrue();
    }

    @Test
    public void shouldUnSupportInputDoNothing() {
        String data = "K\nQ\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
        gameDefaultImpl = new GameDefaultImpl(dealer, players, scanner);

        gameDefaultImpl.run();
        assertThat(gameDefaultImpl.exit).isTrue();
    }

    /**
     * Test the game should exist when
     * 1. FIRST_FIVE, TOP_LINE and FULL_HOUSE are claimed by different players
     *
     * Check
     * 1. Winning Combination should contain FIRST_FIVE, TOP_LINE and FULL_HOUSE and map to correct player
     * 2. Game status `exit` should be true
     */
    @Test
    public void shouldCreateNextNumberAndClaimAllWinTypes() {
        String data = "N\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        when(dealer.getNextNumber()).thenReturn(1);
        when(mockPlayer1.claim(anyInt())).thenReturn(new HashSet<>(Arrays.asList(WinType.FIRST_FIVE, WinType.FULL_HOUSE)));
        when(mockPlayer2.claim(anyInt())).thenReturn(new HashSet<>(Collections.singletonList(WinType.TOP_LINE)));
        when(mockPlayer1.getId()).thenReturn(1);
        when(mockPlayer2.getId()).thenReturn(2);

        gameDefaultImpl = new GameDefaultImpl(dealer, players, scanner);

        gameDefaultImpl.run();
        assertThat(gameDefaultImpl.map.size()).isEqualTo(3);
        assertThat(gameDefaultImpl.map.get(WinType.FIRST_FIVE)).isEqualTo(1);
        assertThat(gameDefaultImpl.map.get(WinType.FULL_HOUSE)).isEqualTo(1);
        assertThat(gameDefaultImpl.map.get(WinType.TOP_LINE)).isEqualTo(2);
        assertThat(gameDefaultImpl.exit).isTrue();
    }

    /**
     * Test the game should exist when
     * 1. round 6 (FIRST_FIVE is not possible to claim anymore)
     * 2. TOP_LINE and FULL_HOUSE both are claimed by different players
     *
     * Check
     * 1. Winning Combination should contain TOP_LINE and FULL_HOUSE and map to correct player
     * 2. Game status `exit` should be true
     */
    @Test
    public void shouldCreateNextNumberAndClaimTwoWinTypes() {
        String data = "N\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);

        when(dealer.getNextNumber()).thenReturn(1);
        when(mockPlayer1.claim(anyInt())).thenReturn(new HashSet<>(Collections.singletonList(WinType.FULL_HOUSE)));
        when(mockPlayer2.claim(anyInt())).thenReturn(new HashSet<>(Arrays.asList(WinType.TOP_LINE, WinType.FULL_HOUSE)));
        when(mockPlayer1.getId()).thenReturn(1);
        when(mockPlayer2.getId()).thenReturn(2);

        gameDefaultImpl = new GameDefaultImpl(dealer, players, scanner);

        gameDefaultImpl.round = 5;
        gameDefaultImpl.run();
        assertThat(gameDefaultImpl.map.size()).isEqualTo(2);
        assertThat(gameDefaultImpl.map.get(WinType.FULL_HOUSE)).isEqualTo(1);
        assertThat(gameDefaultImpl.map.get(WinType.TOP_LINE)).isEqualTo(2);
        assertThat(gameDefaultImpl.exit).isTrue();
    }

}
