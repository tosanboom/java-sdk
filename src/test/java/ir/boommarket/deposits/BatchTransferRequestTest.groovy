package ir.boommarket.deposits

import spock.lang.Specification

import static java.util.Collections.singletonList

class BatchTransferRequestTest extends Specification {
    def "Check validation of parameters"() {
        when:
            new BatchTransferRequest(sourceDeposit, destinationDeposits)

        then:
            thrown(IllegalArgumentException)

        where:
            sourceDeposit | destinationDeposits
              null        | singletonList(new BatchTransferRequest.TransferDestination("545", 10.0G))
              ""          | singletonList(new BatchTransferRequest.TransferDestination("55555", 10.0G))
              "  "        | singletonList(new BatchTransferRequest.TransferDestination("55555", 10.0G))
    }
}