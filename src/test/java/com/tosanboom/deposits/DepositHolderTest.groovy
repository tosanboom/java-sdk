package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Store.APP_KEY
import static commons.Common.Store.DEVICE_ID
import static commons.Common.Store.TOKEN

class DepositHolderTest extends Specification {
    def "With valid depositNumber, return holder information of the given depositNumber"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey(APP_KEY)
                    .withBoomToken(TOKEN)
                    .withDeviceId(DEVICE_ID)
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("775eddcb-4673-4e8d-9629-12862d6bdac2")
                    .build()

        when:
            def res = Deposits.getHolder("124-813-3335585-1", boomApi)

        then:
            res.name != null
    }

    def "With invalid depositNumber, throws RestApiException"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey(APP_KEY)
                    .withBoomToken(TOKEN)
                    .withDeviceId(DEVICE_ID)
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("775eddcb-4673-4e8d-9629-12862d6bdac2")
                    .build()

        when:
            Deposits.getHolder(depositNumber, boomApi)

        then:
            RestApiException ex = thrown()
            ex.errorResponse.code == "032"

        where:
            depositNumber       | _
            "124-813-33355-1"   | _
            "124-813-3335562-1" | _
    }
}