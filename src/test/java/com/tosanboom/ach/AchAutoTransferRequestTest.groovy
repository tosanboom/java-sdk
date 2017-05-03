package com.tosanboom.ach

import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.Deposit.OWNER_NAME
import static commons.Common.TestBoomApi.date

/**
 * @author Mona Mohamadinia
 */
class AchAutoTransferRequestTest extends Specification {
    def "Trying to build an invalid instance of AchAutoTransferRequest will be prevented by throwing an exception"(){
        when:
            AchAutoTransferRequest.newBuilder()
                                  .withSourceDepositNumber(depositNumber)
                                  .withAmount(amount)
                                  .withOwnerName(ownerName)
                                  .withIbanNumber(ibanNumber)
                                  .withPeriods(new AchAutoTransactionPeriod(transactionPeriod) as List<AchAutoTransactionPeriod>)
                                  .build()
        then:
            thrown(IllegalArgumentException)

        where:
            depositNumber | amount |  ownerName |  ibanNumber | transactionPeriod
                null      | 100000 | OWNER_NAME | IBAN_NUMBER | date(2017, 5, 5)
            DEPOSIT_NUMBER| 100000 |     null   | IBAN_NUMBER | date(2017, 5, 5)
            DEPOSIT_NUMBER| 100000 | OWNER_NAME |     null    | date(2017, 5, 5)
            DEPOSIT_NUMBER| 100000 | OWNER_NAME | IBAN_NUMBER |      null
                 "  "     | 100000 | OWNER_NAME | IBAN_NUMBER | date(2017, 5, 5)
            DEPOSIT_NUMBER| 100000 |    "  "    | IBAN_NUMBER | date(2017, 5, 5)
            DEPOSIT_NUMBER| 100000 | OWNER_NAME |    "   "    | date(2017, 5, 5)
            DEPOSIT_NUMBER| -1000  | OWNER_NAME | IBAN_NUMBER | date(2017, 5, 5)
    }
}
