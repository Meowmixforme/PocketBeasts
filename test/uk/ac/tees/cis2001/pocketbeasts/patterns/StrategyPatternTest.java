package uk.ac.tees.cis2001.pocketbeasts.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.model.Player;
import java.util.Collections;

class StrategyPatternTest {

    @Test
    void testDirectAttack() {
        Player player = new Player("Test", new uk.ac.tees.cis2001.pocketbeasts.model.Deck(Collections.emptyList()));
        Card card = new Card("A", "Attacker", 1, 2, 2, new DirectAttack());
        card.performAttack(player);
        assertEquals(Player.START_HEALTH - 2, player.getHealth());
    }

    @Test
    void testBeastAttack() {
        Card attacker = new Card("A", "Attacker", 1, 2, 2, new BeastAttack());
        Card defender = new Card("B", "Defender", 1, 1, 3, new BeastAttack());
        attacker.performAttack(defender);
        assertEquals(1, attacker.getHealth());
        assertEquals(1, defender.getHealth());
    }
}