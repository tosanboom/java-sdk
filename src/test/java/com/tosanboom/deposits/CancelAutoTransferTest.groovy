package com.tosanboom.deposits

import com.tosanboom.RestApiException
import commons.Common
import spock.lang.Specification
import static com.tosanboom.deposits.Deposits.cancelAutoTransfer

class CancelAutoTransferTest extends Specification {
    def "With valid parameter, It cancel autoTransfer"() {
        given:
            def boomApi = Common.TestBoomApi.withTestSession()

        when:
            def res = cancelAutoTransfer("64695", boomApi)

        then:
            res.disableTransferNumber() >= 0
    }

    def "With invalid parameter, It throws an exception"() {
        given:
            def boomApi = Common.TestBoomApi.withTestSession()

        when:
            cancelAutoTransfer("646", boomApi)

        then:
            thrown(RestApiException)
    }
}