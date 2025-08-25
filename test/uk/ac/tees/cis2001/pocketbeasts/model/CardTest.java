package uk.ac.tees.cis2001.pocketbeasts.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.BeastAttack;

class CardTest {

    @Test
    void testCardConstructorAndGetters() {
        Card card = new Card("BR", "Barn Rat", 1, 1, 2, new BeastAttack());
        assertEquals("BR", card.getId());
        assertEquals("Barn Rat", card.getName());
        assertEquals(1, card.getManaCost());
        assertEquals(1, card.getAttack());
        assertEquals(2, card.getHealth());
    }

    @Test
    void testDamage() {
        Card card = new Card("BR", "Barn Rat", 1, 1, 2, new BeastAttack());
        card.damage(1);
        assertEquals(1, card.getHealth());
    }
}