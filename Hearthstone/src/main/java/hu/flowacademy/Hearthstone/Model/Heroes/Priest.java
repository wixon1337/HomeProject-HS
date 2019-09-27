package hu.flowacademy.Hearthstone.Model.Heroes;

import hu.flowacademy.Hearthstone.Model.Cards.Minion;

public class Priest extends Hero{

    private final Integer restoreAmount;

    public Priest() {
        super();
        this.restoreAmount = 2;
    }

    public void heal(Minion target) {
        target.setHealth(target.getHealth() + restoreAmount);
    }

    public void heal(Hero target) {
        target.setHealth(target.getHealth() + restoreAmount);
    }

    @Override
    public String toString() {
        return "Priest{" +
                "defaultHealth=" + this.getDefaultHealth() +
                ", health=" + this.getHealth() +
                ", abilityCost=" + this.getAbilityCost() +
                "restoreAmount=" + restoreAmount +
                '}';
    }
}
