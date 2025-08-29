package uk.ac.tees.cis2001.pocketbeasts.utils;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;
import uk.ac.tees.cis2001.pocketbeasts.patterns.BeastAttack;
import uk.ac.tees.cis2001.pocketbeasts.patterns.DirectAttack;

import java.io.InputStream;
import java.util.*;

/**
 * Generates cards dynamically based on available image files.
 */
public class CardGenerator {
    
    // Available images in your src/images/ folder
    private static final String[] AVAILABLE_IMAGES = {
        "Aqua_Serpent", "Ashen_Lamb", "Fire_Whelp", "Frost_Lynx",
        "Moss_Golem", "Shadow_Bat", "Stone_Rhino", "Sun_Sprite",
        "Thunder_Hawk", "Wind_Sprite"
    };
    
    /**
     * Generate a list of cards based on available images
     */
    public static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        int cardId = 1;
        
        for (String imageName : AVAILABLE_IMAGES) {
            if (imageExists(imageName)) {
                Card card = createCardFromImage(imageName, cardId++);
                cards.add(card);
            }
        }
        
        return cards;
    }
    
    /**
     * Create a balanced deck from available cards
     */
    public static List<Card> createBalancedDeck(int deckSize) {
        List<Card> allCards = generateCards();
        List<Card> deck = new ArrayList<>();
        Random random = new Random();
        
        // Add cards randomly until we reach desired deck size
        for (int i = 0; i < deckSize; i++) {
            Card template = allCards.get(random.nextInt(allCards.size()));
            // Create a copy with unique ID
            Card cardCopy = new Card(
                "C" + (i + 1),
                template.getName(),
                template.getManaCost(),
                template.getAttack(),
                template.getHealth(),
                template.getAttackBehaviour()
            );
            deck.add(cardCopy);
        }
        
        return deck;
    }
    
    private static boolean imageExists(String imageName) {
        InputStream imageStream = CardGenerator.class.getResourceAsStream("/images/" + imageName + ".png");
        return imageStream != null;
    }
    
    private static Card createCardFromImage(String imageName, int cardId) {
        String cardName = imageNameToCardName(imageName);
        CardStats stats = generateStatsForCard(imageName);
        
        return new Card(
            "C" + cardId,
            cardName,
            stats.manaCost,
            stats.attack,
            stats.health,
            stats.isDirect ? new DirectAttack() : new BeastAttack()
        );
    }
    
    /**
     * Convert image name to readable card name
     */
    private static String imageNameToCardName(String imageName) {
        // Convert "Fire_Whelp" to "Fire Whelp", etc.
        return imageName.replace("_", " ");
    }
    
    /**
     * Generate balanced stats based on the card's theme
     */
    private static CardStats generateStatsForCard(String imageName) {
        switch (imageName) {
            case "Fire_Whelp":
                return new CardStats(3, 4, 3, true); // Direct attacker, high attack
            case "Thunder_Hawk":
                return new CardStats(2, 3, 2, true); // Flying direct attacker
            case "Sun_Sprite":
                return new CardStats(2, 2, 3, true); // Light-based direct attacker
            case "Wind_Sprite":
                return new CardStats(1, 1, 2, false); // Fast, low-cost beast
            case "Aqua_Serpent":
                return new CardStats(3, 2, 5, false); // Tanky water beast
            case "Stone_Rhino":
                return new CardStats(4, 3, 6, false); // Heavy tank
            case "Moss_Golem":
                return new CardStats(3, 2, 4, false); // Nature tank
            case "Frost_Lynx":
                return new CardStats(2, 3, 2, false); // Fast ice beast
            case "Shadow_Bat":
                return new CardStats(1, 2, 1, false); // Fast, cheap beast
            case "Ashen_Lamb":
                return new CardStats(2, 1, 3, true); // Sneaky direct attacker
            default:
                return new CardStats(2, 2, 2, false); // Default balanced stats
        }
    }
    
    /**
     * Helper class for card statistics
     */
    private static class CardStats {
        final int manaCost;
        final int attack;
        final int health;
        final boolean isDirect;
        
        CardStats(int manaCost, int attack, int health, boolean isDirect) {
            this.manaCost = manaCost;
            this.attack = attack;
            this.health = health;
            this.isDirect = isDirect;
        }
    }
}