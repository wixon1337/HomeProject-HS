package hu.flowacademy.Hearthstone.Model.Heroes;

public abstract class Hero {
    private final Integer defaultHealth;
    private Integer health;
    private Integer abilityCost;

    public Hero() {
        this.defaultHealth = 30;
        this.health = 30;
        this.abilityCost = 2;
    }

    public Integer getDefaultHealth() {
        return defaultHealth;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getAbilityCost() {
        return abilityCost;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "defaultHealth=" + defaultHealth +
                ", health=" + health +
                ", abilityCost=" + abilityCost +
                '}';
    }
}
