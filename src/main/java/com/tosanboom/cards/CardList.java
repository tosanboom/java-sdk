package com.tosanboom.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class CardList {
    public List<Card> cards = new ArrayList<>();

    public static class Card {
        public String pan;
        public String depositNumber;
        public String customerFirstName;
        public String customerLastName;
        public String expireDate;
        public String issueDate;
        public CardType cardType;
        public CardStatus cardStatus;
        public CardStatusCause cardStatusCause;

        @Override
        public String toString() {
            return "Card{" +
                    "pan='" + pan + '\'' +
                    ", depositNumber='" + depositNumber + '\'' +
                    ", customerFirstName='" + customerFirstName + '\'' +
                    ", customerLastName='" + customerLastName + '\'' +
                    ", expireDate='" + expireDate + '\'' +
                    ", issueDate='" + issueDate + '\'' +
                    ", cardType=" + cardType +
                    ", cardStatus=" + cardStatus +
                    ", cardStatusCause=" + cardStatusCause +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CardList{" +
                "cards=" + cards +
                '}';
    }
}