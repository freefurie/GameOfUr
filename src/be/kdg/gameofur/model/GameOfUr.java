package be.kdg.gameofur.model;

import java.util.Random;

/**
 * This is the main class of the game, all the other classes are initialised here.
 * It is the only class that communicates with the presenter, and in general leads the game.
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class GameOfUr {
    //attributes
    private Board board;
    private Player[] players;
    private Dice dice;
    private Converter converter;
    private ReaderWriter readerWriter;
    private int turns;
    private boolean isSingleplayer;
    private boolean isGesaved;

    //constructor
    public GameOfUr() {
        players = new Player[]{
                new Player(Piece.Type.SUN, true),
                new Player(Piece.Type.MOON, false)
        };
        board = new Board(players[0].getPieces(), players[1].getPieces());
        dice = new Dice();
        converter = new Converter();
        readerWriter = new ReaderWriter();
        isGesaved = false;
        isSingleplayer = false;
        turns = 1;
    }

    //methods
    //dice methods
    public Dice getDice() {
        return dice;
    }

    //board
    public void undo() {
        board.undoMove(getPlayerTurn());
        getPlayerTurn().setMoved(false);
    }

    /**
     * This methode is called upon when a piece is selected to move, upon which it checks if the move is valid,
     * and then sends the piece through to the board with the moves it has to make.
     *
     * @param row    the row of the gridpane the piece is on
     * @param column the column of the gridpane the piece is on
     */
    public void move(int row, int column) {
        if (!getPlayerTurn().hasMoved()) {
            int index = converter.gridToField(row, column);
            if (getPlayerTurn().equals(players[0])) {
                if (board.getFieldsPlayer1()[index].isFull()) {
                    if (board.getFieldsPlayer1()[index].getPiece().getType() == Piece.Type.SUN) {
                        Piece piece = board.getFieldsPlayer1()[index].getPiece();
                        int moves = Integer.parseInt(dice.toString());
                        if (board.update(moves, piece)) {
                            clearField();
                            getPlayerTurn().setMoved(true);
                            if (board.getFieldsPlayer1()[piece.getIndex()].isRosette()) {
                                dice.setRolled(false);
                            }
                        }
                    }
                }
            } else {
                if (board.getFieldsPlayer2()[index].isFull()) {
                    if (board.getFieldsPlayer2()[index].getPiece().getType() == Piece.Type.MOON) {
                        Piece piece = board.getFieldsPlayer2()[index].getPiece();
                        int moves = Integer.parseInt(dice.toString());
                        if (board.update(moves, piece)) {
                            clearField();
                            getPlayerTurn().setMoved(true);
                            if (board.getFieldsPlayer2()[piece.getIndex()].isRosette()) {
                                dice.setRolled(false);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This move is called upon when the computer has to make a move.
     * First it checks if the computer could take a piece of the player, if it can it will take it.
     * If it can't take a piece of the player, it will choose a random piece and try to move it.
     * <p>
     * Then it'll check if the field he landed on isn't a rosette, if it is, it'll go again.     *
     */
    public void moveComputer() {
        boolean hasMoved = false;
        Random random = new Random();
        do {
            dice.roll();
            dice.setRolled(true);
            int moves = Integer.parseInt(dice.toString());
            //see if computer could take a piece from the player
            for (int i = 0; i < getPlayerTurn().getPieces().length; i++) {
                if (getPlayerTurn().getPieces()[i].canMove(moves)) {
                    int destination = getPlayerTurn().getPieces()[i].getIndex() + moves;
                    if (board.getFieldsPlayer2()[destination].isFull() && !hasMoved) {
                        if (board.update(moves, getPlayerTurn().getPieces()[i])) {
                            clearField();
                            if (!board.getFieldsPlayer2()[destination].isRosette()) {
                                hasMoved = true;
                            }
                        }
                    }
                }
            }
            //if computer can't take a piece, move a piece randomly
            if (!hasMoved) {
                dice.roll();
                moves = Integer.parseInt(dice.toString());
                Piece piece = getPlayerTurn().getPieces()[random.nextInt(getPlayerTurn().getPieces().length)];
                if (board.update(moves, piece)) {
                    clearField();
                    if (!board.getFieldsPlayer2()[piece.getIndex()].isRosette()) {
                        hasMoved = true;
                    }
                }
            }
        } while (!hasMoved);
        dice.setRolled(false);
    }

    /**
     * This method is a fail safe, to make sure when a piece has moved, the positions on the board also get updated
     */
    private void clearField() {
        for (int i = 0; i < board.getFieldsPlayer1().length; i++) {
            board.getFieldsPlayer1()[i].removePiece();
            board.getFieldsPlayer2()[i].removePiece();
        }
        for (Piece piece : players[0].getPieces()) {
            board.getFieldsPlayer1()[piece.getIndex()].setPiece(piece);
        }
        for (Piece piece : players[1].getPieces()) {
            board.getFieldsPlayer2()[piece.getIndex()].setPiece(piece);
        }
    }

    /**
     * This method is to check after throwing the dice, if the player is able to make a move with one of his pieces.
     * If he can't (false), the player'll have to skip his turn.
     *
     * @return false if the player is unable to move any of his pieces
     */
    public boolean canMovePiece() {
        boolean move = false;
        int moves = Integer.parseInt(dice.toString());
        for (Piece piece : getPlayerTurn().getPieces()) {
            if (!piece.isFinished() && piece.canMove(moves)) {
                int index = piece.getIndex();
                if (getPlayerTurn().getType() == Piece.Type.SUN) {
                    if (!board.getFieldsPlayer1()[index + moves].isFull()) {
                        move = true;
                    } else if (board.getFieldsPlayer1()[index + moves].getPiece().getType() != piece.getType()) {
                        move = true;
                    }
                } else if (getPlayerTurn().getType() == Piece.Type.MOON) {
                    if (!board.getFieldsPlayer2()[index + moves].isFull()) {
                        move = true;
                    } else if (board.getFieldsPlayer2()[index + moves].getPiece().getType() != piece.getType()) {
                        move = true;
                    }
                }
            }
        }
        return move;
    }

    public int getDestination(int row, int column) {
        int moves = Integer.parseInt(dice.toString());
        int index = converter.gridToField(row, column);
        return index + moves;
    }

    //return coordinates
    public int getPieceColumn(int index, Player player) {
        return converter.fieldToGridColumn(index, player);
    }

    public int getPieceRow(int index, Player player) {
        return converter.fieldToGridRow(index, player);
    }

    //players

    /**
     * @param keuze an int between 1 and 2, to decide which player is called upon
     * @return the player who was chosen
     */
    public Player getPlayers(int keuze) {
        return players[keuze - 1];
    }

    public Player getPlayerTurn() {
        if (players[0].getTurn()) {
            return players[0];
        } else {
            return players[1];
        }
    }

    public void switchTurn() {
        players[0].setTurn();
        players[1].setTurn();
    }

    //reset board

    /**
     * this method resets the board to the initial values, allowing to restart the game
     */
    public void reset() {
        players = new Player[]{
                new Player(Piece.Type.SUN, true),
                new Player(Piece.Type.MOON, false)
        };
        board = new Board(players[0].getPieces(), players[1].getPieces());
        dice = new Dice();
        turns = 1;
    }

    /**
     * If there is a game saved, this method will load that upon the start of the game
     */
    public void load() {
        //player 1 pieces
        String[] player1s = readerWriter.readBoard("Player1");
        int[] indexes1 = new int[player1s.length];
        for (int i = 0; i < indexes1.length; i++) {
            indexes1[i] = Integer.parseInt(player1s[i]);
        }
        getPlayers(1).setPieces(indexes1);
        //player 2 pieces
        String[] player2s = readerWriter.readBoard("Player2");
        int[] indexes2 = new int[player2s.length];
        for (int i = 0; i < indexes2.length; i++) {
            indexes2[i] = Integer.parseInt(player2s[i]);
        }
        getPlayers(2).setPieces(indexes2);
        //number of moves
        String beurtString = readerWriter.readBoard("Turns")[0];
        turns = Integer.parseInt(beurtString);
        //who's turn it is
        String[] strings = readerWriter.readBoard("Turns");
        getPlayers(Integer.parseInt(strings[0]));
    }

    //get the readwriter class
    public ReaderWriter getReaderWriter() {
        return readerWriter;
    }

    public void addTurns() {
        this.turns++;
    }

    public int getTurns() {
        return turns;
    }

    public void setGesaved(boolean gesaved) {
        isGesaved = gesaved;
    }

    public boolean isGesaved() {
        return isGesaved;
    }

    public void setSingleplayer() {
        this.isSingleplayer = true;
    }

    public boolean isSingleplayer() {
        return this.isSingleplayer;
    }
}
