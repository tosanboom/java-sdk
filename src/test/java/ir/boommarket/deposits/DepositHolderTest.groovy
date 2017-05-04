package ir.boommarket.deposits

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.TestBoomApi

class DepositHolderTest extends Specification {
    def "With valid depositNumber, return holder information of the given depositNumber"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            def res = Deposits.getHolder(DEPOSIT_NUMBER, boomApi)

        then:
            res.name != null
    }

    def "With invalid depositNumber, throws RestApiException"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            Deposits.getHolder(depositNumber, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "032"

        where:
            depositNumber       | _
            "124-813-33355-1"   | _
            "124-813-3335562-1" | _
    }
}