package ir.boommarket.bills

import spock.lang.Specification

/**
 * @author Mona Mohamadinia
 */
class BillPaymentByCardRequestTest extends Specification {
    def "give null or invalid parameters bill payment by card should return exception"() {
        when:
           BillPaymentByCardRequest.newBuilder()
                                   .withPan(pan)
                                   .withPin(pin)
                                   .withCvv2(cvv2)
                                   .withExpDate(expDate)
                                   .withBillId(billId)
                                   .withPayId(payId)
                                   .withVerificationExpirationTimeOut(verificationExpirationTimeOut)
                                   .build()
        then:
            thrown(IllegalArgumentException)

        where:
             pan           | pin       | cvv2  | expDate | billId          | payId      | verificationExpirationTimeOut
               null        | "5759084" | "383" | "9808"  | "2519128609051" | "48062127" |  5
        "6273811079282551" |    null   | "383" | "9808"  | "2519128609051" | "48062127" |  5
        "6273811079282551" | "5759084" |  null | "9808"  | "2519128609051" | "48062127" |  5
        "6273811079282551" | "5759084" | "383" |  null   | "2519128609051" | "48062127" |  5
        "6273811079282551" | "5759084" | "383" | "9808"  |      null       | "48062127" |  5
        "6273811079282551" | "5759084" | "383" | "9808"  | "2519128609051" |    null    |  5
        "6273811079282551" | "5759084" | "383" | "9808"  | "2519128609051" | "48062127" |  -5
    }
}