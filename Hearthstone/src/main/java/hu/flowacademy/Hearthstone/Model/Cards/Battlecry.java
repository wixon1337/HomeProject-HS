package hu.flowacademy.Hearthstone.Model.Cards;

public abstract class Battlecry extends Minion {

    public Battlecry(Integer cost, Integer attack, Integer health, boolean isCharge, boolean isTaunt) {
        super(cost, attack, health, isCharge, isTaunt);
    }

}
