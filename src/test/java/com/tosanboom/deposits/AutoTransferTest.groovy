package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

import static commons.Common.Store.APP_KEY
import static commons.Common.Store.DEVICE_ID
import static commons.Common.Store.TOKEN

class AutoTransferTest extends Specification {
    def "With valid parameters, request to auto transfer"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey(APP_KEY)
                    .withBoomToken(TOKEN)
                    .withDeviceId(DEVICE_ID)
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("4f6d4373-176e-4357-8fd7-53ea384f36e7")
                    .build()

            def request = AutoTransferRequest.newBuilder()
                                             .withAmount(new BigDecimal("100"))
                                             .withDestinationDepositNumber("124-4-750-1")
                                             .withSourceDepositNumber("124-813-3335585-1")
                                             .withStartDate(getDate())
                                             .withTermLength((short) 3)
                                             .withTermType(Term.MONTHLY)
                                             .withTransactionCount((short) 4)
                                             .build()

        when:
            def res = Deposits.autoTransfer(request, boomApi)

        then:
            res.trackingNumber() != null
    }

    private static Date getDate() {
        Calendar calendar = Calendar.getInstance()
        calendar.set(2017, Calendar.MAY, 1)
        return calendar.getTime()
    }

    def "With invalid parameters it throws exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey(APP_KEY)
                    .withBoomToken(TOKEN)
                    .withDeviceId(DEVICE_ID)
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("5ff4170e-b2ca-48a7-8f2a-2bf38ac5676c")
                    .build()

            def request = AutoTransferRequest.newBuilder()
                    .withAmount(new BigDecimal("100"))
                    .withDestinationDepositNumber(destinationDeposit)
                    .withSourceDepositNumber(sourceDeposit)
                    .withStartDate(getDate())
                    .withTermLength((short) 3)
                    .withTermType(Term.MONTHLY)
                    .withTransactionCount((short) 4)
                    .build()

        when:
            Deposits.autoTransfer(request, boomApi)

        then:
            thrown(RestApiException)

        where:
            sourceDeposit        |   destinationDeposit
             "124-813-33355-1"   |  "124-4-750-1"
            "124-813-33355852-1" |  "124-4-750-1"
            "124-813-3335585-1"  |  "124-4-75022-1"
            "124-813-3335585-1"  |  "124-4-75-1"

    }
}