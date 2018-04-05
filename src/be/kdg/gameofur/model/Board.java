package be.kdg.gameofur.model;


/**
 * This class creates the board, existing out of fields. you have two arrays of fields, one for each player
 * It also contains the location of the pieces
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Board {
    //attributes
    private final int MAXFIELDS = 16;
    private Field[] fieldsPlayer1;
    private Field[] fieldsPlayer2;
    private Piece undoPiece;


    //constructor
    public Board(Piece[] piecesPlayer1, Piece[] piecesPlayer2) {
        fieldsPlayer1 = new Field[MAXFIELDS];
        fieldsPlayer2 = new Field[MAXFIELDS];
        fillFields();
        for (Piece piece : piecesPlayer1) {
            fieldsPlayer1[0].setPiece(piece);
        }
        for (Piece piece : piecesPlayer2) {
            fieldsPlayer2[0].setPiece(piece);
        }
    }

    //methods

    /**
     * This method fills both fields, between 5 and 12 are individual fields, the rest are shared fields
     * Positions 4 and 14 on the individual fields, and 8 on the shared field, are rosettes
     */
    private void fillFields() {
        for (int i = 0; i < MAXFIELDS; i++) {
            if (i < 5 || i > 12) {
                //generates individual fields
                fieldsPlayer1[i] = new Field();
                fieldsPlayer2[i] = new Field();
            } else {
                //generates shared fields
                fieldsPlayer1[i] = new Field();
                fieldsPlayer2[i] = fieldsPlayer1[i];
            }
        }
        //turns certain fields into rosette fields
        int[] rosetteFields = {4, 8, 14};
        for (int rosetteField : rosetteFields) {
            fieldsPlayer1[rosetteField].setRosette();
            fieldsPlayer2[rosetteField].setRosette();
        }
    }

    /**
     * This method further checks if the piece can move, and updates the board respectively
     *
     * @param moves total amount thrown with dice
     * @param piece the piece that is selected to move
     * @return false if the piece failed to move
     */
    boolean update(int moves, Piece piece) {
        //choose which field to update
        boolean moved = false;
        Field[] fields;
        if (piece.getType() == Piece.Type.SUN) {
            fields = fieldsPlayer1;
        } else {
            fields = fieldsPlayer2;
        }

        int destination = piece.getIndex() + moves;
        if (piece.canMove(moves)) {
            if (fields[destination].isFull()) {
                if (fields[destination].getPiece().getType() != piece.getType()) {
                    piece.setOldIndex();
                    fields[0].setPiece(fields[destination].getPiece());
                    fields[destination].getPiece().moveStart();
                    piece.move(moves);
                    fields[destination].setPiece(piece);
                    undoPiece = piece;
                    moved = true;
                } else if (destination == 15) {
                    piece.setOldIndex();
                    piece.move(moves);
                    fields[destination].setPiece(piece);
                    undoPiece = piece;
                    moved = true;
                }
            } else {                                                                            //destination is available
                piece.setOldIndex();
                piece.move(moves);                                                              //move the piece to his destination
                fields[destination].setPiece(piece);                                            //set the piece in his new position on the board
                undoPiece = piece;
                moved = true;
            }
        }

        //apply field update
        if (piece.getType() == Piece.Type.SUN) {
            fieldsPlayer1 = fields;
        } else {
            fieldsPlayer2 = fields;
        }
        return moved;
    }

    /**
     * this method resets the last moved piece back to its earlier position using the indexes contained in piece
     *
     * @param player the player whose turn it is
     */
    void undoMove(Player player) {
        if (player.getType() == Piece.Type.SUN) {
            fieldsPlayer1[undoPiece.getIndex()].removePiece();
            fieldsPlayer1[undoPiece.getOldIndex()].setPiece(undoPiece);
        } else {
            fieldsPlayer2[undoPiece.getIndex()].removePiece();
            fieldsPlayer2[undoPiece.getOldIndex()].setPiece(undoPiece);
        }
        undoPiece.setIndex(undoPiece.getOldIndex());
    }

    Field[] getFieldsPlayer1() {
        return fieldsPlayer1;
    }

    Field[] getFieldsPlayer2() {
        return fieldsPlayer2;
    }
}
