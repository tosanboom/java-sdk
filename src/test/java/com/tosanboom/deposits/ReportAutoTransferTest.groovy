package com.tosanboom.deposits

import commons.Common
import spock.lang.Specification

class ReportAutoTransferTest extends Specification {
    def "With valid parameters get reports"() {
    given:
        def boomApi = Common.TestBoomApi.withdrawTestSession()
        def request = ReportAutoTransferRequest.newBuilder()
                                               .withOffset(0)
                                               .withLength(10)
                                               .build()

    when:
        def res = Deposits.getAutoTransferReports(request, boomApi)

    then:
        res.autoTransferReports() != null
        res != null
    }
}