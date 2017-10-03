package com.siirush.decksdark;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("deck")
public class DeckController {
    @ResponseBody
    @RequestMapping(path = "{name}", method = RequestMethod.PUT)
    public String putDeck(String name, @Valid Deck deck) {
        return "hi";
    }
}
