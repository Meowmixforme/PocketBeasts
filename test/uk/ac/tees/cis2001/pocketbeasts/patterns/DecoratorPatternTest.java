package uk.ac.tees.cis2001.pocketbeasts.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.tees.cis2001.pocketbeasts.model.Card;

class DecoratorPatternTest {
    @Test
    void testBuffEffect() {
        Card base = new Card("B", "Buffed", 1, 2, 3, new BeastAttack());
        BuffEffect buffed = new BuffEffect(base, 2);
        assertEquals(4, buffed.getAttack());
        assertEquals(3, buffed.getHealth());
    }
}