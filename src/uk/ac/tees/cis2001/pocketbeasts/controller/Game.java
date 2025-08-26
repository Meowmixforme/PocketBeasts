package uk.ac.tees.cis2001.pocketbeasts.controller;

import uk.ac.tees.cis2001.pocketbeasts.model.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;
import java.util.List;

/**
 * Controls PocketBeasts game logic, including end-game conditions.
 */
public class Game extends GameObservable {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private boolean gameOver = false;
    private String winnerMessage = "";

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        this.otherPlayer = player2;
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public Player getOtherPlayer() { return otherPlayer; }
    public boolean isGameOver() { return gameOver; }
    public String getWinnerMessage() { return winnerMessage; }

    public void start() {
        player1.newGame();
        player2.newGame();
        notifyObservers();
    }

    public void nextTurn() {
        if (gameOver) return;
        currentPlayer.addMana();
        currentPlayer.drawCard();
        for (Card c : currentPlayer.getInPlay()) {
            c.setAttackedThisTurn(false);
        }
        checkGameOverConditions();
        notifyObservers();
    }

    public void attack(Card attacker, Object target) {
        if (gameOver) return;
        attacker.performAttack(target);
        removeDeadCards(currentPlayer.getInPlay(), currentPlayer);
        removeDeadCards(otherPlayer.getInPlay(), otherPlayer);
        checkGameOverConditions();
        notifyObservers();
    }

    public void playCard(Card card) {
        if (gameOver) return;
        currentPlayer.playCard(card);
        notifyObservers();
    }

    public void endTurn() {
        if (gameOver) return;
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

    private boolean isOutOfResources(Player player) {
        return player.getDeck().isEmpty() &&
               player.getHand().isEmpty() &&
               player.getInPlay().isEmpty();
    }

    private void checkGameOverConditions() {
        // Health-based win/loss
        if (player1.getHealth() <= 0 && player2.getHealth() <= 0) {
            gameOver = true;
            winnerMessage = "It's a tie! Both players have fallen!";
        } else if (player1.getHealth() <= 0) {
            gameOver = true;
            winnerMessage = player2.getName() + " wins! (" + player2.getHealth() + " health left)";
        } else if (player2.getHealth() <= 0) {
            gameOver = true;
            winnerMessage = player1.getName() + " wins! (" + player1.getHealth() + " health left)";
        }
        // Out of cards and plays
        else if (isOutOfResources(player1) && isOutOfResources(player2)) {
            gameOver = true;
            if (player1.getHealth() > player2.getHealth()) {
                winnerMessage = player1.getName() + " wins! (More health)";
            } else if (player2.getHealth() > player1.getHealth()) {
                winnerMessage = player2.getName() + " wins! (More health)";
            } else {
                winnerMessage = "It's a tie! Both players have equal health and no cards.";
            }
        }
    }
}