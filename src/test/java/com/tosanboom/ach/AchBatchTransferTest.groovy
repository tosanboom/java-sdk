package com.tosanboom.ach

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.*
import static commons.Common.TestBoomApi.withTestSession

/**
 * @author Mona Mohamadinia
 */
class AchBatchTransferTest extends Specification{
    def "with valid parameter ach normal transfer should return transfer info"() {
        given:
            def boomApi = withTestSession();
            def achDestinationTransaction = new AchDestinationTransaction(IBAN_NUMBER, OWNER_NAME, 10000)
            def request = new AchBatchTransferRequest(DEPOSIT_NUMBER, achDestinationTransaction as List<AchDestinationTransaction>)

        when:
            def paidInfo = Ach.batchTransfer(request, boomApi)

        then:
            paidInfo != null
    }

    def "With invalid parameter ach batch transfer should return an exception"(){
        given:
             def boomApi = withTestSession()
             def achDestinationTransaction = new AchDestinationTransaction(ibanNumber, OWNER_NAME, 10000)
             def request = new AchBatchTransferRequest(depositNumber, achDestinationTransaction as List<AchDestinationTransaction>)

        when:
            Ach.batchTransfer(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "071"

        where:
            depositNumber | ibanNumber
              "invalid"   | IBAN_NUMBER
           DEPOSIT_NUMBER |  "invalid"
    }

    def "With invalid deposit number ach batch transfer should return an exception"(){
        given:
             def boomApi = withTestSession()
             def achDestinationTransaction = new AchDestinationTransaction(IBAN_NUMBER, OWNER_NAME, 10000)
             def request = new AchBatchTransferRequest(depositNumber, achDestinationTransaction as List<AchDestinationTransaction>)

        when:
            Ach.batchTransfer(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "004"

        where:
             depositNumber | ibanNumber
            "124-4-750"   | IBAN_NUMBER
    }
}
