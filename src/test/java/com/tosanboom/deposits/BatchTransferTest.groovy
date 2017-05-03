package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import spock.lang.Specification

class BatchTransferTest extends Specification {
    def "With valid parameters batch transfer will be successful"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("828b671a-a4c8-404c-a67e-2db6c8bf8861")
                    .build()

            def request = new BatchTransferRequest("124-813-3335585-1", getDestinations())

        when:
            def res = Deposits.batchTransfer(request, boomApi)

        then:
            res.transferId() != null
    }

    private static List<BatchTransferRequest.TransferDestination> getDestinations() {
        List<BatchTransferRequest.TransferDestination> destinations = new ArrayList<>()

        BatchTransferRequest.TransferDestination destination1 =
                new BatchTransferRequest.TransferDestination("110-70-1000000-1", new BigDecimal("100"))
        destinations.add(destination1)

        BatchTransferRequest.TransferDestination destination2 =
                new BatchTransferRequest.TransferDestination("124-4-750-3", new BigDecimal("200"))
        destinations.add(destination2)

        return destinations
    }
}