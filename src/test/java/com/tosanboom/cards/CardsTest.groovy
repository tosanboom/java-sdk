package com.tosanboom.cards

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import spock.lang.Specification

import static commons.Common.Card.*
import static commons.Common.Store.*

class CardsTest extends Specification {
    def "With valid credentials /balance should return the given card balance"() {
        given:
            def boomApi = BoomApi.newBuilder()
                                 .withAppKey(APP_KEY)
                                 .withBoomToken(TOKEN)
                                 .withDeviceId(DEVICE_ID)
                                 .setSandbox(true)
                                 .withBank(Bank.ANSAR)
                                 .build()

            def request = new CardBalanceRequest(PAN, PIN, CVV, EXP)

        when:
            def res = Cards.getBalance(request, boomApi)

        then:
            res.availableBalance != null
            res.currency != null
            res.ledgerBalance != null
    }
}