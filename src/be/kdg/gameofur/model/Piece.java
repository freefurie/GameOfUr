package be.kdg.gameofur.model;

/**
 * This class contains two indexes, it's current one and the old one.
 * This class also contains an inner class of the type enum, which defines the type of piece (SUN or MOON)
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Piece {
    public enum Type {
        SUN, MOON
    }

    //attributes
    private int index;
    private int oldIndex;
    private Type type;

    //constructor
    Piece(Type type) {
        this.type = type;
        index = 0;
    }

    //methods

    //get place on board
    public int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }

    int getOldIndex() {
        return oldIndex;
    }

    void setOldIndex() {
        oldIndex = index;
    }

    //change place on board
    void move(int moves) {
        this.oldIndex = this.index;
        this.index = this.index + moves;
    }

    //change place on board to start
    void moveStart() {
        this.index = 0;
    }

    //check if the piece doesn't go further than 15
    boolean canMove(int moves) {
        return (this.index + moves <= 15);
    }

    //if piece is finished (15th place on the board)
    boolean isFinished() {
        return (this.index == 15);
    }

    //get type of piece (SUN or MOON)
    Type getType() {
        return type;
    }
}
