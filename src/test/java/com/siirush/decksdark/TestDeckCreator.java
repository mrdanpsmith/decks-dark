package com.siirush.decksdark;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestDeckCreator {
    public Deck createTestDeck() {
        return new Deck(createTestCards());
    }
    
    private List<Card> createTestCards() {
        List<Card> cards = new ArrayList<>();
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Value value: Card.Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
        return cards;
    }
}
