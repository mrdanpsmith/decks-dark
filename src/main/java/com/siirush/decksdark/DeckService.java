package com.siirush.decksdark;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
    private static final Map<String, Deck> deckMap = Collections.synchronizedMap(new HashMap<>());
    
    public void create(String name, Deck deck) {
        deckMap.put(name, deck);
    }
    
    public Deck get(String name) {
        return deckMap.get(name);
    }

    public void delete(String name) {
        deckMap.remove(name);
    }
}
