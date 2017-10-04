package com.siirush.decksdark;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("interlace-shuffle")
public class InterlaceShufflerTest {
    @SpyBean
    private InterlaceShuffler interlaceShuffler;
    
    @Autowired
    private TestDeckCreator testDeckCreator;
    
    @Test
    public void shouldHavePositiveIterationNumber() {
        assertThat(interlaceShuffler.getIterations(), greaterThan(0));
    }

    @Test
    public void shouldShuffleDeck() {
        Deck deck = testDeckCreator.createTestDeck();
        Deck comparisonDeck = testDeckCreator.createTestDeck();
        assertThat(deck, equalTo(comparisonDeck));
        interlaceShuffler.shuffleDeck(deck);
        assertThat(deck, not(equalTo(comparisonDeck)));
        verify(interlaceShuffler, times(interlaceShuffler.getIterations())).interlace(eq(deck));
    }

    @Test
    public void shouldShuffleDeckUsingInterlaceStrategy() {
        Deck deck = testDeckCreator.createTestDeck();
        Deck comparisonDeck = testDeckCreator.createTestDeck();
        assertThat(deck, equalTo(comparisonDeck));
        interlaceShuffler.interlace(deck);
        interlace(comparisonDeck);
        assertThat(deck, equalTo(comparisonDeck));
    }
    
    private void interlace(Deck deck) {
        List<Card> cards = deck.getCards();
        int middleIndex = cards.size() / 2;
        List<Card> firstHalf = cards.subList(0, middleIndex);
        List<Card> secondHalf = cards.subList(middleIndex, cards.size());
        assertThat(firstHalf.size(), is(secondHalf.size()));
        List<Card> interlacedCards = new ArrayList<>();
        for (int i = 0; i < firstHalf.size(); i++) {
            interlacedCards.add(firstHalf.get(i));
            interlacedCards.add(secondHalf.get(i));
        }
        deck.setCards(interlacedCards);
    }
}
