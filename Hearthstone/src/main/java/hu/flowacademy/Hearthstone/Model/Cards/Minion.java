package hu.flowacademy.Hearthstone.Model;

public class Minion extends Card {
    private Integer health;
    private Integer attack;
    private Integer defaultHealth;
    private Integer defaultAttack;

    public Minion(Integer health, Integer attack) {
        this.health = health;
        this.attack = attack;
        this.defaultHealth = health;
        this.defaultAttack = attack;
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
}
