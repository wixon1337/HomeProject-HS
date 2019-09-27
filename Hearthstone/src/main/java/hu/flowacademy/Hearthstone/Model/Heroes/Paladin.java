package hu.flowacademy.Hearthstone.Model.Heroes;

import hu.flowacademy.Hearthstone.Model.Cards.Minion;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.SimpleMinion;

import java.util.LinkedList;

public class Paladin extends Hero {

    public void reinforce(LinkedList<Minion> boardside) {
        boardside.add(new SimpleMinion(1,1,1,false, false, "Silver Hand Recruit"));
    }

    @Override
    public String toString() {
        return "Paladin{" +
                "defaultHealth=" + this.getDefaultHealth() +
                ", health=" + this.getHealth() +
                ", abilityCost=" + this.getAbilityCost() +
                '}';
    }
}
