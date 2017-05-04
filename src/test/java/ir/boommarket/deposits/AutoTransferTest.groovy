package ir.boommarket.deposits

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER_WITHDRAW
import static commons.Common.TestBoomApi

class AutoTransferTest extends Specification {
    def "With valid parameters, request to auto transfer"() {
        given:
            def boomApi = TestBoomApi.withdrawTestSession()
            def request = AutoTransferRequest.newBuilder()
                                             .withAmount(100.0G)
                                             .withDestinationDepositNumber("124-4-750-1")
                                             .withSourceDepositNumber(DEPOSIT_NUMBER_WITHDRAW)
                                             .withStartDate(getDate())
                                             .withTermLength((short) 3)
                                             .withTermType(Term.MONTHLY)
                                             .withTransactionCount((short) 4)
                                             .build()

        when:
            def res = Deposits.autoTransfer(request, boomApi)

        then:
            res.trackingNumber() != null
    }

    def "With invalid parameters it throws exception"() {
        given:
            def boomApi = TestBoomApi.withdrawTestSession()
            def request = AutoTransferRequest.newBuilder()
                                             .withAmount(100.0G)
                                             .withDestinationDepositNumber(destinationDeposit)
                                             .withSourceDepositNumber(sourceDeposit)
                                             .withStartDate(getDate())
                                             .withTermLength((short) 3)
                                             .withTermType(Term.MONTHLY)
                                             .withTransactionCount((short) 4)
                                             .build()

        when:
            Deposits.autoTransfer(request, boomApi)

        then:
            thrown(RestApiException)

        where:
            sourceDeposit        |   destinationDeposit
             "124-813-33355-1"   |  "124-4-750-1"
            "124-813-33355852-1" |  "124-4-750-1"
            "124-813-3335585-1"  |  "124-4-75022-1"
            "124-813-3335585-1"  |  "124-4-75-1"

    }

    private static Date getDate() {
        def calendar = Calendar.getInstance()
        calendar.setTime(aWeekFromNow())

        return calendar.getTime()
    }

    private static Date aWeekFromNow() {
        def now = new Date().getTime()

        return new Date(now + 7 * 24 * 60 * 60000L)
    }
}