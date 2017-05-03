package com.tosanboom.ach

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.*
import static commons.Common.TestBoomApi.date
import static commons.Common.TestBoomApi.withTestSession
import static java.util.Collections.singletonList

/**
 * @author Mona Mohamadinia
 */
class AchAutoTransferTest extends Specification {
    def "with valid parameter ach auto transfer should return transfer info"() {
        given:
             def boomApi = withTestSession();
             def request = AchAutoTransferRequest.newBuilder()
                                                 .withSourceDepositNumber(DEPOSIT_NUMBER)
                                                 .withIbanNumber(IBAN_NUMBER)
                                                 .withOwnerName(OWNER_NAME)
                                                 .withAmount(100000)
                                                 .withPeriods(singletonList(new AchAutoTransactionPeriod(date(2017, 12, 5))))
                                                 .build()

        when:
        def paidInfo = Ach.autoTransfer(request, boomApi)

        then:
            paidInfo != null
    }

    def "With invalid parameter ach auto transfer should return an exception"(){
        given:
             def boomApi = withTestSession();
             def request = AchAutoTransferRequest.newBuilder()
                .withSourceDepositNumber(depositNumber)
                .withIbanNumber(ibanNumber)
                .withOwnerName(OWNER_NAME)
                .withAmount(amount)
                .withPeriods(singletonList(new AchAutoTransactionPeriod(transactionPeriods)))
                .build()

        when:
            Ach.autoTransfer(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "071"

        where:
               depositNumber | ibanNumber  | amount | transactionPeriods
                  "invalid"  | IBAN_NUMBER | 100000 |  date(2017, 8, 8)
              DEPOSIT_NUMBER |  "invalid"  | 100000 |  date(2017, 8, 8)
              DEPOSIT_NUMBER | IBAN_NUMBER | 100000 |  date(2017, 4, 1)
    }
}
