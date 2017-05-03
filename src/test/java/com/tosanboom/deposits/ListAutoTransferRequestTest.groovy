package com.tosanboom.deposits

import spock.lang.Specification

class ListAutoTransferRequestTest extends Specification {
    def "Check validation of parameters"() {
        when:
        ListAutoTransferRequest.newBuilder()
                .withOffset(offset)
                .withLength(length)
                .withMinAmount(minAmount)
                .withMaxAmount(maxAmount)
                .build()

        then:
        thrown(IllegalArgumentException)

        where:
        offset |   length   |   minAmount   |   maxAmount
         -10   |   10       |    10.0G      |   12.0G
          0    |   0        |    10.0G      |   12.0G
          0    |   -8       |    10.0G      |   12.0G
          0    |   10       |    -10.0G     |   12.0G
          0    |   10       |    0.0G       |   12.0G
          0    |   10       |    10.0G      |   -12.0G
          0    |   10       |    10.0G      |   0.0G
    }
}