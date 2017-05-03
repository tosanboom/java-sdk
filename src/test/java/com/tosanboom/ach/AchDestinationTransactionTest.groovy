package com.tosanboom.ach

import spock.lang.Specification

import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.Deposit.OWNER_NAME

/**
 * @author Mona Mohamadinia
 */
class AchDestinationTransactionTest extends Specification {
    def "Trying to build an invalid instance of AchDestinationTransaction will be prevented by throwing an exception"(){

        when:
            new AchDestinationTransaction(ibanNumber, ownerName, amount)

        then:
            thrown(IllegalArgumentException)

        where:
            ibanNumber | ownerName  | amount
              null     | OWNER_NAME | 100000
            IBAN_NUMBER|   null     | 100000
            IBAN_NUMBER| OWNER_NAME | null
                ""     | OWNER_NAME | 100000
            IBAN_NUMBER|    " "     | 100000
    }
}
