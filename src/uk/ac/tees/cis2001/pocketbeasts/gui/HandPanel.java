package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.List;

/**
 * Panel for displaying player's hand and allowing card play.
 */
public class HandPanel extends JPanel {
    private JScrollPane scrollPane;
    private JPanel cardContainer;
    
    public HandPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Your Hand"));
        
        cardContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scrollPane = new JScrollPane(cardContainer, 
                JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(0, 200)); // Fixed height for full visibility
        
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateHand(List<Card> hand, Consumer<Card> onCardPlayed, boolean enabled) {
        cardContainer.removeAll();
        for (Card card : hand) {
            CardButton cardBtn = new CardButton(card);
            cardBtn.setEnabled(enabled);
            cardBtn.addActionListener(ev -> onCardPlayed.accept(card));
            cardContainer.add(cardBtn);
        }
        cardContainer.revalidate();
        cardContainer.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}