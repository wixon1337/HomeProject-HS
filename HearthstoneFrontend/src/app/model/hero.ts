export class Hero {
    abilityCost: number;
    health: number;
    name: String;

    constructor(abilityCost: number, health: number, name: String) {
        this.abilityCost = abilityCost;
        this.health = health;
        this.name = name;
    }
}