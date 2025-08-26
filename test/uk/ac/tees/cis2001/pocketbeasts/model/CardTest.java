package uk.ac.tees.cis2001.pocketbeasts.model;

import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.BeastAttack;

public class CardTest {

    public CardTest() {
        // Default public constructor
    }

    @Test
    public void testCardConstructorAndGetters() {
        Card card = new Card("BR", "Barn Rat", 1, 1, 2, new BeastAttack());
        assertEquals("BR", card.getId());
        assertEquals("Barn Rat", card.getName());
        assertEquals(1, card.getManaCost());
        assertEquals(1, card.getAttack());
        assertEquals(2, card.getHealth());
    }

    @Test
    public void testDamage() {
        Card card = new Card("BR", "Barn Rat", 1, 1, 2, new BeastAttack());
        card.damage(1);
        assertEquals(1, card.getHealth());
    }
}