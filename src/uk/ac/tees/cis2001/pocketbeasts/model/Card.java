package uk.ac.tees.cis2001.pocketbeasts.model;

import uk.ac.tees.cis2001.pocketbeasts.patterns.AttackBehaviour;
import java.io.Serializable;

/**
 * Represents a beast card in PocketBeasts.
 */
public class Card implements Serializable {
    private final String id;
    private final String name;
    private final int manaCost;
    private int attack;
    private int health;
    private AttackBehaviour attackBehaviour;

    /**
     * Constructs a Card object.
     * 
     * @param id The card's identifier.
     * @param name The card's name.
     * @param manaCost The mana cost to play the card.
     * @param attack The attack value.
     * @param health The health value.
     * @param attackBehaviour The attack behaviour pattern.
     */
    public Card(String id, String name, int manaCost, int attack, int health, AttackBehaviour attackBehaviour) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
        this.attackBehaviour = attackBehaviour;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getManaCost() { return manaCost; }
    public int getAttack() { return attack; }
    public int getHealth() { return health; }

    public void setAttack(int attack) { this.attack = attack; }
    public void setHealth(int health) { this.health = health; }
    public void setAttackBehaviour(AttackBehaviour attackBehaviour) { this.attackBehaviour = attackBehaviour; }

    // Added getter for attackBehaviour
    public AttackBehaviour getAttackBehaviour() {
        return attackBehaviour;
    }

    /**
     * Performs attack behaviour on target card or player.
     * 
     * @param target The target card or player.
     */
    public void performAttack(Object target) {
        attackBehaviour.attack(this, target);
    }

    /**
     * Damages this card.
     * @param amount Amount to damage.
     */
    public void damage(int amount) {
        this.health -= amount;
    }

    @Override
    public String toString() {
        return name + " (" + id + ") Mana: " + manaCost + ", Attack: " + attack + ", Health: " + health;
    }
}