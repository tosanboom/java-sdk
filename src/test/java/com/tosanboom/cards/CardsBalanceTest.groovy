package com.tosanboom.cards

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Card.*
import static commons.Common.TestBoomApi

class CardsBalanceTest extends Specification {
    def "Cards.getBalance should throw an exception if either of its parameters were null"() {
        when:
            Cards.getBalance(request, api)

        then:
            thrown(IllegalArgumentException)

        where:
            request                |      api
             null                  |      null
             dummyBalanceRequest() |      null
    }

    def "With valid credentials, Cards.getBalance should return the given card balance"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new CardBalanceRequest(PAN, PIN, CVV, EXP)

        when:
            def res = Cards.getBalance(request, boomApi)

        then:
            res.availableBalance() != null
            res.currency() != null
            res.ledgerBalance() != null
    }

    def "With invalid credentials, Cards.getBalance should return a 4xx client error"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def invalidRequest = new CardBalanceRequest("pan", "pin", "cvv2", "exp")

        when:
            Cards.getBalance(invalidRequest, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "037"
    }

    private static CardBalanceRequest dummyBalanceRequest() {
        return new CardBalanceRequest("pan", "pin", "cvv", "exp");
    }
}