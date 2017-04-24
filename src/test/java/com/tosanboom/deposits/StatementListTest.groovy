package com.tosanboom.deposits

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

class StatementListTest extends Specification {
    def "With valid parameters get list of statements"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("175a55fc-7bf1-4572-b374-abcef4fb5e37")
                    .build()

            def request = StatementListRequest.newBuilder()
                                              .withDepositNumber(depositNumber)
                                              .withFromDate(fromDate)
                                              .withToDate(toDate)
                                              .withOffset(offset)
                                              .withLength(length)
                                              .withOrder(order)
                                              .withDescription(null)
                                              .withAction(action)
                                              .build()

        when:
            def res = Deposits.getStatements(request, boomApi)

        then:
            res.statements != null

        where:
            depositNumber       |fromDate               |toDate                 |offset  |length |order         |action
            "124-813-3335585-1" |null                   |null                   |0       | 10    |null          |null
            "124-813-3335585-1" |"2016-01-01T01:37:40Z" |null                   |0       |2      |null          |null
            "124-813-3335585-1" |"2016-01-01T01:37:40Z" |"2017-01-01T01:37:40Z" |0       |2      |OrderType.DESC|null
    }
    def "With invalid parameters to get list of statements, It throws exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("e10ac9f3-3f39-4420-bd2d-f5a5d9944b18")
                    .build()

            def request = StatementListRequest.newBuilder()
                    .withDepositNumber(depositNumber)
                    .withFromDate(fromDate)
                    .withToDate(toDate)
                    .withOffset(offset)
                    .withLength(length)
                    .withOrder(order)
                    .withDescription(null)
                    .withAction(action)
                    .build()

        when:
            def res = Deposits.getStatements(request, boomApi)

        then:
            RestApiException ex = thrown()

        where:
            depositNumber       |fromDate               |toDate                 |offset  |length |order |action
            "124-813-33355-1"   |null                   |null                   |0       | 10    |null  |null
            "124-813-3335585-1" |"2016-06-01T01:37:40Z" |"2016-05-01T01:37:40Z" |0       |2      |null  |null
            "124-813-3335585-1" |"2016-06-01T01:37:40Z" |"2016-05-01T01:37:40Z" |0       |2      |null  |null
            "124-813-3335522-1" |"2016-06-01T01:37:40Z" |"2016-05-01T01:37:40Z" |0       |2      |null  |null
    }
}
