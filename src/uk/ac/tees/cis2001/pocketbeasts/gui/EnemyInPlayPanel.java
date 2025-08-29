package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * Panel for displaying enemy's in-play area and handling attack targets.
 */
public class EnemyInPlayPanel extends JPanel {
    public EnemyInPlayPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Enemy Beasts"));
    }

    public void updateInPlay(List<Card> inPlay, boolean enableTarget, Consumer<Card> onTarget) {
        removeAll();
        for (Card card : inPlay) {
            CardButton cardBtn = new CardButton(card);
            cardBtn.setEnabled(enableTarget);
            cardBtn.addActionListener(ev -> onTarget.accept(card));
            add(cardBtn);
        }
        revalidate();
        repaint();
    }
}