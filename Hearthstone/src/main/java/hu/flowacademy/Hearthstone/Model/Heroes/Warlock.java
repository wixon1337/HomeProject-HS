package hu.flowacademy.Hearthstone.Model.Heroes;

import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Card;

import java.util.ArrayList;

public class Warlock extends Hero {

    private final Integer abilityDamage;
    private final String name;

    public Warlock() {
        super();
        this.name = "Warlock";
        this.abilityDamage = 2;
    }

    public Integer getAbilityDamage() {
        return abilityDamage;
    }

    public String getName() {
        return name;
    }

    public void lifeTapByPlayer1(Board board) {
        this.setHealth(this.getHealth() - abilityDamage);
        board.drawCardByPlayer1();
    }

    public void lifeTapByPlayer2(Board board) {
        this.setHealth(this.getHealth() - abilityDamage);
        board.drawCardByPlayer2();
    }

    @Override
    public String toString() {
        return "Warlock{" +
                "defaultHealth=" + this.getDefaultHealth() +
                ", health=" + this.getHealth() +
                ", abilityCost=" + this.getAbilityCost() +
                "abilityDamage=" + abilityDamage +
                '}';
    }
}
