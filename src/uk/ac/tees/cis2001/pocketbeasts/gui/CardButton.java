package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * Custom button for displaying cards with images and text information.
 */
public class CardButton extends JButton {
    private static final int CARD_WIDTH = 120;
    private static final int CARD_HEIGHT = 160;
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 80;
    
    private final Card card;
    private ImageIcon cardImage;
    
    public CardButton(Card card) {
        this.card = card;
        loadCardImage();
        setupButton();
    }
    
    private void loadCardImage() {
        try {
            // Map card names to image files
            String imageName = getImageNameForCard(card.getName());
            InputStream imageStream = getClass().getResourceAsStream("/images/" + imageName + ".png");
            
            if (imageStream != null) {
                BufferedImage originalImage = ImageIO.read(imageStream);
                Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                cardImage = new ImageIcon(scaledImage);
            } else {
                // Fallback: create a simple colored rectangle if image not found
                BufferedImage fallbackImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = fallbackImage.createGraphics();
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
                g2d.setColor(Color.BLACK);
                g2d.drawString(card.getName(), 10, 40);
                g2d.dispose();
                cardImage = new ImageIcon(fallbackImage);
            }
        } catch (IOException e) {
            // Create fallback image
            BufferedImage fallbackImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = fallbackImage.createGraphics();
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
            g2d.setColor(Color.BLACK);
            g2d.drawString(card.getName(), 10, 40);
            g2d.dispose();
            cardImage = new ImageIcon(fallbackImage);
        }
    }
    
    private String getImageNameForCard(String cardName) {
        // Map your card names to image file names
        switch (cardName.toLowerCase()) {
            case "wolf": return "Shadow_Bat";
            case "owl": return "Wind_Sprite";
            case "dragon": return "Fire_Whelp";
            case "bear": return "Moss_Golem";
            case "fox": return "Frost_Lynx";
            case "eagle": return "Thunder_Hawk";
            case "fireballer": return "Fire_Whelp";
            case "sniper": return "Ashen_Lamb";
            case "archer": return "Sun_Sprite";
            case "fire mage": return "Fire_Whelp";
            default: return "Stone_Rhino"; // Default fallback
        }
    }
    
    private void setupButton() {
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setLayout(new BorderLayout());
        
        // Image at top
        JLabel imageLabel = new JLabel(cardImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
        
        // Text info at bottom
        String infoText = "<html><center>" + card.getName() + "<br>" +
                         "M:" + card.getManaCost() + " A:" + card.getAttack() + " H:" + card.getHealth() +
                         (card.hasAttackedThisTurn() ? "<br>[USED]" : "") +
                         "</center></html>";
        
        JLabel infoLabel = new JLabel(infoText);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel, BorderLayout.SOUTH);
        
        // Remove default button styling to show the image clearly
        setBorderPainted(true);
        setContentAreaFilled(true);
        setFocusPainted(false);
    }
    
    public Card getCard() {
        return card;
    }
    
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        setOpaque(true);
    }
}