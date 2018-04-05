package be.kdg.gameofur.model;

/**
 * This class creates fields which can contain a piece
 * The fields can be either normal or Rosette
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
class Field {
    //attributes
    private Piece piece;
    private boolean rosette;

    //constructor
    Field() {
        this.rosette = false;
    }

    //methods
    boolean isFull() {
        return !(piece == null);
    }

    Piece getPiece() {
        return piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    void removePiece() {
        this.piece = null;
    }

    boolean isRosette() {
        return rosette;
    }

    void setRosette() {
        this.rosette = true;
    }
}
