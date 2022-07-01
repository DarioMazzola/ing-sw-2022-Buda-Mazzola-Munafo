package it.polimi.ingsw.model;

/** enum class representing Tower Colors
 * @author Alessio Buda & Dario Mazzola
 */
public enum Color {
    WHITE ("WHITE", "\u001B[0m"),
    GRAY("GRAY","\u001B[37m"),
    BLACK("BLACK","\u001B[30m");

    public static final String RESET = "\u001B[0m";

    private final String escape;
    private final String name;

    Color(String name, String escape) {
        this.name = name;
        this.escape = escape;
    }

    @Override
    public String toString() {
        return escape + name + RESET;
    }
}

