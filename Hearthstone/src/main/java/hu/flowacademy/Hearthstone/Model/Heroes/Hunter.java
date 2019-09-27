package hu.flowacademy.Hearthstone.Model.Heroes;

public class Hunter extends Hero {

    private Integer abilityDamage;

    public Hunter() {
        super();
        this.abilityDamage = 2;
    }

    public void steadyShot(Hero target) {
        target.setHealth(target.getHealth() - abilityDamage);
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "defaultHealth=" + this.getDefaultHealth() +
                ", health=" + this.getHealth() +
                ", abilityCost=" + this.getAbilityCost() +
                "abilityDamage=" + abilityDamage +
                '}';
    }
}
