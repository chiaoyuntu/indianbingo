package indianbingo.model;

public enum WinType {
    FIRST_FIVE("First Five"),
    TOP_LINE("Top Line"),
    FULL_HOUSE("Full House"),
    NOTHING("Nothing");

    private final String description;

    public String getDescription() {
        return description;
    }

    private WinType(String d) {
        this.description = d;
    }

}
