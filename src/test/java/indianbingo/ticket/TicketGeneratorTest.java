package indianbingo.ticket;

import indianbingo.model.ImmutableTicketInfo;
import indianbingo.model.TicketInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketGeneratorTest {

    private TicketGenerator ticketGenerator;
    private final int RANGE = 30;
    private final int ROW = 3;
    private final int COL = 10;
    private final int NUMBER_PER_ROW = 5;


    @Before
    public void setUp() {
        TicketInfo ticketInfo = getMockTicketInfo();
        ticketGenerator = new TicketGenerator(ticketInfo);
    }

    @Test
    public void shouldGenerate() {
        Ticket t = ticketGenerator.generate();
        Set<Integer> set = new HashSet<>();
        int cnt;
        for (int i = 0; i < ROW; i++) {
            cnt = 0;
            for (int j = 0; j < COL; j++) {
                if (t.ticket[i][j] != 0) {
                    cnt++;
                    assertThat(t.ticket[i][j]).isLessThanOrEqualTo(RANGE);
                    assertThat(t.ticket[i][j]).isGreaterThan(0);
                    assertThat(set.contains(t.ticket[i][j])).isFalse();
                    set.add(t.ticket[i][j]);
                }
            }
            assertThat(cnt).isEqualTo(NUMBER_PER_ROW);
        }
    }

    private TicketInfo getMockTicketInfo() {
        return ImmutableTicketInfo.builder()
                .ranges(RANGE)
                .row(ROW)
                .col(COL)
                .numberPerRow(NUMBER_PER_ROW)
                .build();
    }

}
