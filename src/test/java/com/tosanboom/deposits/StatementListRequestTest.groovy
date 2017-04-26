package com.tosanboom.deposits

import spock.lang.Specification

import static java.util.Calendar.*

class StatementListRequestTest extends Specification {
    def "With invalid parameters, StatementListRequest's Builder should throw IllegalArgumentException"() {
     when:
        StatementListRequest.newBuilder()
                            .withLength(length)
                            .withOffset(offset)
                            .withDepositNumber(depositNumber)
                            .withFromDate(fromDate)
                            .withToDate(toDate)
                            .build()

        then:
            thrown(IllegalArgumentException)

        where:
            depositNumber |  length |   offset |  fromDate                         |   toDate
                  ""      |    1    |    0     |      null                         |   null
                   null   |    1    |    0     |      null                         |   null
                "2354254" |    0    |    0     |      null                         |   null
                "2354254" |    -1   |    0     |      null                         |   null
                "2354254" |    1    |    -2    |      null                         |   null
                "2354254" |    1    |    -2    | getDate(2016, APRIL, 2)           | getDate(2016, JANUARY, 4)
    }

    def "If fromDate and toDate were equal nothing will happen"() {
        when:
            StatementListRequest.newBuilder()
                                .withDepositNumber("65765")
                                .withFromDate(getDate(2016, AUGUST, 6))
                                .withToDate(getDate(2016, AUGUST, 6))
                                .build()

        then:
            println("Nothing to assert")
    }

    private static Date getDate(int year, int month, int day) {
        Calendar calendar = getInstance()
        calendar.set(year, month, day)

        return calendar.getTime()
    }
}