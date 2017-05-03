package com.tosanboom.ach

import spock.lang.Specification

import static commons.Common.Deposit.IBAN_NUMBER
import static commons.Common.Deposit.OWNER_NAME

/**
 * @author Mona Mohamadinia
 */
class AchBatchTransferRequestTest extends Specification {
    def "Trying to build an invalid instance of AchBatchTransferRequest will be prevented by throwing an exception"() {
        when:
            def achDestinationTransaction = new AchDestinationTransaction(IBAN_NUMBER, OWNER_NAME, 10000)
            new AchBatchTransferRequest(null,  achDestinationTransaction as List<AchDestinationTransaction>)

        then:
            thrown(IllegalArgumentException)

    }
}
