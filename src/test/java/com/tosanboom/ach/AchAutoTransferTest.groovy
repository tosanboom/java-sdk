package com.tosanboom.ach

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.Deposit.OWNER_NAME
import static commons.Common.TestBoomApi.date
import static commons.Common.TestBoomApi.withTestSession

/**
 * @author Mona Mohamadinia
 */
class AchAutoTransferTest extends Specification{
    def "with valid parameter ach auto transfer should return transfer info"() {
        given:
             def boomApi = withTestSession();
             def request = AchAutoTransferRequest.newBuilder()
                                                 .withSourceDepositNumber(DEPOSIT_NUMBER)
                                                 .withIbanNumebr(IBAN_NUMBER)
                                                 .withOwnerName(OWNER_NAME)
                                                 .withAmount(100000)
                                                 .withPeriods(new AchAutoTransactionPeriod(date(2017, 12, 5)) as List<AchAutoTransactionPeriod>)
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
                .withIbanNumebr(ibanNumber)
                .withOwnerName(OWNER_NAME)
                .withAmount(amount)
                .withPeriods(new AchAutoTransactionPeriod(transactionPeriods) as List<AchAutoTransactionPeriod>)
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
              DEPOSIT_NUMBER | IBAN_NUMBER |  1000  |  date(2017, 8, 8)
              DEPOSIT_NUMBER | IBAN_NUMBER | 100000 |  date(2017, 4, 1)
    }
}
