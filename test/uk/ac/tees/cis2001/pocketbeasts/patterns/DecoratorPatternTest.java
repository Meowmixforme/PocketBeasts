package uk.ac.tees.cis2001.pocketbeasts.patterns;

import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.tees.cis2001.pocketbeasts.model.Card;

public class DecoratorPatternTest {
    @Test
    public void testBuffEffect() {
        Card base = new Card("B", "Buffed", 1, 2, 3, new BeastAttack());
        BuffEffect buffed = new BuffEffect(base, 2);
        assertEquals(4, buffed.getAttack());
        assertEquals(3, buffed.getHealth());
    }
}