package ir.boommarket.cards

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Card.*
import static commons.Common.TestBoomApi

class CardsHolderTest extends Specification {
    def "Cards.getHolder should throw exception when invoked will null parameters"() {
        when:
            Cards.getHolder(request, api)

        then:
            thrown(IllegalArgumentException)

        where:
            request          |     api
              null           |     null
              dummyRequest() |     null
    }

    def "With valid credentials /holder should return the given card balance"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new CardHolderRequest(PAN, PIN, CVV, EXP, null)

        when:
            def res = Cards.getHolder(request, boomApi)

        then:
            res.firstName() != null
            res.lastName() != null
    }

    def "With invalid credential (invalid pan) /holder should throw the exception"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new CardHolderRequest("675467578564646", "334534", "238", "9911", null)

        when:
            Cards.getHolder(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "037"
    }

    private static CardHolderRequest dummyRequest() {
        new CardHolderRequest("", "", "", "", "")
    }
}