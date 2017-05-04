package ir.boommarket.deposits

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.TestBoomApi

class StatementListTest extends Specification {
    def "With valid parameters get list of statements"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = StatementListRequest.newBuilder()
                                              .withDepositNumber(DEPOSIT_NUMBER)
                                              .withFromDate(TestBoomApi.date(2016, 4, 3))
                                              .build()

        when:
            def res = Deposits.getStatements(request, boomApi)

        then:
            res != null
            res.statements() != null
    }
    def "With invalid parameters to get list of statements, It throws exception"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = StatementListRequest.newBuilder()
                                              .withDepositNumber(depositNumber)
                                              .build()

        when:
            Deposits.getStatements(request, boomApi)

        then:
            def ex = thrown(RestApiException)
            ex.getErrorResponse().code == "032"

        where:
            depositNumber         |  _
            "124-813-33355-1"     |  _
            "124-813-3335522-1"   |  _
    }
}
