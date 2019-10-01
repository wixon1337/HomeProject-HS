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

    constructor(id: number, name: String, attack: number, health: number, taunt: boolean, charge: boolean, summoned: boolean) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.taunt = taunt;
        this.charge = charge;
        this.summoned = summoned;
    }
}