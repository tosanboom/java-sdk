package com.tosanboom.deposits

import com.tosanboom.RestApiException
import spock.lang.Specification

import static com.tosanboom.deposits.Deposits.getDetail
import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.TestBoomApi

class DepositDetailTest extends Specification {
    def "With valid depositNumber get detail"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            def res = getDetail(DEPOSIT_NUMBER , boomApi)

        then:
            res != null
    }

    def "With invalid depositNumber get detail"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            getDetail(depositNumber, boomApi)

        then:
            thrown(RestApiException)

        where:
        depositNumber       | _
        "124-813-3335522-1" | _
        "124-813-3335-1"    | _
    }
}
