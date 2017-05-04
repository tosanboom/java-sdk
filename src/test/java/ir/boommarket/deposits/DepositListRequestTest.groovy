package ir.boommarket.deposits

import spock.lang.Specification

class DepositListRequestTest extends Specification {
    def "Check validation of parameters"() {
        when:
            DepositListRequest.newBuilder()
                              .withLength(length)
                              .withOffset(offset)
                              .build()

        then:
            thrown(IllegalArgumentException)

        where:
            length  |  offset
             1      |  -1
            -1      |   0
             0      |   0
    }
}