package hu.flowacademy.Hearthstone.Model.Cards;

public abstract class Card {
    public static Integer counter;
    private final Integer id;
    private Integer cost;
    private Integer defaultCost;

    public Card(Integer cost) {
        this.cost = cost;
        this.defaultCost = cost;
        if (counter == null) {
            counter = 0;
            this.id = counter++;
        } else {
            this.id = counter++;
        }
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

    public static Integer getCounter() {
        return counter;
    }

    public Integer getId() {
        return id;
    }
}
