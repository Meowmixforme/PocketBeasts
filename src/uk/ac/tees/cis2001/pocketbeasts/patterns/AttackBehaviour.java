package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

/**
 * Strategy interface for card attack behaviours.
 */
public interface AttackBehaviour {
    void attack(Card self, Object target);
}