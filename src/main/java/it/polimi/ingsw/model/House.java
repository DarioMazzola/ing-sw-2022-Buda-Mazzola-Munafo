package it.polimi.ingsw.model;

/**
 * enum class representing Students' and Professors' Houses
 *
 * @author Alessio Buda & Dario Mazzola
 */
public enum House {
    GREEN("GREEN", "\u001b[32m"),
    RED("RED", "\u001b[31m"),
    YELLOW("YELLOW", "\u001b[33m"),
    PINK("PINK", "\u001b[35;1m"),
    BLUE("BLUE", "\u001b[34m");

    public static final String RESET = "\u001B[0m";

    private final String escape;
    private final String name;

    House(String name, String escape) {
        this.name = name;
        this.escape = escape;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getColouredHouse() {
        return escape + name + RESET;
    }
}
