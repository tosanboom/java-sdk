package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

class DepositDetailTest extends Specification{
    def "With valid depositNumber get detail"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("445d389a-f7fc-4759-9394-04403dd2125c")
                    .build()

        when:
            def res = Deposits.getDetail("124-813-3335585-1", boomApi)

        then:
            res != null
    }

    def "With invalid depositNumber get detail"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("445d389a-f7fc-4759-9394-04403dd2125c")
                    .build()

        when:
            Deposits.getDetail(depositNumber, boomApi)

        then:
            thrown(RestApiException)

        where:
        depositNumber       | _
        "124-813-3335522-1" | _
        "124-813-3335-1"    | _
    }
}
