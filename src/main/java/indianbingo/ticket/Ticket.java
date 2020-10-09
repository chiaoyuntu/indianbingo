package indianbingo.ticket;

public class Ticket {

    protected final int[][] ticket;
    protected final int numbersPerRow;
    protected int[] match;
    protected int totalMatch;

    Ticket(int[][] ticket, int numbersPerRow) {
        this.ticket = ticket;
        this.numbersPerRow = numbersPerRow;
        match = new int[ticket.length];
        totalMatch = 0;
    }

    /**
     * If a number called by Dealer appears on this ticket,
     * mark the number and make it negative.
     */
    public void claim(int number) {
        for (int i = 0; i < ticket.length; i++) {
            for (int j = 0; j < ticket[0].length; j++) {
                if (ticket[i][j] == number) {
                    ticket[i][j] = -number;
                    match[i]++;
                    totalMatch++;
                }
            }
        }
    }

    public void printTicket() {
        StringBuilder tmp;
        for (int[] ints : ticket) {
            tmp = new StringBuilder();
            for (int j = 0; j < ticket[0].length; j++) {
                tmp.append(" ").append(ints[j]);
            }
            System.out.println(tmp.toString());
        }

    }

    public boolean isFirstLineMatch() {
        return match[0] == numbersPerRow;
    }

    public boolean isEarlyFive() {
        return totalMatch == 5;
    }

    public boolean isFullHouse() {
        return totalMatch == (numbersPerRow * ticket.length);
    }

}
