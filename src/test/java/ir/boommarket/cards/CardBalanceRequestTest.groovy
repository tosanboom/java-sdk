package ir.boommarket.cards

import spock.lang.Specification

class CardBalanceRequestTest extends Specification {
    def "If required parameters were missing, CardBalanceRequest constructor should throw an exception"() {
        when:
            new CardBalanceRequest(pan, pin, cvv2, exp)

        then:
            thrown(IllegalArgumentException)

        where:
            pan   |   pin   |   cvv2   |   exp
            null  |   null  |   null   |   null
            ""    |   null  |   null   |   null
            "  "  |   null  |   null   |   null
            "pan" |   null  |   null   |   null
            "pan" |   ""    |   null   |   null
            "pan" |   "  "  |   null   |   null
            "pan" |   "pin" |   null   |   null
            "pan" |   "pin" |   ""     |   null
            "pan" |   "pin" |   "  "   |   null
            "pan" |   "pin" |   "cvv"  |   ""
            "pan" |   "pin" |   "cvv"  |   "  "
    }
}