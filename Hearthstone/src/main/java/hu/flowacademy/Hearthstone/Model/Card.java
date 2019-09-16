package hu.flowacademy.Hearthstone.Model;

public class Card {
    private Long mana;

    public Card(Long mana) {
        this.mana = mana;
    }

    public Long getMana() {
        return mana;
    }

    public void setMana(Long mana) {
        this.mana = mana;
    }
}
