package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.controller.Game;
import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.model.Player;
import uk.ac.tees.cis2001.pocketbeasts.patterns.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Main game window with fantasy adventure theme matching CardButton styling.
 */
public class GameFrame extends JFrame implements GameObserver {
    // Theme colors to match your CardButton
    private static final Color BACKGROUND_COLOR = new Color(35, 42, 55);        // Darker background
    private static final Color PANEL_BACKGROUND = new Color(45, 52, 65);        // Your card background
    private static final Color BUTTON_BACKGROUND = new Color(60, 70, 85);       // Lighter button background
    private static final Color ATTACK_BUTTON_COLOR = new Color(220, 50, 50);    // Red for attack
    private static final Color END_TURN_BUTTON_COLOR = new Color(50, 150, 220); // Blue for end turn
    private static final Color TEXT_COLOR = new Color(240, 235, 220);           // Parchment text
    private static final Color BORDER_COLOR = new Color(100, 110, 125);         // Border color
    
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
        super("PocketBeasts - Fantasy Adventure");
        this.game = game;
        this.game.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set the main background color
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        setupUI();
        setupButtonListeners();
        updateDisplay();
        
        setSize(1000, 800);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    
    private void setupUI() {
        // Style the info label
        setupInfoLabel();
        
        // Style the scroll pane for enemy area
        setupEnemyScrollPane();
        
        // Style the main buttons
        setupButtons();
        
        // Style the panels
        setupPanels();
        
        // Create the layout
        createLayout();
    }
    
    private void setupInfoLabel() {
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoLabel.setForeground(TEXT_COLOR);
        infoLabel.setOpaque(true);
        infoLabel.setBackground(PANEL_BACKGROUND);
        infoLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void setupEnemyScrollPane() {
        enemyScrollPane.setPreferredSize(new Dimension(700, 180));
        enemyScrollPane.setBackground(BACKGROUND_COLOR);
        enemyScrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        enemyScrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        
        // Style the scrollbars
        enemyScrollPane.getVerticalScrollBar().setBackground(PANEL_BACKGROUND);
        enemyScrollPane.getHorizontalScrollBar().setBackground(PANEL_BACKGROUND);
    }
    
    private void setupButtons() {
        // End Turn Button
        styleButton(endTurnButton, END_TURN_BUTTON_COLOR, "🔄 End Turn");
        endTurnButton.setPreferredSize(new Dimension(120, 50));
        
        // Attack Enemy Player Button
        styleButton(enemyPlayerButton, ATTACK_BUTTON_COLOR, "⚡ Attack Enemy Player");
        enemyPlayerButton.setPreferredSize(new Dimension(180, 50));
    }
    
    private void styleButton(JButton button, Color backgroundColor, String text) {
        button.setText(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(backgroundColor.darker(), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        button.setFocusPainted(false);
        button.setOpaque(true);
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(backgroundColor.brighter());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
    }
    
    private void setupPanels() {
        // Set backgrounds for all panels to match theme
        handPanel.setBackground(BACKGROUND_COLOR);
        inPlayPanel.setBackground(BACKGROUND_COLOR);
        enemyInPlayPanel.setBackground(BACKGROUND_COLOR);
        
        // Add borders to panels
        handPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            "Your Hand",
            0, 0,
            new Font("Arial", Font.BOLD, 12),
            TEXT_COLOR
        ));
        
        inPlayPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            "Your Beasts",
            0, 0,
            new Font("Arial", Font.BOLD, 12),
            TEXT_COLOR
        ));
        
        enemyInPlayPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            "Enemy Beasts",
            0, 0,
            new Font("Arial", Font.BOLD, 12),
            TEXT_COLOR
        ));
    }
    
    private void createLayout() {
        // Create top panel with info and enemy area
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(infoLabel, BorderLayout.NORTH);
        topPanel.add(enemyScrollPane, BorderLayout.CENTER);
        
        // Create button panel for the attack button
        JPanel attackButtonPanel = new JPanel(new FlowLayout());
        attackButtonPanel.setBackground(BACKGROUND_COLOR);
        attackButtonPanel.add(enemyPlayerButton);
        topPanel.add(attackButtonPanel, BorderLayout.EAST);
        
        // Create side panel for end turn button
        JPanel sidePanel = new JPanel(new FlowLayout());
        sidePanel.setBackground(BACKGROUND_COLOR);
        sidePanel.add(endTurnButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(handPanel, BorderLayout.SOUTH);
        add(inPlayPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
    }
    
    private void setupButtonListeners() {
        endTurnButton.addActionListener(this::handleEndTurn);
        enemyPlayerButton.addActionListener(this::handleEnemyPlayerAttack);
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

        // Update info label with styled text
        String infoText = "<html><center><b>" + player.getName() + "</b> | Health: <font color='#90EE90'>" + player.getHealth() + 
                         "</font> | Mana: <font color='#87CEEB'>" + player.getManaAvailable() + "</font>" +
                         "<br><b>Enemy " + enemy.getName() + "</b> | Health: <font color='#FFB6C1'>" + enemy.getHealth() + 
                         "</font> | Mana: <font color='#DDA0DD'>" + enemy.getManaAvailable() + "</font></center></html>";
        infoLabel.setText(infoText);

        // End game UI lock and winner message
        if (game.isGameOver()) {
            showGameOverDialog();
            disableAllControls();
            return;
        }

        // Update all panels
        updatePanels(player, enemy);
        
        // Update button states
        updateButtonStates();
    }
    
    private void showGameOverDialog() {
        JOptionPane optionPane = new JOptionPane(
            game.getWinnerMessage(),
            JOptionPane.INFORMATION_MESSAGE
        );
        
        // Style the dialog
        JDialog dialog = optionPane.createDialog(this, "Game Over");
        dialog.getContentPane().setBackground(PANEL_BACKGROUND);
        dialog.setVisible(true);
    }
    
    private void disableAllControls() {
        Player player = game.getCurrentPlayer();
        Player enemy = game.getOtherPlayer();
        
        handPanel.updateHand(player.getHand(), c -> {}, false);
        inPlayPanel.updateInPlay(player.getInPlay(), null, c -> {}, false);
        enemyInPlayPanel.updateInPlay(enemy.getInPlay(), false, c -> {});
        endTurnButton.setEnabled(false);
        enemyPlayerButton.setEnabled(false);
    }
    
    private void updatePanels(Player player, Player enemy) {
        // Hand
        handPanel.updateHand(player.getHand(), card -> {
            game.playCard(card);
        }, true);

        // In play (player)
        inPlayPanel.updateInPlay(player.getInPlay(), selectedAttacker, card -> {
            selectedAttacker = card;
            updateDisplay();
        }, true);

        // Enemy in play (targets)
        AttackBehaviour attackBehaviour = selectedAttacker != null ? selectedAttacker.getAttackBehaviour() : null;
        boolean enableBeastAttack = (selectedAttacker != null && attackBehaviour instanceof BeastAttack && !selectedAttacker.hasAttackedThisTurn());
        enemyInPlayPanel.updateInPlay(enemy.getInPlay(), enableBeastAttack, enemyCard -> {
            if (selectedAttacker != null && attackBehaviour instanceof BeastAttack && !selectedAttacker.hasAttackedThisTurn()) {
                game.attack(selectedAttacker, enemyCard);
                selectedAttacker.setAttackedThisTurn(true);
                selectedAttacker = null;
                updateDisplay();
            } else {
                showStyledMessage("Select a beast attacker that hasn't already attacked!");
            }
        });
    }
    
    private void updateButtonStates() {
        AttackBehaviour attackBehaviour = selectedAttacker != null ? selectedAttacker.getAttackBehaviour() : null;
        boolean enableDirectAttack = (selectedAttacker != null && attackBehaviour instanceof DirectAttack && !selectedAttacker.hasAttackedThisTurn());
        enemyPlayerButton.setEnabled(enableDirectAttack);
        
        // Update button colors based on state
        if (enableDirectAttack) {
            enemyPlayerButton.setBackground(ATTACK_BUTTON_COLOR.brighter());
        } else {
            enemyPlayerButton.setBackground(ATTACK_BUTTON_COLOR.darker());
        }
    }
    
    private void showStyledMessage(String message) {
        JOptionPane optionPane = new JOptionPane(
            message,
            JOptionPane.WARNING_MESSAGE
        );
        
        JDialog dialog = optionPane.createDialog(this, "Invalid Action");
        dialog.getContentPane().setBackground(PANEL_BACKGROUND);
        dialog.setVisible(true);
    }

    private void handleEnemyPlayerAttack(ActionEvent e) {
        if (selectedAttacker != null && selectedAttacker.getAttackBehaviour() instanceof DirectAttack && !selectedAttacker.hasAttackedThisTurn()) {
            game.attack(selectedAttacker, game.getOtherPlayer());
            selectedAttacker.setAttackedThisTurn(true);
            selectedAttacker = null;
            updateDisplay();
        } else {
            showStyledMessage("Select a direct attacker that hasn't already attacked!");
        }
    }
}