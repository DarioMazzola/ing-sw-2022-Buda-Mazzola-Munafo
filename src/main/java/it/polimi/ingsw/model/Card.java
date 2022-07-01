package it.polimi.ingsw.model;

/**
 * enum class representing Assistant Cards
 *
 * @author Alessio Buda
 */
public enum Card {
    CARD1(1, 1),
    CARD2(2, 1),
    CARD3(3, 2),
    CARD4(4, 2),
    CARD5(5, 3),
    CARD6(6, 3),
    CARD7(7, 4),
    CARD8(8, 4),
    CARD9(9, 5),
    CARD10(10, 5);

    private final int value;
    private final int moves;

    /**
     * class constructor, assigns value and moves to enum object
     *
     * @param value card value
     * @param moves maximum number of moves by Mother Nature
     */
    Card(int value, int moves) {
        this.value = value;
        this.moves = moves;
    }

    public int getValue() {
        return value;
    }

    public int getMoves() {
        return moves;
    }
}
