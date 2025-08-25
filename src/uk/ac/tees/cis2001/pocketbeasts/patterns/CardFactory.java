package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

/**
 * Factory for creating cards.
 */
public class CardFactory {
    public static Card createStandardCard(String id, String name, int manaCost, int attack, int health) {
        // Standard cards default to BeastAttack behaviour
        return new Card(id, name, manaCost, attack, health, new BeastAttack());
    }
}