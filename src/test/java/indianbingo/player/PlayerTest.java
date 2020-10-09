package indianbingo.player;

import indianbingo.model.WinType;
import indianbingo.ticket.Ticket;
import indianbingo.ticket.TicketGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private final int ID = 1;
    private Player player;
    private Ticket ticket;

    @Before
    public void setUp() {
        TicketGenerator ticketGenerator = mock(TicketGenerator.class);
        ticket = mock(Ticket.class);
        when(ticketGenerator.generate()).thenReturn(ticket);
        player = new Player(ID, ticketGenerator);
    }

    /**
     * Player should be able to get correct id
     */
    @Test
    public void shouldGetId() {
        assertThat(player.getId()).isEqualTo(ID);
    }

    /**
     * Player should be able to set WinningCombination status
     */
    @Test
    public void shouldSetStatus() {
        player.setStatus(WinType.FIRST_FIVE);
        assertThat(player.getWinningCombination().get(WinType.FIRST_FIVE)).isTrue();
    }

    /**
     * Player should return TOP_LINE and FULL_HOUSE status
     */
    @Test
    public void shouldClaim() {
        when(ticket.isFirstLineMatch()).thenReturn(true);
        when(ticket.isFullHouse()).thenReturn(true);
        Set<WinType> set = player.claim(5);
        assertThat(set.size()).isEqualTo(2);
        assertThat(set.contains(WinType.TOP_LINE)).isTrue();
        assertThat(set.contains(WinType.FULL_HOUSE)).isTrue();
    }

    /**
     * Player should return FIRST_FIVE status when round 5 and ticket totalMatch number is 5
     */
    @Test
    public void shouldClaimTopFive() {
        when(ticket.isEarlyFive()).thenReturn(true);
        Set<WinType> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set = player.claim(5);
        }
        assertThat(set.size()).isEqualTo(1);
        assertThat(set.contains(WinType.FIRST_FIVE)).isTrue();
    }

}
