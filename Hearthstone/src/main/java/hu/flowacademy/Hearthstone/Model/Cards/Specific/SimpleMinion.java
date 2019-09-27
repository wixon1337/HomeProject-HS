package hu.flowacademy.Hearthstone.Model.Cards.Specific;

import hu.flowacademy.Hearthstone.Model.Cards.Minion;

public class SimpleMinion extends Minion {
    private String name;

    public SimpleMinion(Integer cost, Integer attack, Integer health, boolean isCharge, boolean isTaunt, String name) {
        super(cost, attack, health, isCharge, isTaunt);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
