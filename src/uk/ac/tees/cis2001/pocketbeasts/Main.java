package uk.ac.tees.cis2001.pocketbeasts;

import uk.ac.tees.cis2001.pocketbeasts.controller.Game;
import uk.ac.tees.cis2001.pocketbeasts.model.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;
import uk.ac.tees.cis2001.pocketbeasts.gui.GameFrame;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Example: Deck with both BeastAttack and DirectAttack cards
        List<Card> deck1 = new ArrayList<>();
        deck1.add(new Card("C1", "Wolf", 1, 2, 2, new BeastAttack())); // beast
        deck1.add(new Card("C2", "Owl", 2, 1, 3, new BeastAttack()));  // beast
        deck1.add(new Card("C3", "Dragon", 3, 4, 4, new BeastAttack())); // beast
        deck1.add(new Card("D1", "Fireballer", 2, 3, 2, new DirectAttack())); // direct
        deck1.add(new Card("D2", "Sniper", 2, 2, 1, new DirectAttack())); // direct

        List<Card> deck2 = new ArrayList<>();
        deck2.add(new Card("C4", "Bear", 1, 3, 2, new BeastAttack()));
        deck2.add(new Card("C5", "Fox", 2, 1, 2, new BeastAttack()));
        deck2.add(new Card("C6", "Eagle", 3, 3, 3, new BeastAttack()));
        deck2.add(new Card("D3", "Archer", 2, 2, 2, new DirectAttack()));
        deck2.add(new Card("D4", "Fire Mage", 3, 4, 1, new DirectAttack()));

        Player p1 = new Player("James", new Deck(deck1));
        Player p2 = new Player("Steve", new Deck(deck2));

        Game game = new Game(p1, p2);
        SwingUtilities.invokeLater(() -> {
            new GameFrame(game);
            game.start();
            game.nextTurn();
        });
    }
}