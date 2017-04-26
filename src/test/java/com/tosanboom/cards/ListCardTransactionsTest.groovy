package com.tosanboom.cards

import com.tosanboom.RestApiException
import spock.lang.Specification

import static com.tosanboom.cards.ListTransactionsRequest.forPan
import static commons.Common.Card.*
import static commons.Common.TestBoomApi
import static commons.Common.TestBoomApi.date

class ListCardTransactionsTest extends Specification {
    def "If either of parameters were null, Cards.listTransactions will throw an IllegalArgumentException"() {
        given:
        when:
            Cards.listTransactions(request, boomApi)

        then:
            thrown(IllegalArgumentException)

        where:
            request             | boomApi
            getADummyRequest()  |  null
            null                |  null
    }

    def "With valid parameters, Cards.listTransactions should generate a list of transactions"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = forPan(PAN).withPin(PIN).withCvv2(CVV).expiresIn(EXP).fromDate(date(2017, 2, 1)).build()

        when:
            def cardTransactions = Cards.listTransactions(request, boomApi)

        then:
            cardTransactions != null
    }

    def "With invalid card credentials, it should return 4xx error"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def invalidRequest = forPan("invalid")
                         .withPin("pin")
                         .withCvv2("cvv2")
                         .expiresIn("9905")
                         .fromDate(date(2017, 1, 1))
                         .build()

        when:
            Cards.listTransactions(invalidRequest, boomApi)

        then:
            RestApiException ex = thrown()
            ex.getErrorResponse().code == "037"
    }

    private static ListTransactionsRequest getADummyRequest() {
        forPan("pan")
                .withPin("pan")
                .expiresIn("9911")
                .withCvv2("cvv2")
                .fromDate(new Date())
                .build()
    }
}