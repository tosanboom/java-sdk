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
                    .withSession("85462b0c-7213-4f97-82a5-acf79d88ca0a")
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
            res.statements() != null

        where:
            depositNumber       |fromDate                   |toDate |offset |length |order |action
            "124-813-3335585-1" |date(2016, 4, 3)           |null   |0      |2      |null  |null
    }
    def "With invalid parameters to get list of statements, It throws exception"() {
        given:
            def boomApi = BoomApi.newBuilder()
                    .withAppKey("12374")
                    .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                    .withDeviceId("123456789")
                    .setSandbox(true)
                    .withBank(Bank.ANSAR)
                    .withSession("a657590c-ad73-4bad-bb53-857726859bc5")
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
            Deposits.getStatements(request, boomApi)

        then:
            thrown(RestApiException)

        where:
            depositNumber       |fromDate               |toDate                 |offset  |length |order |action
            "124-813-33355-1"   |null                   |null                   |0       | 10    |null  |null
            "124-813-3335585-1" |null                   |null                   |0       |2      |null  |null
            "124-813-3335585-1" |null                   |null                   |0       |2      |null  |null
            "124-813-3335522-1" |date(2016, 04, 01)     |date(2016, 05, 01)     |0       |2      |null  |null
    }

    Date date(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        return calendar.getTime()
    }
}
