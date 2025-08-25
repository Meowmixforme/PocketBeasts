package uk.ac.tees.cis2001.pocketbeasts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of cards.
 */
public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int count() { return cards.size(); }

    public Card draw() {
        if (cards.isEmpty()) return null;
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}