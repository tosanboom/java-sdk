package ir.boommarket.bills

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.Bill.BILL_ID
import static commons.Common.Bill.PAY_ID
import static commons.Common.Card.*
import static commons.Common.TestBoomApi

/**
 * @author Mona Mohamadinia
 */
class BillPaymentByCard extends Specification {
    def "With invalid parameters bill payment by card should throws exception"() {
        given:
           def boomApi = TestBoomApi.withTestSession()
           def request = BillPaymentByCardRequest.newBuilder()
                                                 .withPan(pan)
                                                 .withPin(pin)
                                                 .withCvv2(cvv2)
                                                 .withExpDate(expDate)
                                                 .withBillId(billId)
                                                 .withPayId(payId)
                                                 .build()

        when:
            Bills.payBillByCard(request, boomApi)

        then:
            def e = thrown(RestApiException)
             e.errorResponse.code == "071"

        where:
                pan   |   pin    |    cvv2   |  expDate  |    billId   | payId
            "invalid" |"invalid" |     CVV   |     EXP   |    BILL_ID  | PAY_ID
                PAN   |   PIN    | "invalid" |     EXP   |    BILL_ID  | PAY_ID
                PAN   |   PIN    |   CVV     | "invalid" |    BILL_ID  | PAY_ID
                PAN   |   PIN    |   CVV     |     EXP   |   "invalid" | PAY_ID
                PAN   |   PIN    |   CVV     |     EXP   |    BILL_ID  | "invalid"
                PAN   |   PIN    | "38322"   |     EXP   |    BILL_ID  | PAY_ID
                PAN   |   PIN    |   CVV     | "980822"  |    BILL_ID  | PAY_ID
    }

    def "With invalid pan bill payment by card should throws exception"() {
        given:
           def boomApi = TestBoomApi.withTestSession()
           def request = BillPaymentByCardRequest.newBuilder()
                .withPan(pan)
                .withPin(pin)
                .withCvv2(cvv2)
                .withExpDate(expDate)
                .withBillId(billId)
                .withPayId(payId)
                .build()

        when:
            Bills.payBillByCard(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "078"

        where:
                  pan        |   pin    |  cvv2    |  expDate |    billId      |  payId
        "226273811079282551" |"5759084" | "383"    | "9808"   |"2519128609051" | "48062127"
    }
}