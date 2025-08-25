package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.controller.Game;
import uk.ac.tees.cis2001.pocketbeasts.model.*;
import uk.ac.tees.cis2001.pocketbeasts.patterns.GameObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Main game GUI window.
 */
public class GameFrame extends JFrame implements GameObserver {
    private final Game game;
    private final JLabel infoLabel = new JLabel("PocketBeasts");
    private final JPanel handPanel = new JPanel();
    private final JPanel inPlayPanel = new JPanel();
    private final JButton endTurnButton = new JButton("End Turn");

    public GameFrame(Game game) {
        super("PocketBeasts");
        this.game = game;
        this.game.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(infoLabel, BorderLayout.NORTH);
        add(handPanel, BorderLayout.SOUTH);
        add(inPlayPanel, BorderLayout.CENTER);
        add(endTurnButton, BorderLayout.EAST);
        endTurnButton.addActionListener(this::handleEndTurn);

        updateDisplay();
        setSize(800, 600);
        setVisible(true);
    }

    private void handleEndTurn(ActionEvent e) {
        game.endTurn();
        game.nextTurn();
    }

    @Override
    public void onGameStateChanged() {
        updateDisplay();
    }

    private void updateDisplay() {
        Player player = game.getCurrentPlayer();
        infoLabel.setText(player.getName() + " | Health: " + player.getHealth() + " | Mana: " + player.getManaAvailable());
        handPanel.removeAll();
        for (Card card : player.getHand()) {
            JButton cardBtn = new JButton(card.toString());
            cardBtn.addActionListener(ev -> {
                game.playCard(card);
            });
            handPanel.add(cardBtn);
        }
        inPlayPanel.removeAll();
        for (Card card : player.getInPlay()) {
            JButton cardBtn = new JButton(card.toString());
            cardBtn.addActionListener(ev -> {
                // Attack: for demonstration, always attack opponent directly
                game.attack(card, game.getOtherPlayer());
            });
            inPlayPanel.add(cardBtn);
        }
        handPanel.revalidate();
        handPanel.repaint();
        inPlayPanel.revalidate();
        inPlayPanel.repaint();
    }
}