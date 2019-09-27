package hu.flowacademy.Hearthstone.Model.Heroes;

import hu.flowacademy.Hearthstone.Model.Cards.Minion;

public class Mage extends Hero {

    private Integer abilityDamage;

    public Mage() {
        super();
        this.abilityDamage = 2;
    }

    public void fireblast(Hero target) {
        target.setHealth(target.getHealth() - abilityDamage);
    }

    public void fireblast(Minion target) {
        target.setHealth(target.getHealth() - abilityDamage);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "defaultHealth=" + this.getDefaultHealth() +
                ", health=" + this.getHealth() +
                ", abilityCost=" + this.getAbilityCost() +
                "abilityDamage=" + abilityDamage +
                '}';
    }
}
