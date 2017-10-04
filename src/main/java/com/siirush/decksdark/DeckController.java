package com.siirush.decksdark;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deck")
@RequiredArgsConstructor
public class DeckController {
    private final DeckService deckService;
    
    @RequestMapping(path = "{name}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse putDeck(@PathVariable("name") String name, @RequestBody String[] cardArray) {
        Deck deck = validateDeck(cardArray);
        deckService.put(name, deck);
        return new SuccessResponse();
    }
    
    @RequestMapping(path = "{name}", method = RequestMethod.GET)
    public String[] getDeck(@PathVariable("name") String name) {
        Deck deck = deckService.get(name);
        if (deck == null) {
            throw new DeckNotFoundException(String.format("Deck not found. name=%s", name));
        }
        return deckToArray(deck);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Set<String> getDeckList() {
        return deckService.getDeckList();
    }

    @RequestMapping(path = "{name}/shuffle", method = RequestMethod.POST)
    public SuccessResponse shuffleDeck(@PathVariable("name") String name) {
        deckService.shuffle(name);
        return new SuccessResponse();
    }
    
    @RequestMapping(path = "{name}", method = RequestMethod.DELETE)
    public SuccessResponse deleteDeck(@PathVariable("name") String name) {
        deckService.delete(name);
        return new SuccessResponse();
    }
    
    private String[] deckToArray(Deck deck) {
        return deck.getCards().stream().map((card) -> card.toString()).toArray(String[]::new);
    }

    private Deck validateDeck(String[] cardArray) {
        if (cardArray.length != 52) {
            throw new InvalidDeckException("Your deck does not contain 52 cards.");
        }
        LinkedHashSet<Card> cards = new LinkedHashSet<>();
        for (String cardStr: cardArray) {
            cards.add(cardOf(cardStr));
        }
        if (cards.size() != 52) {
            throw new InvalidDeckException("Your deck does not contain 52 unique cards.");
        }
        return new Deck(new ArrayList<>(cards));
    }
    
    private Card cardOf(String cardStr) {
        String[] valueAndSuit = cardStr.split("\\-", 2);
        try {
            Card.Value value = Card.Value.valueOf("CARD_" + valueAndSuit[0]);
            Card.Suit suit = Card.Suit.valueOf(valueAndSuit[1].toUpperCase());
            return new Card(suit, value);
        } catch (IllegalArgumentException e) {
            throw new InvalidDeckException("Your deck contains invalid cards.", e);
        }
    }
}
