package hu.flowacademy.Hearthstone.Model.Cards.Specific;

import hu.flowacademy.Hearthstone.Model.Cards.Battlecry;
import hu.flowacademy.Hearthstone.Model.Cards.Minion;
import hu.flowacademy.Hearthstone.Model.Heroes.Hero;

public class ShroomBrewer extends Battlecry {
    private final String name;
    private final Integer restoreAmount;

    public ShroomBrewer() {
        super(4, 4, 4, false, false);
        this.name = "Shroom Brewer";
        this.restoreAmount = 4;
    }

    public void BattleCry(Minion target) {
        target.setHealth(target.getHealth() + restoreAmount);
    }

    public void BattleCry(Hero target) {
        target.setHealth(target.getHealth() + restoreAmount);
    }

    public String getName() {
        return name;
    }
}
