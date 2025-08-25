package uk.ac.tees.cis2001.pocketbeasts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in PocketBeasts.
 */
public class Player {
    private final String name;
    private int manaAvailable;
    private int manaTicker;
    private int health;
    private final Deck deck;
    private final List<Card> hand;
    private final List<Card> inPlay;
    private final List<Card> graveyard;
    public static final int MAX_MANA = 9;
    public static final int START_HEALTH = 15;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.inPlay = new ArrayList<>();
        this.graveyard = new ArrayList<>();
        this.health = START_HEALTH;
        this.manaAvailable = 0;
        this.manaTicker = 0;
    }

    public String getName() { return name; }
    public int getManaAvailable() { return manaAvailable; }
    public int getHealth() { return health; }
    public Deck getDeck() { return deck; }
    public List<Card> getHand() { return hand; }
    public List<Card> getInPlay() { return inPlay; }
    public List<Card> getGraveyard() { return graveyard; }

    public void newGame() {
        deck.shuffle();
        hand.clear();
        inPlay.clear();
        graveyard.clear();
        manaAvailable = 0;
        manaTicker = 0;
        health = START_HEALTH;
        for (int i = 0; i < 4; i++) {
            drawCard();
        }
    }

    public void addMana() {
        if (manaTicker < MAX_MANA) manaTicker++;
        manaAvailable = manaTicker;
    }

    public void useMana(int amount) {
        manaAvailable -= amount;
    }

    public void drawCard() {
        Card c = deck.draw();
        if (c != null) hand.add(c);
    }

    public boolean damage(int amount) {
        health -= amount;
        return health <= 0;
    }

    public void playCard(Card card) {
        if (card.getManaCost() <= manaAvailable) {
            inPlay.add(card);
            useMana(card.getManaCost());
            hand.remove(card);
        }
    }
}