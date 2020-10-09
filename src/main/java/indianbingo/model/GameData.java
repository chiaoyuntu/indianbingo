package indianbingo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface GameData {

    Integer range();

    Integer players();

    TicketInfo ticketInfo();

}
