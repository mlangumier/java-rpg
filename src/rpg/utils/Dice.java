package rpg.utils;

import rpg.enums.DiceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dice {
    private DiceType value;
    private final Random random = new Random();

    /**
     * Constructor
     *
     * @param value Value of the dice (how many faces the dice has)
     */
    public Dice(DiceType value) {
        this.value = value;
    }

    public DiceType getValue() {
        return value;
    }

    public void setValue(DiceType value) {
        this.value = value;
    }

    /**
     * Rolls the dice - Gives a random number between 1 and the number of sides on the dice
     *
     * @return The value of the dice roll
     */
    public int rollDice() {
        return random.nextInt(value.getValue()) + 1;
    }

    /**
     * Rolls the dice multiple times and gives the total of their values
     *
     * @param nbrDice The number of dice we want to roll
     * @return The total sum of the dice rolls
     */
    public int rollMultipleDice(int nbrDice) {
        int total = 0;

        for (int i = 0; i < nbrDice; i++) {
            int roll = this.rollDice();
            total += roll;
        }

        return total;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                '}';
    }
}
