export class Card {
    id: number;
    name: String;
    attack: number;
    health: number;
    battlycry: String;
    taunt: boolean;
    charge: boolean;
    summoned: boolean;
    imgUrl: String;
    cost: number;

    constructor(id: number, name: String, attack: number, health: number, taunt: boolean, charge: boolean, summoned: boolean, cost: number) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.taunt = taunt;
        this.charge = charge;
        this.summoned = summoned;
        this.cost = cost;

    }
}