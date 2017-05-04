package ir.boommarket.bills

import spock.lang.Specification

/**
 * @author Mona Mohamadinia
 */
class BillPaymentByDepositRequestTest extends Specification{
    def "with null parameters, BillPaymentByDepositRequest's constructor should return exception"(){
        when:
            BillPaymentByDepositRequest.newBuilder()
                    .withBillId(billId)
                    .withPayId(payId)
                    .withDepositNumber(depositNumber)
                    .withVerificationExpirationTimeOut(verificationExpirationTimeOut)
                    .build()

        then:
            thrown(IllegalArgumentException)

        where:
                billId   |   payId    |  depositNumber  | verificationExpirationTimeOut
                 null    | "48062127" | "0103342702000" |             5
            "25191209051"|   null     | "0103342702000" |             5
            "25191209051"| "48062127" |      null       |             5
            "25191209051"| "48062127" | "0103342702000" |            -5
                 " "     | "48062127" | "0103342702000" |             5
            "25191209051"|     " "    | "0103342702000" |             5
            "25191209051"| "48062127" |       " "       |             5
    }
}
