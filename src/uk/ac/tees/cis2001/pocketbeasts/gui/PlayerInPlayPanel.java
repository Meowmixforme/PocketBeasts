package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Panel for displaying player's in-play area and handling attacker selection.
 */
public class PlayerInPlayPanel extends JPanel {
    public PlayerInPlayPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Your Beasts"));
    }

    public void updateInPlay(List<Card> inPlay, Card selectedAttacker, Consumer<Card> onSelect, boolean enabled) {
        removeAll();
        for (Card card : inPlay) {
            JButton cardBtn = new JButton(card.toString());
            if (selectedAttacker == card) {
                cardBtn.setBackground(Color.YELLOW);
            }
            cardBtn.setEnabled(enabled && !card.hasAttackedThisTurn());
            cardBtn.addActionListener(ev -> onSelect.accept(card));
            add(cardBtn);
        }
        revalidate();
        repaint();
    }
}