package com.tosanboom.loans

import spock.lang.Specification

class LoanDetailsRequestTest extends Specification {
    def "Clients shouldn't be able to create an invalid instance of LoanDetailsRequest"() {
        when:
            LoanDetailsRequest.forLoanNumber(loanNumber).withOffset(offset).withLength(length).build()

        then:
            thrown(IllegalArgumentException)

        where:
            loanNumber     |    length    | offset
               null        |     null     |   null
                ""         |     null     |   null
               "  "        |     null     |   null
               "valid"     |     0L       |   null
               "valid"     |     -5L      |   null
               "valid"     |     +5L      |   -42L
    }
}