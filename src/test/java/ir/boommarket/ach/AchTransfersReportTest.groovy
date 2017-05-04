package ir.boommarket.ach

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.TestBoomApi.*

/**
 * @author Mona Mohamadinia
 */
class AchTransfersReportTest extends Specification {
    def "With valid parameter ach transfer report should return report of transfer"() {
        given:
            def boomApi = withTestSession();

        when:
             def reports = Ach.transferReports(AchTransferReportRequest.withoutFilter(), boomApi)

        then:
            reports != null
    }

    def "With invalid parameter ach transfer report should throw an exception"(){
        given:
             def boomApi = withTestSession();
             def request = AchTransferReportRequest.newBuilder()
                                              .withSourceDepositIban(sourceIban)
                                              .withDestinationIbanNumber(destinationIban)
                                              .build()

        when:
            Ach.transferReports(request, boomApi)

        then:
             RestApiException ex = thrown()
             ex.getErrorResponse().code == "071"

        where:
            sourceIban | destinationIban
            "invalid"  |  IBAN_NUMBER
            IBAN_NUMBER|    "invalid"
    }
}
