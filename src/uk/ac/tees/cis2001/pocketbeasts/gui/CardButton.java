package uk.ac.tees.cis2001.pocketbeasts.gui;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.patterns.DirectAttack;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

/**
 * Custom button for displaying cards with images and text information.
 */
public class CardButton extends JButton {
    // Card dimensions
    private static final int CARD_WIDTH = 120;
    private static final int CARD_HEIGHT = 160;
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 80;
    
    // Color scheme - Fantasy Adventure Theme
    private static final Color CARD_BACKGROUND = new Color(45, 52, 65);          // Dark slate blue
    private static final Color SELECTED_HIGHLIGHT = new Color(255, 215, 0);      // Gold
    private static final Color TEXT_BACKGROUND = new Color(240, 235, 220);       // Parchment
    private static final Color USED_CARD_COLOR = new Color(120, 120, 120);       // Muted gray
    private static final Color DIRECT_ATTACK_ACCENT = new Color(220, 50, 50);    // Red for direct attackers
    private static final Color BEAST_ATTACK_ACCENT = new Color(50, 150, 220);    // Blue for beast attackers
    
    private final Card card;
    private ImageIcon cardImage;
    private JLabel infoLabel;
    private boolean isSelected = false;
    
    public CardButton(Card card) {
        this.card = card;
        loadCardImage();
        setupButton();
    }
    
    private void loadCardImage() {
        try {
            String imageName = getImageNameForCard(card.getName());
            InputStream imageStream = getClass().getResourceAsStream("/images/" + imageName + ".png");
            
            if (imageStream != null) {
                BufferedImage originalImage = ImageIO.read(imageStream);
                Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                cardImage = new ImageIcon(scaledImage);
            } else {
                cardImage = createFallbackImage();
            }
        } catch (IOException e) {
            cardImage = createFallbackImage();
        }
    }
    
    private ImageIcon createFallbackImage() {
        BufferedImage fallbackImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = fallbackImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Create a gradient background
        GradientPaint gradient = new GradientPaint(0, 0, CARD_BACKGROUND.brighter(), 
                                                   IMAGE_WIDTH, IMAGE_HEIGHT, CARD_BACKGROUND.darker());
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        
        // Add card name
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g2d.getFontMetrics();
        String name = card.getName();
        int textWidth = fm.stringWidth(name);
        int x = (IMAGE_WIDTH - textWidth) / 2;
        int y = IMAGE_HEIGHT / 2;
        g2d.drawString(name, x, y);
        
        g2d.dispose();
        return new ImageIcon(fallbackImage);
    }
    
    private String getImageNameForCard(String cardName) {
        return cardName.replace(" ", "_");
    }
    
    private void setupButton() {
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setLayout(new BorderLayout());
        setBackground(CARD_BACKGROUND);
        
        // Create card border based on attack type
        Color accentColor = (card.getAttackBehaviour() instanceof DirectAttack) 
                           ? DIRECT_ATTACK_ACCENT 
                           : BEAST_ATTACK_ACCENT;
        
        Border outerBorder = BorderFactory.createLineBorder(accentColor, 2);
        Border innerBorder = BorderFactory.createLineBorder(CARD_BACKGROUND.brighter(), 1);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        // Image panel with padding
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        
        JLabel imageLabel = new JLabel(cardImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.CENTER);
        
        // Text info at bottom
        createInfoLabel();
        
        // Button styling
        setContentAreaFilled(true);
        setFocusPainted(false);
        setBorderPainted(true);
        
        // Add hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!isSelected && !card.hasAttackedThisTurn()) {
                    setBackground(CARD_BACKGROUND.brighter());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateAppearance();
            }
        });
        
        updateAppearance();
    }
    
    private void createInfoLabel() {
        String attackTypeIcon = (card.getAttackBehaviour() instanceof DirectAttack) ? "⚡" : "⚔";
        String usedStatus = card.hasAttackedThisTurn() ? "<br><font color='red'>[USED]</font>" : "";
        
        String infoText = "<html><center><b>" + card.getName() + "</b><br>" +
                         "<font size='-1'>M:" + card.getManaCost() + " A:" + card.getAttack() + 
                         " H:" + card.getHealth() + " " + attackTypeIcon + "</font>" +
                         usedStatus + "</center></html>";
        
        infoLabel = new JLabel(infoText);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 9));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setOpaque(true);
        infoLabel.setBackground(TEXT_BACKGROUND);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        add(infoLabel, BorderLayout.SOUTH);
    }
    
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        updateAppearance();
    }
    
    private void updateAppearance() {
        if (card.hasAttackedThisTurn()) {
            // Used card - grayed out
            setBackground(USED_CARD_COLOR);
            infoLabel.setBackground(USED_CARD_COLOR.brighter());
        } else if (isSelected) {
            // Selected card - gold highlight
            setBackground(SELECTED_HIGHLIGHT);
            infoLabel.setBackground(SELECTED_HIGHLIGHT.brighter());
        } else {
            // Normal card
            setBackground(CARD_BACKGROUND);
            infoLabel.setBackground(TEXT_BACKGROUND);
        }
    }
    
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        setOpaque(true);
    }
    
    public Card getCard() {
        return card;
    }
    
    /**
     * Update the card information display
     */
    public void updateCardInfo() {
        if (infoLabel != null) {
            String attackTypeIcon = (card.getAttackBehaviour() instanceof DirectAttack) ? "⚡" : "⚔";
            String usedStatus = card.hasAttackedThisTurn() ? "<br><font color='red'>[USED]</font>" : "";
            
            String infoText = "<html><center><b>" + card.getName() + "</b><br>" +
                             "<font size='-1'>M:" + card.getManaCost() + " A:" + card.getAttack() + 
                             " H:" + card.getHealth() + " " + attackTypeIcon + "</font>" +
                             usedStatus + "</center></html>";
            
            infoLabel.setText(infoText);
            updateAppearance();
        }
    }
}