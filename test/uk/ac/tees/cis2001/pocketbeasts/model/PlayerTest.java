package uk.ac.tees.cis2001.pocketbeasts.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import uk.ac.tees.cis2001.pocketbeasts.patterns.BeastAttack;

class PlayerTest {
    @Test
    void testPlayerConstructorAndStart() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            cards.add(new Card("C" + i, "Card" + i, 1, 1, 1, new BeastAttack()));
        }
        Player player = new Player("Test", new Deck(cards));
        player.newGame();
        assertEquals("Test", player.getName());
        assertEquals(4, player.getHand().size());
        assertEquals(Player.START_HEALTH, player.getHealth());
        assertEquals(0, player.getManaAvailable());
    }

    @Test
    void testAddMana() {
        Player player = new Player("Test", new Deck(new ArrayList<>()));
        player.addMana();
        assertEquals(1, player.getManaAvailable());
    }

    @Test
    void testDamage() {
        Player player = new Player("Test", new Deck(new ArrayList<>()));
        boolean dead = player.damage(Player.START_HEALTH);
        assertTrue(dead);
    }
}