package com.tosanboom.cards

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

class CardsHolderTest extends Specification {
    def "With valid credentials /holder should return the given card balance"() {
        given:
           def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .build()

            def request = new CardHolderRequest("6393461031212026", "334534", "238", "9911", null)

        when:
            def res = Cards.getHolder(request, boomApi)

        then:
            res.firstName != null
            res.lastName != null
    }

    def "With invalid credential (invalid pan) /holder should throw the exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .build()

            def request = new CardHolderRequest("675467578564646", "334534", "238", "9911", null)

        when:
            Cards.getHolder(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "037"
    }

    def "With invalid credential (invalid pin) /holder should throw the exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .build()

            def request = new CardHolderRequest("6393461031212026", "11111", "238", "9911", null)

        when:
            Cards.getHolder(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == ""
    }

    def "With invalid credential (invalid cvv2) /holder should throw the exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .build()

            def request = new CardHolderRequest("6393461031212026", "334534", "111", "9911", null)

        when:
            Cards.getHolder(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == ""
    }

    def "With invalid credential (invalid expire date) /holder should throw the exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .build()

            def request = new CardHolderRequest("6393461031212026", "334534", "238", "22", null)

        when:
            Cards.getHolder(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == ""
    }
}
