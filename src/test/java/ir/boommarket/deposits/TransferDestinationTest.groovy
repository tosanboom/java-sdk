package ir.boommarket.deposits

import spock.lang.Specification

class TransferDestinationTest extends Specification {
    def "Check validation of destinations"() {

        when:
            new BatchTransferRequest.TransferDestination(destinationDepositNumber, amount)

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