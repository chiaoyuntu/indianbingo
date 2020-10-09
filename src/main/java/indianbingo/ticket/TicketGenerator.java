package indianbingo.ticket;

import indianbingo.Util;
import indianbingo.model.TicketInfo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class TicketGenerator {

    protected final TicketInfo ticketInfo;

    public TicketGenerator(TicketInfo ticketInfo) {
        this.ticketInfo = Objects.requireNonNull(ticketInfo);
    }

    /**
     * Generate a ticket
     *     (1) Random generate a list of number for the ticket
     *     (2) Random generate positions for each row to fill those numbers
     */
    public Ticket generate() {
        int[][] content = new int[ticketInfo.row()][ticketInfo.col()];
        Deque<Integer> numbers = new ArrayDeque<>(Util.generateRandomNumber(ticketInfo.row() * ticketInfo.numberPerRow(), ticketInfo.ranges(), 1));

        for (int i = 0; i < ticketInfo.row(); i++) {
            List<Integer> positions = Util.generateRandomNumber(ticketInfo.numberPerRow(), ticketInfo.col(), 1);

            for (Integer position : positions) {
                content[i][position] = numbers.poll();
            }

        }
        return new Ticket(content, ticketInfo.numberPerRow());
    }

}
