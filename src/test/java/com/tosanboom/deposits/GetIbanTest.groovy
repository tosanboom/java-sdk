package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Store.APP_KEY
import static commons.Common.Store.DEVICE_ID
import static commons.Common.Store.TOKEN

class GetIbanTest extends Specification {
    def "Get iban of the valid given depositNumber"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey(APP_KEY)
                    .withBoomToken(TOKEN)
                    .withDeviceId(DEVICE_ID)
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("8942fa12-cc01-4d88-8e84-9e8feca83efe")
                    .build()

            def depositNumber = "124-813-3335585-1"

        when:
            def res = Deposits.getIban(depositNumber, boomApi)

        then:
            res.ibanNumber != null
    }

        def "Get iban of the invalid given depositNumber"() {
            given:
                def boomApi = BoomApi.newBuilder()
                        .withAppKey(APP_KEY)
                        .withBoomToken(TOKEN)
                        .withDeviceId(DEVICE_ID)
                        .setSandbox(true)
                        .withBank(Bank.ANSAR)
                        .withSession("358ac202-6725-423a-8e20-3a9684fbb6a8")
                        .build()

            when:
                Deposits.getIban(depositNumber, boomApi)

            then:
                thrown(RestApiException)

            where:
                depositNumber        | _
                "124-813-333522-1"   | _
                "124-813-33355852-1" | _
        }
}
