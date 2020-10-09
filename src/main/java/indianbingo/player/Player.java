package indianbingo.player;

import indianbingo.model.WinType;
import indianbingo.ticket.Ticket;
import indianbingo.ticket.TicketGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player {

    private final int id;
    private final Ticket ticket;

    private int round;
    protected Map<WinType, Boolean> winningCombination;

    public Player(int id, TicketGenerator ticketGenerator) {
        this.id = id;
        ticket = ticketGenerator.generate();
        printTicket();
        round = 0;
        winningCombination = new HashMap<>();
    }

    public void printTicket() {
        System.out.println("Print ticket for Player#" + id);
        System.out.println("==============================");
        ticket.printTicket();
        System.out.println("==============================");
    }

    /**
     * Player claim the number and return the winning combinations on this ticket after this round
     */
    public Set<WinType> claim(int number) {
        ticket.claim(number);
        round++;
        Set<WinType> res = new HashSet<>();
        if (round == 5 && ticket.isEarlyFive()) res.add(WinType.FIRST_FIVE);
        if (ticket.isFirstLineMatch()) res.add(WinType.TOP_LINE);
        if (ticket.isFullHouse()) res.add(WinType.FULL_HOUSE);
        return res;
    }

    public int getId() {
        return id;
    }

    /**
     * This function is used to set the winning combination status of the player
     *
     * If a particular winning combination has been successfully claimed,
     * it cannot be claimed again.
     * The central GameDefaultImpl will set the status of player after checking
     * a particular winning combination hasn't been claimed yet.
     */
    public void setStatus(WinType type) {
        if (!winningCombination.containsKey(type)) {
            winningCombination.put(type, true);
        }
    }

    public Map<WinType, Boolean> getWinningCombination() {
        return winningCombination;
    }

}
