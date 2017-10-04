package com.siirush.decksdark;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Card {
    private final Suit suit;
    private final Value value;
    
    public static enum Suit {
        CLUB, DIAMOND, SPADE, HEART;
    }
    
    @Getter
    public static enum Value {
        CARD_2("2"), 
        CARD_3("3"), 
        CARD_4("4"), 
        CARD_5("5"), 
        CARD_6("6"), 
        CARD_7("7"), 
        CARD_8("8"), 
        CARD_9("9"), 
        CARD_10("10"), 
        CARD_J("J"), 
        CARD_Q("Q"), 
        CARD_K("K"), 
        CARD_A("A");
        private final String mark;
        private Value(String mark) {
            this.mark = mark;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s-%s", value.getMark(), suit.toString().toLowerCase());
    }
}
