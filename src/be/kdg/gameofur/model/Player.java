package be.kdg.gameofur.model;

/**
 * This class creates the players, with their type being decided by the type of piece they use (SUN or MOON)
 * Each player also has an array filled with his pieces, and keeps track of the fact if it is his turn, or if he has moved
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Player {
    //attributes
    private final int MAXPIECES = 7;
    private String name ="";
    private Piece.Type type;
    private Piece[] pieces;
    private boolean isTurn;
    private boolean hasMoved;

    //constructor
    Player(Piece.Type type, boolean turn){
        this.type = type;
        pieces = new Piece[MAXPIECES];
        for (int i = 0; i < MAXPIECES; i++) {
            pieces[i] = new Piece(type);
        }
        isTurn = turn;
        hasMoved = true;
    }

    //methods
    Piece.Type getType() {
        return type;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public String PiecesRemaining(){
        int i = 0;
        for (Piece piece : pieces){
            if (piece.isFinished()){
                i++;
            }
        }
        return String.valueOf(MAXPIECES - i);
    }

    void setPieces(int[] pieces) {
        for (int i = 0; i < this.pieces.length; i++) {
            this.pieces[i].setIndex(pieces[i]);
        }
    }

    /**
     * This method checks if the name isn't empty or longer than 10 letters, else the gameofurexception is thrown
     *
     * @param name player
     * @throws GameOfUrException when name isn't correct
     */
    public void setName(String name) throws GameOfUrException {
        if (name.isEmpty()){
            throw new GameOfUrException("Your playername(s) can't be empty");
        }
        if (name.length() > 10){
            throw new GameOfUrException("Your playername(s) can't be empty");
        }
        else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    void setTurn() {
        this.isTurn = !isTurn;
    }

    public boolean getTurn() {
        return isTurn;
    }

    public boolean isFinished() {
        for (Piece piece : pieces) {
            if (!piece.isFinished()) {
                return false;
            }
        }
        return true;
    }

    public void setMoved(boolean boo) {
        hasMoved = boo;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * This method is for retrieving all the indexes each piece has, together with ";" as field separator
     *
     * @return String value of the current index of each piece
     */
    @Override
    public String toString() {
        StringBuilder piecesString = new StringBuilder();
        for (Piece piece : pieces) {
            piecesString.append(piece.getIndex()).append(";");
        }
        return piecesString.toString();
    }
}
