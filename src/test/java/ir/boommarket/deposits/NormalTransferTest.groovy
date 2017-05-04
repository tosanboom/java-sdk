package ir.boommarket.deposits

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER_WITHDRAW
import static commons.Common.TestBoomApi

class NormalTransferTest extends Specification {
    def "With valid parameters the transfer will be success"() {
        given:
            def boomApi = TestBoomApi.withdrawTestSession()
            def request = NormalTransferRequest.newBuilder()
                                               .withSourceDeposit(DEPOSIT_NUMBER_WITHDRAW)
                                               .withDestinationDeposit(destinationDeposit)
                                               .withAmount(10.0G)
                                               .build()

        when:
            def res = Deposits.normalTransfer(request, boomApi)

        then:
            res.trackingCode() != null

        where:
             destinationDeposit   | _
             "124-4-750-2"        | _
             "124-74-32651-1"     | _
             "124-813-174726-1"   | _
    }

    def "With invalid parameters the transfer will be failed, and it throws an exception"() {
        given:
            def boomApi = TestBoomApi.withdrawTestSession()

            def request = NormalTransferRequest.newBuilder()
                    .withSourceDeposit(sourceDeposit)
                    .withDestinationDeposit(destinationDeposit)
                    .withAmount(30.0G)
                    .build()

        when:
            Deposits.normalTransfer(request, boomApi)

        then:
           thrown(RestApiException)
        where:
            sourceDeposit        |destinationDeposit
            "124-813-33355-1"    |"124-4-750-2"
            "124-813-3335585-1"  |"124-74-326-1"
            "124-813-33355852-1" |"124-813-174726-1"
    }
}