package indianbingo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import indianbingo.dealer.Dealer;
import indianbingo.game.Game;
import indianbingo.game.GameDefaultImpl;
import indianbingo.model.GameData;
import indianbingo.player.Player;
import indianbingo.ticket.TicketGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameModule extends AbstractModule {

    private final GameData gameData;
    private final Scanner scanner;

    public GameModule(GameData gameData, Scanner scanner) {
        this.gameData = Objects.requireNonNull(gameData);
        this.scanner = Objects.requireNonNull(scanner);
    }

    @Override
    protected void configure() {
        bind(Game.class).to(GameDefaultImpl.class);
    }

    @Provides
    @Singleton
    GameDefaultImpl gameDefaultImpl(Dealer dealer, List<Player> players, Scanner scanner) {
        return new GameDefaultImpl(dealer, players, scanner);
    }

    @Provides
    @Singleton
    Scanner scanner() {
        return scanner;
    }

    @Provides
    @Singleton
    List<Player> players(TicketGenerator ticketGenerator) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < gameData.players(); i++) {
            players.add(new Player(i + 1, ticketGenerator));
        }
        return players;
    }

    @Provides
    @Singleton
    TicketGenerator ticketGenerator() {
        return new TicketGenerator(gameData.ticketInfo());
    }

    @Provides
    @Singleton
    Dealer dealer() {
        return new Dealer(gameData);
    }

}
