package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.List;

/**
 * Panel for displaying player's hand and allowing card play.
 */
public class HandPanel extends JPanel {
    public HandPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Your Hand"));
    }

    public void updateHand(List<Card> hand, Consumer<Card> onCardPlayed, boolean enabled) {
        removeAll();
        for (Card card : hand) {
            JButton cardBtn = new JButton(card.toString());
            cardBtn.setEnabled(enabled);
            cardBtn.addActionListener(ev -> onCardPlayed.accept(card));
            add(cardBtn);
        }
        revalidate();
        repaint();
    }
}