package com.siirush.decksdark;

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
    
    @Autowired
    private TestDeckCreator testDeckCreator;
    
    @Test
    public void shouldShuffleCards() {
        Deck testDeck = testDeckCreator.createTestDeck();
        Deck compareDeck = testDeckCreator.createTestDeck();
        assertThat(testDeck, equalTo(compareDeck));
        inplaceShuffler.shuffleDeck(testDeck);
        assertThat(testDeck, not(equalTo(compareDeck)));
    }
}
