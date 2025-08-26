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

    // New: Track if this card has attacked this turn
    private boolean attackedThisTurn = false;

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

    // New: Getter and setter for attackedThisTurn
    public boolean hasAttackedThisTurn() { return attackedThisTurn; }
    public void setAttackedThisTurn(boolean attacked) { this.attackedThisTurn = attacked; }

    public AttackBehaviour getAttackBehaviour() { return attackBehaviour; }

    public void performAttack(Object target) {
        attackBehaviour.attack(this, target);
    }

    public void damage(int amount) {
        this.health -= amount;
    }

    @Override
    public String toString() {
        return name + " (" + id + ") Mana: " + manaCost + ", Attack: " + attack + ", Health: " + health +
               (attackedThisTurn ? " [USED]" : "");
    }
}