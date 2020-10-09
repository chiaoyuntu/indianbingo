package indianbingo.ticket;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketTest {

    private Ticket ticket;

    @Before
    public void setUp() {
        int[][] mockTicket = getMockTicket();
        int NUMBER_PER_ROW = 3;
        ticket = new Ticket(mockTicket, NUMBER_PER_ROW);
    }

    @Test
    public void shouldClaim() {
        ticket.claim(2);
        assertThat(ticket.totalMatch).isEqualTo(1);
        assertThat(ticket.match[0]).isEqualTo(1);
    }

    @Test
    public void shouldNotClaim() {
        ticket.claim(6);
        assertThat(ticket.totalMatch).isEqualTo(0);
        int ROW = 3;
        for (int i = 0; i < ROW; i++) {
            assertThat(ticket.match[i]).isEqualTo(0);
        }
    }

    @Test
    public void shouldFirstLineMatch() {
        ticket.claim(2);
        ticket.claim(4);
        ticket.claim(7);
        assertThat(ticket.isFirstLineMatch()).isTrue();
    }

    @Test
    public void shouldNotFirstLineMatch() {
        ticket.claim(1);
        ticket.claim(8);
        ticket.claim(9);
        assertThat(ticket.isFirstLineMatch()).isFalse();
    }

    @Test
    public void shouldEarlyFive() {
        ticket.claim(2);
        ticket.claim(4);
        ticket.claim(7);
        ticket.claim(1);
        ticket.claim(8);
        assertThat(ticket.isEarlyFive()).isTrue();
    }

    @Test
    public void shouldNotEarlyFive() {
        ticket.claim(6);
        ticket.claim(10);
        ticket.claim(7);
        ticket.claim(1);
        ticket.claim(8);
        assertThat(ticket.isEarlyFive()).isFalse();
        assertThat(ticket.isFullHouse()).isFalse();
    }

    @Test
    public void shouldFullHouse() {
        ticket.claim(2);
        ticket.claim(4);
        ticket.claim(7);
        ticket.claim(1);
        ticket.claim(8);
        ticket.claim(9);
        ticket.claim(15);
        ticket.claim(12);
        ticket.claim(5);
        assertThat(ticket.isFullHouse()).isTrue();
    }

    private int[][] getMockTicket() {
        return new int[][]{
                {0, 2, 4, 0, 7},
                {1, 0, 8, 9, 0},
                {15, 0, 0, 12, 5}
        };

    }

}
