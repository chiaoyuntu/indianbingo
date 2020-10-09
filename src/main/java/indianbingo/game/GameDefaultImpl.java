package indianbingo.game;

import indianbingo.Util;
import indianbingo.dealer.Dealer;
import indianbingo.exception.FullBoardException;
import indianbingo.model.WinType;
import indianbingo.player.Player;

import java.util.*;

public class GameDefaultImpl implements Game {

    private final Dealer dealer;
    private final List<Player> players;
    private final Scanner scanner;
    protected int round;
    protected final Map<WinType, Integer> map;
    protected boolean exit;

    public GameDefaultImpl(Dealer dealer, List<Player> players, Scanner scanner) {
        this.dealer = Objects.requireNonNull(dealer);
        this.players = Objects.requireNonNull(players);
        this.scanner = Objects.requireNonNull(scanner);
        this.exit = false;
        this.map = new HashMap<>();
        this.round = 0;
    }

    /**
     * Main process
     * 1. Dealer calls the randomly generated number/cue one at a time.
     * 2. Players mark off the numbers on their tickets as the numbers are
     * called by the Dealer.
     * 3. Exit and print summary if
     *  (1) Press "Q"
     *  (2) All winning combinations are claimed
     *  (3) Board is full(Dealer called all numbers)
     */
    @Override
    public void run() {
        String next;
        while (!exit) {
            next = scanner.next();
            if (Util.isQuit(next)) {
                exit = true;
                break;
            }

            if (Util.isNext(next)) {
                int number;
                try {
                    number = dealer.getNextNumber();
                    System.out.println("Next number is: " + number);
                    players.forEach(
                            p -> {
                                Set<WinType> set = p.claim(number);

                                set.forEach(s -> {
                                    if (!map.containsKey(s)) {
                                        map.put(s, p.getId());
                                        p.setStatus(s);
                                        System.out.println("We have a winner: Player#" + p.getId() + " has won '" + s.getDescription() + "' winning combination.");
                                    }
                                });
                            }
                    );


                    if (map.size() == 3 || (map.containsKey(WinType.FULL_HOUSE) && map.containsKey(WinType.TOP_LINE) && round >= 5)) {
                        exit = true;
                    }
                    round ++;

                } catch (FullBoardException e) {
                    exit = true;
                    System.out.println("Board is full, exit game...");
                }
            } else {
                System.out.println("UnSupport keyword:" + next);
            }
        }

        printSummary();
    }

    protected void printSummary() {
        System.out.println("***** Game Over *****");
        System.out.println("==============================");
        System.out.println("Summary:");
        players.forEach(p -> {
            if (p.getWinningCombination() == null || p.getWinningCombination().size() == 0) {
                System.out.printf("Player#%s : Nothing%n", p.getId());
            } else {
                StringBuilder tmp = new StringBuilder();
                p.getWinningCombination().keySet().forEach(s -> {
                    if (tmp.length() == 0) {
                        tmp.append(s);
                    } else {
                        tmp.append(", ").append(s);
                    }
                });
                System.out.printf("Player#%s : %s%n", p.getId(), tmp.toString());
            }
        });

        System.out.println("==============================");

        players.forEach(Player::printTicket);
    }

}
