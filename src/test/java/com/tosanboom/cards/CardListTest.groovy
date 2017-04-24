package com.tosanboom.cards

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

class CardListTest extends Specification {
    def "With valid parameters /cards should return the list of cards"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("44c18c60-b1f9-4f61-806d-ce4f681c6c9f")
                    .build()

            def request = CardListRequest.newBuilder()
                                             .withPan(pan)
                                             .withDepositNumber(depositNumber)
                                             .withCardStatus(cardStatus)
                                             .withOffset(offset)
                                             .withLength(length)
                                             .build()

        when:
            def res = Cards.getCards(request, boomApi)

        then:
            res.cards != null

        where:
            pan                 |depositNumber        |cardStatus  |offset  |length
            null                |null                 |null        |0       | 10
            "6393461031212026"  |null                 |null        |0       |2
            null                |"124-813-3335585-1"  |null        |0       |2
    }

    def "With invalid parameters /cards should throws exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("b3c8b58e-7d9e-4122-bca6-03fb85dcaed3")
                    .build()

            def request =  CardListRequest.newBuilder()
                                             .withPan(pan)
                                             .withDepositNumber(depositNumber)
                                             .withCardStatus(cardStatus)
                                             .withOffset(offset)
                                             .withLength(length).build()

        when:
            Cards.getCards(request, boomApi)

        then:
            RestApiException e = thrown()

        where:
            pan                  |depositNumber        |cardStatus  |offset  |length
            "639346103121202655"   |null                 |null        |0       |2
    }
}
