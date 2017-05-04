package ir.boommarket.cards

import spock.lang.Specification

import static commons.Common.TestBoomApi.date

class ListTransactionsRequestTest extends Specification {
    def "When constructing a ListTransactionsRequest, all the mandatory parameters should be passed"() {
        when:
            ListTransactionsRequest
                    .forPan(pan)
                    .withPin(pin)
                    .withCvv2(cvv2)
                    .expiresIn(exp)
                    .fromDate(fromDate)
                    .build()

        then:
            thrown(IllegalArgumentException)

        where:
            pan   |   pin   |    cvv2   |    exp   |   fromDate
            null  |   null  |    null   |    null  |     null
            ""    |   null  |    null   |    null  |     null
            "  "  |   null  |    null   |    null  |     null
            "pan" |   null  |    null   |    null  |     null
            "pan" |   ""    |    null   |    null  |     null
            "pan" |   "  "  |    null   |    null  |     null
            "pan" |   "pin" |    null   |    null  |     null
            "pan" |   "pin" |    ""     |    null  |     null
            "pan" |   "pin" |    " "    |    null  |     null
            "pan" |   "pin" |    "cvv2" |    null  |     null
            "pan" |   "pin" |    "cvv2" |    ""    |     null
            "pan" |   "pin" |    "cvv2" |    " "   |     null
            "pan" |   "pin" |    "cvv2" |    "exp" |     null
    }

    def "If both fromDate and toDate are given, fromDate shouldn't be after toDate"() {
        given:
            def request = ListTransactionsRequest
                                   .forPan("pan")
                                   .withPin("pin")
                                   .withCvv2("cvv2")
                                   .expiresIn("exp")

        when:
            request.fromDate(date(2016, 1, 2)).toDate(date(2016, 1, 1)).build()

        then:
            IllegalArgumentException ex = thrown()
            ex.getMessage() == "fromDate can't be after toDate"
    }

    def "fromAmount and toAmount should be positive and fromAmount should be less than toAmount"() {
        given:
            def request = ListTransactionsRequest
                    .forPan("pan")
                    .withPin("pin")
                    .withCvv2("cvv2")
                    .expiresIn("exp")

        when:
            request.fromAmount(123.1G).toAmount(123.2G).build()

        then:
            thrown(IllegalArgumentException)

        where:
            fromAmount | toAmount
              -12.0G   |   null
                null   |  -12.0G
               13.0G   |   12.0G
    }

    def "Pagination parameters should be valid and in acceptable range"() {
        given:
            def request = ListTransactionsRequest
                    .forPan("pan")
                    .withPin("pin")
                    .withCvv2("cvv2")
                    .expiresIn("exp")

        when:
            request.withLength(length).withOffset(offset).build()

        then:
            thrown(IllegalArgumentException)

        where:
            length  |  offset
              0L    |   null
             -1L    |   null
             null   |   -5L
    }
}