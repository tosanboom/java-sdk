package com.tosanboom.loans

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Loan.LOAN_NUMBER
import static commons.Common.TestBoomApi

class LoanDetailTest extends Specification {
    def "Can't call the get details method with invalid parameters"() {
        when:
            Loans.getDetails(request, api)

        then:
            thrown(IllegalArgumentException)

        where:
            request          |   api
              null           |   null
              dummyRequest() |   null
    }

    def "With valid parameters, get details will return the loan detail without loan rows"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = LoanDetailsRequest.forLoanNumber(LOAN_NUMBER).build()

        when:
            def res = Loans.getDetails(request, boomApi)

        then:
            res != null
            res.loanRows().isEmpty()
    }

    def "With has_detail=true, the returned response would contain loan rows"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = LoanDetailsRequest.forLoanNumber(LOAN_NUMBER)
                                            .showDetails().withLength(10L).withOffset(0L)
                                            .build()

        when:
            def res = Loans.getDetails(request, boomApi)

        then:
            res != null
            !res.loanRows().isEmpty()
    }

    def "with invalid loan number, there should be an error representing the invalid"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = LoanDetailsRequest.forLoanNumber("124.120.7451.2").build()

        when:
            Loans.getDetails(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "024"
    }

    private static LoanDetailsRequest dummyRequest() {
        return LoanDetailsRequest.forLoanNumber("dummy").build()
    }
}