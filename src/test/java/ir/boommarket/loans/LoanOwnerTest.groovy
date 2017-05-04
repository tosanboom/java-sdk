package ir.boommarket.loans

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Loan.LOAN_NUMBER
import static commons.Common.TestBoomApi

class LoanOwnerTest extends Specification {
    def "Calling get loan owner will be prevented with invalid parameters"() {
        when:
            Loans.getLoanOwner(loanNumber, api)

        then:
            thrown(IllegalArgumentException)

        where:
            loanNumber      |      api
               null         |      null
               ""           |      null
               "  "         |      null
               "valid"      |      null
    }

    def "With valid loan number, get loan owner should return owner info"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            def res = Loans.getLoanOwner(LOAN_NUMBER, boomApi)

        then:
            res != null
    }

    def "With invalid loan number, get loan owner should throw an exception"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            Loans.getLoanOwner("124.120.7451.2", boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "024"
    }
}