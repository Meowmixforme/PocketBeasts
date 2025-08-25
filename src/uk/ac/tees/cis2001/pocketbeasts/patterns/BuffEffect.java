package uk.ac.tees.cis2001.pocketbeasts.patterns;

import uk.ac.tees.cis2001.pocketbeasts.model.Card;

/**
 * Buffs a card's attack temporarily.
 */
public class BuffEffect extends CardEffectDecorator {
    private int attackBuff;

    public BuffEffect(Card card, int attackBuff) {
        super(card);
        this.attackBuff = attackBuff;
    }

    @Override
    public int getAttack() {
        return decoratedCard.getAttack() + attackBuff;
    }
}