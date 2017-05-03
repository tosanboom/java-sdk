package com.tosanboom.ach

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.TestBoomApi.withTestSession

/**
 * @author Mona Mohamadinia
 */
class AchTransactionReportTest extends Specification {
    def "With valid parameter ach transaction report should return report of transfer"() {
        given:
            def boomApi = withTestSession();

        when:
            def reports = Ach.transactionReports(AchTransactionReportRequest.withoutFilter(), boomApi)

        then:
            reports != null
    }

    def "With invalid parameter ach transaction report should throw an exception"() {
        given:
            def boomApi = withTestSession();
            def request = AchTransactionReportRequest.newBuilder()
                .withSourceDepositIban(sourceIban)
                .withIbanNumber(destinationIban)
                .build()

        when:
            Ach.transactionReports(request, boomApi)

        then:
           RestApiException ex = thrown()
           ex.getErrorResponse().code == "071"

        where:
            sourceIban | destinationIban
            "invalid"  |  IBAN_NUMBER
            IBAN_NUMBER|    "invalid"
    }
}