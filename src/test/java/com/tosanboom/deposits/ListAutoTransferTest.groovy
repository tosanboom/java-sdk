package com.tosanboom.deposits

import commons.Common
import spock.lang.Specification

class ListAutoTransferTest extends Specification {
    def "With valid parameters get reports"() {
        given:
        def boomApi = Common.TestBoomApi.withdrawTestSession()
        def request = ListAutoTransferRequest.newBuilder()
                .withOffset(0)
                .withLength(10)
                .build()

        when:
        def res = Deposits.getListAutoTransfer(request, boomApi)

        then:
        res.autoTransferDetailsList() != null
        res != null
    }
}