package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.model.Player;

/**
 * Attacks a player directly.
 */
public class DirectAttack implements AttackBehaviour {
    @Override
    public void attack(Card self, Object target) {
        if (target instanceof Player player) {
            System.out.println(self.getName() + " attacks player for " + self.getAttack());
            player.damage(self.getAttack());
        } else {
            System.out.println("DirectAttack used on non-player target!");
        }
    }
}