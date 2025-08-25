package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

/**
 * Attacks another beast card.
 */
public class BeastAttack implements AttackBehaviour {
    @Override
    public void attack(Card self, Object target) {
        if (target instanceof Card targetCard) {
            targetCard.damage(self.getAttack());
            self.damage(targetCard.getAttack());
        }
    }
}