package uk.ac.tees.cis2001.pocketbeasts.controller;

import uk.ac.tees.cis2001.pocketbeasts.model.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;
import java.util.List;

/**
 * Controls PocketBeasts game logic.
 */
public class Game extends GameObservable {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private boolean gameOver = false;

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        this.otherPlayer = player2;
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public Player getOtherPlayer() { return otherPlayer; }
    public boolean isGameOver() { return gameOver; }

    public void start() {
        player1.newGame();
        player2.newGame();
        notifyObservers();
    }

    public void nextTurn() {
        // Mana and draw phase
        currentPlayer.addMana();
        currentPlayer.drawCard();
        notifyObservers();
    }

    public void attack(Card attacker, Object target) {
        attacker.performAttack(target);
        // Remove dead cards
        removeDeadCards(currentPlayer.getInPlay(), currentPlayer);
        removeDeadCards(otherPlayer.getInPlay(), otherPlayer);
        // Check for game over
        if (otherPlayer.getHealth() <= 0) {
            gameOver = true;
        }
        notifyObservers();
    }

    public void playCard(Card card) {
        currentPlayer.playCard(card);
        notifyObservers();
    }

    public void endTurn() {
        // Swap current player
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        notifyObservers();
    }

    private void removeDeadCards(List<Card> cards, Player owner) {
        cards.removeIf(card -> {
            if (card.getHealth() <= 0) {
                owner.getGraveyard().add(card);
                return true;
            }
            return false;
        });
    }
}