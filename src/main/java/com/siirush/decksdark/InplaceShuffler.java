package com.siirush.decksdark;

import java.util.Collections;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("inplace-shuffle")
@Component
public class InplaceShuffler implements Shuffler {
    @Override
    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getCards());
    }
}
