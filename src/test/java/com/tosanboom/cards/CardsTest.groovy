package com.tosanboom.cards

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import spock.lang.Specification

class CardsTest extends Specification {
    def "With valid credentials /balance should return the given card balance"() {
        given:
            def boomApi = BoomApi.newBuilder()
                                 .withAppKey("12374")
                                 .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                                 .withDeviceId("123456789")
                                 .setSandbox(true)
                                 .withBank(Bank.ANSAR)
                                 .build()

            def request = new CardBalanceRequest("6393461031212026", "334534", "238", "9911")

        when:
            def res = Cards.getBalance(request, boomApi)

        then:
            res.availableBalance != null
            res.currency != null
            res.ledgerBalance != null
    }
}