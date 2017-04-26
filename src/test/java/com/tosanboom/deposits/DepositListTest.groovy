package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import spock.lang.Specification

class DepositListTest extends Specification {
    def "Return list of deposit without filtering"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("d9c86382-94e8-42b5-b70b-a325d8010473")
                    .build()

            def request = DepositListRequest.withoutFilter()

        when:
            def res = Deposits.getDeposits(request, boomApi)

        then:
            res.deposits != null
    }

    def "With sending valid parameters, get list of deposit"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("d9c86382-94e8-42b5-b70b-a325d8010473")
                    .build()

            def request = DepositListRequest.newBuilder()
                                            .withDepositNumbers(depositNumbers)
                                            .withDepositStatus(depositStatus)
                                            .withIncludeCreditAccount(creditAccounts)
                                            .withIncludeSupportAccount(supportAccounts)
                                            .withOffset(offset)
                                            .withLength(length)
                                            .build()

        when:
            def res = Deposits.getDeposits(request, boomApi)

        then:
            res.deposits != null

        where:
            depositNumbers           |depositStatus      |creditAccounts |supportAccounts |offset |length
            getValidDepositNumbers() |null               |null           |null            |0      |10
            null                     |DepositStatus.OPEN |null           |null            |0      |10
    }

    private static List<String> getValidDepositNumbers() {
        List<String> deposits = new ArrayList<>()
        deposits.add("124-813-3335585-1")

        return deposits
    }
}
