package com.tosanboom.ach

import spock.lang.Specification

import static commons.Common.Deposit.*

/**
 * @author Mona Mohamadinia
 */
class AchNormalTransferRequestTest extends Specification {
    def "Trying to build an invalid instance of AchNormalTransfer will be prevented by throwing an exception"() {
        when:
            AchNormalTransferRequest.newBuilder().withSourceDepositNumber(sourceDepositNumber)
                                                 .withIbanNumber(ibanNumber)
                                                 .withOwnerName(ownerName)
                                                 .withAmount(amount)
                                                 .build()
        then:
             thrown(IllegalArgumentException)

        where:
            sourceDepositNumber | ibanNumber  | ownerName  | amount
                   null         | IBAN_NUMBER | OWNER_NAME | 100000
              DEPOSIT_NUMBER    |     null    | OWNER_NAME | 100000
              DEPOSIT_NUMBER    | IBAN_NUMBER |    null    | 100000
              DEPOSIT_NUMBER    | IBAN_NUMBER | OWNER_NAME | null
                      " "       | IBAN_NUMBER | OWNER_NAME | 100000
              DEPOSIT_NUMBER    |     " "     | OWNER_NAME | 100000
              DEPOSIT_NUMBER    | IBAN_NUMBER |   "  "     | 100000
              DEPOSIT_NUMBER    | IBAN_NUMBER | OWNER_NAME | -10000
    }
}
