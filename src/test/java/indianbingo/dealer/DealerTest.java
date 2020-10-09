package indianbingo.dealer;

import indianbingo.exception.FullBoardException;
import indianbingo.model.GameData;
import indianbingo.model.ImmutableGameData;
import indianbingo.model.ImmutableTicketInfo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerTest {

    private Dealer dealer;
    private static final int TEST_RANGE = 5;

    @Before
    public void setUp() {
        GameData gameData = getMockGameData();
        dealer = new Dealer(gameData);
    }

    /**
     * dealer should generate a number between range 1 - n
     */
    @Test
    public void shouldGetNextNumber() {
        int x = dealer.getNextNumber();
        assertThat(x).isLessThanOrEqualTo(TEST_RANGE);
    }

    /**
     * dealer should throw FullBoardException after claiming all numbers between range 1 - n
     */
    @Test
    public void shouldThrowFullBoardException() {
        for (int i = 0; i < TEST_RANGE; i++) {
            dealer.getNextNumber();
        }
        assertThatThrownBy(() -> dealer.getNextNumber())
                .isExactlyInstanceOf(FullBoardException.class);
    }

    private GameData getMockGameData() {
        return ImmutableGameData.builder()
                .range(TEST_RANGE)
                .players(1)
                .ticketInfo(
                        ImmutableTicketInfo.builder()
                                .ranges(TEST_RANGE)
                                .row(2)
                                .col(4)
                                .numberPerRow(2)
                                .build()
                )
                .build();
    }

}
