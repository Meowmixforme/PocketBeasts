package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

/**
 * Decorator for Card to add effects.
 */
public abstract class CardEffectDecorator extends Card {
    protected Card decoratedCard;

    public CardEffectDecorator(Card card) {
        super(card.getId(), card.getName(), card.getManaCost(), card.getAttack(), card.getHealth(), card.getAttackBehaviour());
        this.decoratedCard = card;
    }

    @Override
    public int getAttack() {
        return decoratedCard.getAttack();
    }

    @Override
    public int getHealth() {
        return decoratedCard.getHealth();
    }
}