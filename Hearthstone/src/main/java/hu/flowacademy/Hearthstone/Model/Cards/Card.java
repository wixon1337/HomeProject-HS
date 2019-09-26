package hu.flowacademy.Hearthstone.Model;

public abstract class Card {
    private Integer cost;
    private Integer defaultCost;

    public Card(Integer cost) {
        this.cost = cost;
        this.defaultCost = cost;
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
}
