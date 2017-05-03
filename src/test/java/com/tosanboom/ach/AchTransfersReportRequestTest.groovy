package com.tosanboom.ach

import spock.lang.Specification

import static commons.Common.TestBoomApi.date

/**
 * @author Mona Mohamadinia
 */
class AchTransfersReportRequestTest extends Specification {
    def "Trying to build an invalid instance of AchNormalTransfer will be prevented by throwing an exception"() {
        when:
            AchTransferReportRequest.newBuilder()
                                    .withFromTransactionAmount(fromAmount)
                                    .withToTransactionAmount(toAmount)
                                    .withFromIssueDate(fromIssueDate)
                                    .withToIssueDate(toIssueDate)
                                    .withFromRegisterDate(fromRegistereDate)
                                    .withToRegisterDate(toRegisterDate)
                                    .withLength(length)
                                    .withOffset(offset)
                                    .build()
        then:
            thrown(IllegalArgumentException)

        where:
            fromAmount | toAmount | fromIssueDate   |   toIssueDate  | fromRegistereDate | toRegisterDate | length |offset
                -500   |  10000   | date(2016, 2, 1)|date(2017, 2, 1)|  date(2016, 1, 1) |date(2017, 1, 1)|   5    |  0
                10000  |   500    | date(2016, 2, 1)|date(2017, 2, 1)|  date(2016, 1, 1) |date(2017, 1, 1)|   5    |  0
                10000  |  -500    | date(2016, 2, 1)|date(2017, 2, 1)|  date(2016, 1, 1) |date(2017, 1, 1)|   5    |  0
                10000  |  20000   | date(2017, 2, 1)|date(2016, 2, 1)|  date(2016, 1, 1) |date(2017, 1, 1)|   5    |  0
                10000  |  20000   | date(2016, 2, 1)|date(2017, 2, 1)|  date(2017, 1, 1) |date(2016, 1, 1)|   5    |  0
                10000  |  20000   | date(2016, 2, 1)|date(2017, 2, 1)|  date(2016, 1, 1) |date(2017, 1, 1)|   -5   |  0
                10000  |  20000   | date(2016, 2, 1)|date(2016, 2, 1)|  date(2016, 1, 1) |date(2016, 1, 1)|   5    |  -5
    }
}
