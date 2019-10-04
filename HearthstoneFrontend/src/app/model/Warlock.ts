export class Warlock {
    abilityCost: number;
    health: number;
    name: String;
    abilityDamage: number;

    constructor(abilityCost: number, health: number, name: String, abilityDamage: number) {
        this.abilityCost = abilityCost;
        this.health = health;
        this.name = name;
        this.abilityDamage = abilityDamage;
    }
}