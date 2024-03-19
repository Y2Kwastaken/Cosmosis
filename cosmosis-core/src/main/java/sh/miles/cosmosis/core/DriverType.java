package sh.miles.cosmosis.core;

public enum DriverType {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String string;

    DriverType(final String string) {
        this.string = string;
    }

    public String asString() {
        return this.string;
    }
}
