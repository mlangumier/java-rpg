package rpg.utils;

import rpg.enums.DiceType;

import java.util.Random;

public class Dice {
    private DiceType value;
    private final Random random = new Random();

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
     * Roll a dice of the chosen value
     *
     * @return The value of the dice roll
     */
    public int rollDice() {
        return random.nextInt(value.getValue()) + 1;
    }

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
