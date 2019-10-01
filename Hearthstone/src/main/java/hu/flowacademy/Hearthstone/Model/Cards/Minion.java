package hu.flowacademy.Hearthstone.Model.Cards;

import javax.validation.constraints.Min;

public abstract class Minion extends Card {
    private Integer attack;
    private Integer health;
    private final Integer defaultAttack;
    private final Integer defaultHealth;
    private boolean isCharge;
    private boolean isTaunt;
    private boolean isSummoned;

    public Minion(Integer cost, Integer attack, Integer health, boolean isCharge, boolean isTaunt) {
        super(cost);
        this.attack = attack;
        this.health = health;
        this.defaultAttack = attack;
        this.defaultHealth = health;
        this.isCharge = isCharge;
        this.isTaunt = isTaunt;
        this.isSummoned = false;
    }

    public Minion(Integer cost, Integer attack, Integer health) {
        super(cost);
        this.attack = attack;
        this.health = health;
        this.defaultAttack = attack;
        this.defaultHealth = health;
        this.isCharge = false;
        this.isTaunt = false;
        this.isSummoned = false;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefaultHealth() {
        return defaultHealth;
    }

    public Integer getDefaultAttack() {
        return defaultAttack;
    }

    public boolean isCharge() {
        return isCharge;
    }

    public void setCharge(boolean charge) {
        isCharge = charge;
    }

    public boolean isTaunt() {
        return isTaunt;
    }

    public void setTaunt(boolean taunt) {
        isTaunt = taunt;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean summoned) {
        isSummoned = summoned;
    }
}
