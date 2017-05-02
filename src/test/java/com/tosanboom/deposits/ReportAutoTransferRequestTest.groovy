package com.tosanboom.deposits

import spock.lang.Specification

class ReportAutoTransferRequestTest extends Specification {
    def "Check validation of parameters"() {
        when:
            ReportAutoTransferRequest.newBuilder()
                                     .withSourceDepositNumber(sourceDeposit)
                                     .withDestinationDepositNumber(destinationDeposit)
                                     .withOffset(offset)
                                     .withLength(length)
                                     .withMinAmount(minAmount)
                                     .withMaxAmount(maxAmount)
                                     .build()

        then:
            thrown(IllegalArgumentException)

        where:
            sourceDeposit |  destinationDeposit   |   offset   |   length   |   minAmount   |   maxAmount
             "554"        |     "786565"          |   -10      |   10       |    10.0G      |   12.0G
             "58745"      |     "786565"          |   0        |   0        |    10.0G      |   12.0G
             "45646"      |     "786565"          |   0        |   -8       |    10.0G      |   12.0G
             "3356"       |     "786565"          |   0        |   10       |    -10.0G     |   12.0G
             "35636"      |     "786565"          |   0        |   10       |    0.0G       |   12.0G
             "35656"      |     "786565"          |   0        |   10       |    10.0G      |   -12.0G
             "3565"       |     "786565"          |   0        |   10       |    10.0G      |   0.0G
    }
}