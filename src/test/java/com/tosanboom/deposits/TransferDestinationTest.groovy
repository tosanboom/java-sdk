package com.tosanboom.deposits

import spock.lang.Specification

import static com.tosanboom.deposits.BatchTransferRequest.*

class TransferDestinationTest extends Specification {
    def "Check validation of destinations"() {

        when:
            new TransferDestination(destinationDepositNumber, amount)

        then:
            thrown(IllegalArgumentException)

        where:
        destinationDepositNumber | amount
            ""                   | 10.0G
            "  "                 | 10.0G
            null                 | 10.0G
            "545"                | 0.0G
            "545"                | -16.0G
    }
}