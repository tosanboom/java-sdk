package ir.boommarket.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class CardList {
    private List<Card> cards = new ArrayList<>();

    public static class Card {
        private String pan;
        private String depositNumber;
        private String customerFirstName;
        private String customerLastName;
        private String expireDate;
        private String issueDate;
        private CardType cardType;
        private CardStatus cardStatus;
        private CardStatusCause cardStatusCause;

        public String pan() {
            return pan;
        }

        public String depositNumber() {
            return depositNumber;
        }

        public String customerFirstName() {
            return customerFirstName;
        }

        public String customerLastName() {
            return customerLastName;
        }

        public String expireDate() {
            return expireDate;
        }

        public String issueDate() {
            return issueDate;
        }

        public CardType cardType() {
            return cardType;
        }

        public CardStatus cardStatus() {
            return cardStatus;
        }

        public CardStatusCause cardStatusCause() {
            return cardStatusCause;
        }

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

    public List<Card> cards() {
        return cards;
    }

    @Override
    public String toString() {
        return "CardList{" +
                "cards=" + cards +
                '}';
    }
}