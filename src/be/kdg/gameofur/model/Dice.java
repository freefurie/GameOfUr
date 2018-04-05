package be.kdg.gameofur.model;

import java.util.Random;

/**
 * This class contains three values, one for each respective dice.
 *
 * @author William Van Bouwel
 * @author Anouk As
 */
public class Dice {
    //attributes
    private final int MAX_VALUE = 2;
    private int valueDice1;
    private int valueDice2;
    private int valueDice3;
    private Random random;
    private boolean isRolled;

    //constructor
    Dice() {
        this.random = new Random();
        this.isRolled = false;
    }

    //methods
    public void roll() {
        valueDice1 = random.nextInt(MAX_VALUE);
        valueDice2 = random.nextInt(MAX_VALUE);
        valueDice3 = random.nextInt(MAX_VALUE);
    }

    public int getValueDice1() {
        return valueDice1;
    }

    public int getValueDice2() {
        return valueDice2;
    }

    public int getValueDice3() {
        return valueDice3;
    }

    public void setRolled(boolean TF) {
        isRolled = TF;
    }

    public boolean getRolled() {
        return isRolled;
    }

    /**
     * The toString method returns the total of moves you are allowed to make, based on the values thrown.
     * If the total is 0, it'll return 4. Otherwise it'll just return it's total.
     *
     * @return string of total amount of moves
     */
    @Override
    public String toString() {
        int totaal = valueDice1 + valueDice2 + valueDice3;
        if (totaal == 0) {
            return "4";
        } else {
            return String.valueOf(totaal);
        }
    }
}
