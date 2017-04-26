package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

class NormalTransferTest extends Specification {
    def "With valid parameters the transfer will be success"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("7c0f1ab6-27ca-4413-b83e-6e87bfb316db")
                    .build()

            def request = NormalTransferRequest.newBuilder()
                                               .withSourceDeposit(sourceDeposit)
                                               .withDestinationDeposit(destinationDeposit)
                                               .withAmount(amount)
                                               .build()

        when:
            def res = Deposits.normalTransfer(request, boomApi)

        then:
            res.trackingCode != null

        where:
            sourceDeposit       |destinationDeposit   |amount
            "124-813-3335585-1" |"124-4-750-2"        |new BigDecimal("10")
            "124-813-3335585-1" |"124-74-32651-1"     |new BigDecimal("20")
            "124-813-3335585-1" |"124-813-174726-1"   |new BigDecimal("30")
    }

    def "With invalid parameters the transfer will be failed, and it throws an axception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("7c0f1ab6-27ca-4413-b83e-6e87bfb316db")
                    .build()

            def request = NormalTransferRequest.newBuilder()
                    .withSourceDeposit(sourceDeposit)
                    .withDestinationDeposit(destinationDeposit)
                    .withAmount(amount)
                    .build()

        when:
            Deposits.normalTransfer(request, boomApi)

        then:
           thrown(RestApiException)
        where:
            sourceDeposit       |destinationDeposit   |amount
            "124-813-33355-1"   |"124-4-750-2"        |new BigDecimal("10")
            "124-813-3335585-1" |"124-74-326-1"       |new BigDecimal("20")
            "124-813-33355852-1" |"124-813-174726-1"   |new BigDecimal("30")
    }
}