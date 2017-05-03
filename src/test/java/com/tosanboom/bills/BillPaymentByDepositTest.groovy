package com.tosanboom.bills

import com.tosanboom.RestApiException
import spock.lang.Ignore
import spock.lang.Specification

import static commons.Common.Bill.BILL_ID
import static commons.Common.Bill.PAY_ID
import static commons.Common.Deposit.DEPOSIT_NUMBER
import static commons.Common.TestBoomApi

/**
 * @author Mona Mohamadinia
 */
class BillPaymentByDepositTest extends Specification {
    @Ignore
    def "With valid parameter, pay by deposit should return payment info"() {
        given:
            def boomApi = TestBoomApi.withTestSession();
            def request = BillPaymentByDepositRequest.newBuilder()
                                                     .withBillId(BILL_ID)
                                                     .withPayId(PAY_ID)
                                                     .withDepositNumber("")
                                                     .build()

        when:
            def paidInfo = Bills.payBillByDeposit(request, boomApi)

        then:
            paidInfo != null

    }

    def "With invalid parameter, pay by deposit should return an exception"() {
        given:
             def boomApi = TestBoomApi.withTestSession();
             def request = BillPaymentByDepositRequest.newBuilder()
                                                      .withBillId(billId)
                                                      .withPayId(payId)
                                                      .withDepositNumber(depositNumber)
                                                      .build()

        when:
            Bills.payBillByDeposit(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.getErrorResponse().code == "071"

        where:
                billId  |   payId   |  depositNumber
               BILL_ID  | "invalid" |  DEPOSIT_NUMBER
               "invalid"|   PAY_ID  |  DEPOSIT_NUMBER
    }

    def "With invalid deposit number, pay by deposit should return an exception"() {
        given:
             def boomApi = TestBoomApi.withTestSession();
             def request = BillPaymentByDepositRequest.newBuilder()
                                                      .withBillId(billId)
                                                      .withPayId(payId)
                                                      .withDepositNumber(depositNumber)
                                                      .build()

        when:
             Bills.payBillByDeposit(request, boomApi)

        then:
             RestApiException ex = thrown()
             ex.getErrorResponse().code == "077"

        where:
              billId  |   payId   |  depositNumber
             BILL_ID  |   PAY_ID  | "invalid"
    }
}