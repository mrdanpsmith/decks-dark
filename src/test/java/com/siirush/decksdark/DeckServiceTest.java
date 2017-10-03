package com.siirush.decksdark;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckServiceTest {
    @Autowired
    private DeckService deckService;
    
    private Deck testDeck;
    
    @Before
    public void setUp() {
        deckService.deleteAll();
        testDeck = new Deck();
        testDeck.setCards(new ArrayList<>());
    }
    
    @Test
    public void shouldStoreAndRetrieveDeck() {
        deckService.put("testDeckName", testDeck);
        assertThat(deckService.get("testDeckName"), equalTo(testDeck));
    }
    
    @Test
    public void shouldDeleteDecks() {
        deckService.put("testDeckName", testDeck);
        deckService.delete("testDeckName");
        assertThat(deckService.get("testDeckName"), is(nullValue()));
    }
    
    @Test
    public void shouldRetrieveDeckList() {
        assertThat(deckService.getDeckList().size(), is(0));
        deckService.put("testDeck", testDeck);
        assertThat(deckService.getDeckList(), contains("testDeck"));
    }
}
