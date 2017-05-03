package com.tosanboom.deposits

import spock.lang.Ignore
import spock.lang.Specification

import static com.tosanboom.deposits.BatchTransferRequest.TransferDestination
import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.Deposit.DEPOSIT_NUMBER_WITHDRAW
import static commons.Common.TestBoomApi

class BatchTransferTest extends Specification {
    @Ignore
    def "With valid parameters batch transfer will be successful"() {
        given:
            def boomApi = TestBoomApi.withdrawTestSession()
            def request = new BatchTransferRequest(DEPOSIT_NUMBER_WITHDRAW, getDestinations())

        when:
            def res = Deposits.batchTransfer(request, boomApi)

        then:
            res.transferId() != null
    }

    private static List<TransferDestination> getDestinations() {
        return Arrays.asList(
                new TransferDestination(DEPOSIT_NUMBER , 100.0G),
                new TransferDestination("124-4-750-3", 100.0G)
        )
    }
}