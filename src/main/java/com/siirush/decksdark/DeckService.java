package com.siirush.decksdark;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeckService {
    private final Shuffler shuffler;
    private final Map<String, Deck> deckMap = Collections.synchronizedMap(new HashMap<>());
    
    public void put(String name, Deck deck) {
        deckMap.put(name, deck);
    }
    
    public Deck get(String name) {
        return deckMap.get(name);
    }

    public void delete(String name) {
        deckMap.remove(name);
    }

    public Set<String> getDeckList() {
        return deckMap.keySet();
    }
    
    public void deleteAll() {
        deckMap.clear();
    }
    
    public void shuffle(String name) {
        Deck toShuffle = deckMap.get(name);
        if (toShuffle == null) {
            throw new DeckNotFoundException(String.format("Deck not found. name=%s", name));
        }
        shuffler.shuffleDeck(toShuffle);
    }
}