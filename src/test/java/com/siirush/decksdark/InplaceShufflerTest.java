package com.siirush.decksdark;

import com.siirush.decksdark.Card.Suit;
import com.siirush.decksdark.Card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InplaceShufflerTest { 
    @Autowired
    private InplaceShuffler inplaceShuffler;
    
    @Test
    public void shouldShuffleCards() {
        Deck testDeck = createTestDeck();
        Deck compareDeck = createTestDeck();
        assertThat(testDeck, equalTo(compareDeck));
        inplaceShuffler.shuffleDeck(testDeck);
        assertThat(testDeck, not(equalTo(compareDeck)));
    }
    
    private Deck createTestDeck() {
        return new Deck(createTestCards());
    }
    
    private List<Card> createTestCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit: Suit.values()) {
            for (Value value: Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
        return cards;
    }
}
