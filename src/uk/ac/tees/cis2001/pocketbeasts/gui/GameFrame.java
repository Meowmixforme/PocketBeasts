package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.controller.Game;
import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.model.Player;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Main game window using panel classes and robust end game handling.
 */
public class GameFrame extends JFrame implements GameObserver {
    private final Game game;
    private final JLabel infoLabel = new JLabel();

    private final HandPanel handPanel = new HandPanel();
    private final PlayerInPlayPanel inPlayPanel = new PlayerInPlayPanel();
    private final EnemyInPlayPanel enemyInPlayPanel = new EnemyInPlayPanel();
    private final JScrollPane enemyScrollPane = new JScrollPane(enemyInPlayPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private final JButton endTurnButton = new JButton("End Turn");
    private final JButton enemyPlayerButton = new JButton("Attack Enemy Player");

    private Card selectedAttacker = null;

    public GameFrame(Game game) {
        super("PocketBeasts");
        this.game = game;
        this.game.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        enemyScrollPane.setPreferredSize(new Dimension(700, 120));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(infoLabel, BorderLayout.NORTH);
        topPanel.add(enemyScrollPane, BorderLayout.CENTER);
        topPanel.add(enemyPlayerButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(handPanel, BorderLayout.SOUTH);
        add(inPlayPanel, BorderLayout.CENTER);
        add(endTurnButton, BorderLayout.WEST);

        endTurnButton.addActionListener(this::handleEndTurn);
        enemyPlayerButton.addActionListener(this::handleEnemyPlayerAttack);

        updateDisplay();
        setSize(900, 700);
        setVisible(true);
    }

    private void handleEndTurn(ActionEvent e) {
        selectedAttacker = null;
        game.endTurn();
        game.nextTurn();
    }

    @Override
    public void onGameStateChanged() {
        updateDisplay();
    }

    private void updateDisplay() {
        Player player = game.getCurrentPlayer();
        Player enemy = game.getOtherPlayer();

        infoLabel.setText(player.getName() + " | Health: " + player.getHealth() + " | Mana: " + player.getManaAvailable() +
                "    ||    Enemy " + enemy.getName() + " | Health: " + enemy.getHealth() + " | Mana: " + enemy.getManaAvailable());

        // End game UI lock and winner message
        if (game.isGameOver()) {
            JOptionPane.showMessageDialog(this, game.getWinnerMessage(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            handPanel.updateHand(player.getHand(), c -> {}, false);
            inPlayPanel.updateInPlay(player.getInPlay(), null, c -> {}, false);
            enemyInPlayPanel.updateInPlay(enemy.getInPlay(), false, c -> {});
            endTurnButton.setEnabled(false);
            enemyPlayerButton.setEnabled(false);
            return;
        }

        // Hand
        handPanel.updateHand(player.getHand(), card -> {
            game.playCard(card);
        }, true);

        // In play (player)
        inPlayPanel.updateInPlay(player.getInPlay(), selectedAttacker, card -> {
            selectedAttacker = card;
            updateDisplay();
        }, true);

        // Determine attack behaviour of selected card
        AttackBehaviour attackBehaviour = selectedAttacker != null ? selectedAttacker.getAttackBehaviour() : null;

        // Enemy in play (targets)
        boolean enableBeastAttack = (selectedAttacker != null && attackBehaviour instanceof BeastAttack && !selectedAttacker.hasAttackedThisTurn());
        enemyInPlayPanel.updateInPlay(enemy.getInPlay(), enableBeastAttack, enemyCard -> {
            if (selectedAttacker != null && attackBehaviour instanceof BeastAttack && !selectedAttacker.hasAttackedThisTurn()) {
                game.attack(selectedAttacker, enemyCard);
                selectedAttacker.setAttackedThisTurn(true);
                selectedAttacker = null;
                updateDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "Select a beast attacker that hasn't already attacked!");
            }
        });

        // Enemy player attack button
        boolean enableDirectAttack = (selectedAttacker != null && attackBehaviour instanceof DirectAttack && !selectedAttacker.hasAttackedThisTurn());
        enemyPlayerButton.setEnabled(enableDirectAttack);

        // UI refresh
        handPanel.revalidate(); handPanel.repaint();
        inPlayPanel.revalidate(); inPlayPanel.repaint();
        enemyInPlayPanel.revalidate(); enemyInPlayPanel.repaint();
    }

    private void handleEnemyPlayerAttack(ActionEvent e) {
        if (selectedAttacker != null && selectedAttacker.getAttackBehaviour() instanceof DirectAttack && !selectedAttacker.hasAttackedThisTurn()) {
            game.attack(selectedAttacker, game.getOtherPlayer());
            selectedAttacker.setAttackedThisTurn(true);
            selectedAttacker = null;
            updateDisplay();
        } else {
            JOptionPane.showMessageDialog(this, "Select a direct attacker that hasn't already attacked!");
        }
    }
}