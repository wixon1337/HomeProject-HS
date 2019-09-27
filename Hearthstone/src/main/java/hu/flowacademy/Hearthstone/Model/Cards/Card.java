package hu.flowacademy.Hearthstone.Model.Cards;

public abstract class Card {
    private Integer cost;
    private Integer defaultCost;
    private boolean isSummoned;

    public Card(Integer cost) {
        this.cost = cost;
        this.defaultCost = cost;
        this.isSummoned = true;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDefaultCost() {
        return defaultCost;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public void setSummoned(boolean summoned) {
        isSummoned = summoned;
    }
}
