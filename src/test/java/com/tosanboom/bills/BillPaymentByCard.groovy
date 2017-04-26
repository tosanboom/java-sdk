package com.tosanboom.bills

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RestApiException
import spock.lang.Specification

/**
 * @author Mona Mohamadinia
 */
class BillPaymentByCard extends Specification {
    def "With valid credentials bill payment by card should return details of payment"() {
        given:
            def request = BillPaymentByCardRequest.newBuilder()
                                                  .withPan(pan)
                                                  .withPin(pin)
                                                  .withCvv2(cvv2)
                                                  .withExpDate(expDate)
                                                  .withBillId(billId)
                                                  .withPayId(payId)
                                                  .build();
        when:
            def res = Bills.payBillByCard(request, getBoomApi())

        then:
            res.referralNumber() != null

        where:
                   pan         |   pin    |  cvv2   | expDate |   billId       |   payId
            "6273811079282551" |"5759084" | "383"   | "9808"  |"2519128609051" | "48062127"
    }

    def "With invalid parameters bill payment by card should throws exception"() {
        given:
           def request = BillPaymentByCardRequest.newBuilder()
                                                 .withPan(pan)
                                                 .withPin(pin)
                                                 .withCvv2(cvv2)
                                                 .withExpDate(expDate)
                                                 .withBillId(billId)
                                                 .withPayId(payId)
                                                 .build()

        when:
            Bills.payBillByCard(request, getBoomApi())

        then:
            def e = thrown(RestApiException)
             e.errorResponse.code == "071"

        where:
                pan        |   pin    |    cvv2   |  expDate |    billId      |payId
            "invalid"      |"invalid" |   "383"   |  "9808"  |"2519128609051" | "48062127"
        "6273811079282551" |"5759084" | "invalid" |  "9808"  |"2519128609051" | "48062127"
        "6273811079282551" |"5759084" | "383"     | "invalid"|"2519128609051" | "48062127"
        "6273811079282551" |"5759084" | "383"     |  "9808"  |   "invalid"    | "48062127"
        "6273811079282551" |"5759084" | "383"     |  "9808"  |"2519128609051" | "invalid"
        "6273811079282551" |"5759084" | "38322"   |  "9808"  |"2519128609051" | "48062127"
        "6273811079282551" |"5759084" | "383"     | "980822" |"2519128609051" | "48062127"
    }

    def "With invalid pan bill payment by card should throws exception"() {
        given:
           def request = BillPaymentByCardRequest.newBuilder()
                .withPan(pan)
                .withPin(pin)
                .withCvv2(cvv2)
                .withExpDate(expDate)
                .withBillId(billId)
                .withPayId(payId)
                .build()

        when:
            Bills.payBillByCard(request, getBoomApi())

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "078"

        where:
                  pan        |   pin    |  cvv2    |  expDate |    billId      |  payId
        "226273811079282551" |"5759084" | "383"    | "9808"   |"2519128609051" | "48062127"
    }

    private static BoomApi getBoomApi(){
        BoomApi.newBuilder()
                .withAppKey("12374")
                .withBoomToken("08d4032deeb68a719e52d38be8f869c4")
                .withDeviceId("123456789")
                .setSandbox(true)
                .withBank(Bank.ANSAR)
                .build()
    }
}