package com.tosanboom.deposits

import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.TestBoomApi

class GetIbanTest extends Specification {
    def "Get iban of the valid given depositNumber"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def depositNumber = DEPOSIT_NUMBER;

        when:
            def res = Deposits.getIban(depositNumber, boomApi)

        then:
            res.ibanNumber() != null
            !res.ibanNumber().trim().isEmpty()
    }

    def "Get iban of the invalid given depositNumber"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            Deposits.getIban(depositNumber, boomApi)

        then:
            RestApiException ex = thrown()
            ex.getErrorResponse().code == "032"

        where:
            depositNumber        | _
            "124-813-333522-1"   | _
            "124-813-33355852-1" | _
    }
}