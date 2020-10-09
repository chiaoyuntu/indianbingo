package indianbingo.model;

import org.immutables.value.Value;

@Value.Immutable
public interface TicketInfo {

    int ranges();

    @Value.Default default int row() {
        return 3;
    }

    @Value.Default default int col() {
        return 10;
    }

    @Value.Default default int numberPerRow() {
        return 5;
    }

}
