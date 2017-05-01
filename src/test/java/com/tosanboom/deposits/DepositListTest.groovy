package com.tosanboom.deposits

import spock.lang.Specification

import static commons.Common.TestBoomApi

class DepositListTest extends Specification {
    def "Return list of deposit without filtering"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = DepositListRequest.withoutFilter()

        when:
            def res = Deposits.getDeposits(request, boomApi)

        then:
            res.deposits() != null
    }

    def "With sending valid parameters, get list of deposit"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = DepositListRequest.newBuilder()
                                            .withDepositNumbers(depositNumbers)
                                            .withDepositStatus(depositStatus)
                                            .withOffset(offset)
                                            .withLength(length)
                                            .build()

        when:
            def res = Deposits.getDeposits(request, boomApi)

        then:
            res.deposits() != null

        where:
            depositNumbers           |depositStatus       |offset |length
            getValidDepositNumbers() |null                |0      |10
            null                     |DepositStatus.OPEN  |0      |10
    }

    private static List<String> getValidDepositNumbers() {
        List<String> deposits = new ArrayList<>()
        deposits.add("124-813-3335585-1")

        return deposits
    }
}