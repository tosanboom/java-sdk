package com.tosanboom.deposits

import spock.lang.Specification

class NormalTransferRequestTest extends Specification {
    def "Check validation of parameters"() {
        when:
            NormalTransferRequest.newBuilder()
                                           .withSourceDeposit(sourceDeposit)
                                           .withDestinationDeposit(destinationDeposit)
                                           .withAmount(amount)
                                           .build()

        then:
            thrown(IllegalArgumentException)

        where:
            sourceDeposit    |  destinationDeposit  |  amount
            null             |  "776"               | new BigDecimal("10")
              ""             |  "776"               | new BigDecimal("10")
            "76567"          |  null                | new BigDecimal("10")
            "856"            |  ""                  | new BigDecimal("10")
            "675"            |  "776"               | new BigDecimal("-10")
    }
}