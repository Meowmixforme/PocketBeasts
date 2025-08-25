package uk.ac.tees.cis2001.pocketbeasts;

import uk.ac.tees.cis2001.pocketbeasts.model.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;
import uk.ac.tees.cis2001.pocketbeasts.controller.Game;
import uk.ac.tees.cis2001.pocketbeasts.gui.GameFrame;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Main entry point for PocketBeasts.
 */
public class Main {
    public static void main(String[] args) {
        // Use the Factory pattern to build starter cards
        List<Card> starterCards = new ArrayList<>();
        starterCards.add(CardFactory.createStandardCard("BR", "Barn Rat", 1, 1, 1));
        starterCards.add(CardFactory.createStandardCard("SP", "Scampering Pup", 2, 2, 1));
        starterCards.add(CardFactory.createStandardCard("HB", "Hardshell Beetle", 2, 1, 2));
        starterCards.add(CardFactory.createStandardCard("VHC", "Vicious House Cat", 3, 3, 2));
        starterCards.add(CardFactory.createStandardCard("GD", "Guard Dog", 3, 2, 3));
        starterCards.add(CardFactory.createStandardCard("ARH", "All Round Hound", 3, 3, 3));
        starterCards.add(CardFactory.createStandardCard("MO", "Moor Owl", 4, 4, 2));
        starterCards.add(CardFactory.createStandardCard("HT", "Highland Tiger", 5, 4, 4));

        // Each player gets a deck with two of each card
        List<Card> deck1 = new ArrayList<>(), deck2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (Card c : starterCards) {
                deck1.add(CardFactory.createStandardCard(c.getId(), c.getName(), c.getManaCost(), c.getAttack(), c.getHealth()));
                deck2.add(CardFactory.createStandardCard(c.getId(), c.getName(), c.getManaCost(), c.getAttack(), c.getHealth()));
            }
        }

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