package com.siirush.decksdark;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("interlace-shuffle")
@Getter(AccessLevel.PACKAGE)
public class InterlaceShuffler implements Shuffler {
    private final Integer iterations;

    public InterlaceShuffler(@Value("${interlace.iterations:10}") Integer iterations) {
        this.iterations = iterations;
    }
    
    @Override
    public void shuffleDeck(Deck deck) {
        for (int i = 0; i < iterations; i++) {
            interlace(deck);
        }
    }

    void interlace(Deck deck) {
        List<Card> cards = deck.getCards();
        int middle = cards.size() / 2;
        List<Card> firstHalf = cards.subList(0, middle);
        List<Card> secondHalf = cards.subList(middle, cards.size());
        List<Card> interlaced = new ArrayList<>();
        for (int i = 0; i < firstHalf.size(); i++) {
            interlaced.add(firstHalf.get(i));
            interlaced.add(secondHalf.get(i));
        }
        deck.setCards(interlaced);
    }
}
