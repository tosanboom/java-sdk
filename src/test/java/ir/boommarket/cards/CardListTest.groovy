package ir.boommarket.cards

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.TestBoomApi

class CardListTest extends Specification {
    def "Cards.getCards will throw an exception when passing a null boomApi"() {
        when:
            Cards.getCards(CardListRequest.withoutFilter(), null)

        then:
            thrown(IllegalArgumentException)
    }

    def "Passing null as the CardListRequest will equals to no filtering at all"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            def res = Cards.getCards(null, boomApi)

        then:
            res != null
    }

    def "With valid parameters /cards should return the list of cards"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
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
            res.cards() != null

        where:
            pan                 |depositNumber        |cardStatus  |offset  |length
            null                |null                 |null        |0       | 10
            "6393461031212026"  |null                 |null        |0       |2
            null                |"124-813-3335585-1"  |null        |0       |2
    }

    def "With invalid parameters /cards should throws exception"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request =  CardListRequest.newBuilder().withPan("63934610312120234").build()

        when:
            Cards.getCards(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "078"
    }
}