package com.tosanboom.ach

import com.tosanboom.RestApiException
import commons.Common
import spock.lang.Specification

import static commons.Common.Deposit.*

/**
 * @author Mona Mohamadinia
 */
class AchNormalTransferTest extends Specification{
    def "with valid parameter ach normal transfer should return transfer info"(){
        given:
             def boomApi = Common.TestBoomApi.withTestSession();
             def request = AchNormalTransferRequest.newBuilder()
                                                   .withSourceDepositNumber(DEPOSIT_NUMBER)
                                                   .withIbanNumber(IBAN_NUMBER)
                                                   .withOwnerName(OWNER_NAME)
                                                   .withAmount(100000)
                                                   .build()
        when:
             def paidInfo = Ach.normalTransfer(request, boomApi)

        then:
             paidInfo != null
    }

    def "with invalid parameter ach normal transfer should return an exception"(){
        given:
             def boomApi = Common.TestBoomApi.withTestSession();
             def request = AchNormalTransferRequest.newBuilder()
                                                   .withSourceDepositNumber(depositNumber)
                                                   .withIbanNumber(ibanNumber)
                                                   .withOwnerName(ownerName)
                                                   .withAmount(amount)
                                                   .build()
        when:
            Ach.normalTransfer(request, boomApi)

        then:
            def e = thrown(RestApiException)
                e.errorResponse.code == "071"
        where:
            depositNumber | ibanNumber  | ownerName  | amount
              "invalid"   | IBAN_NUMBER | OWNER_NAME | 100000
           DEPOSIT_NUMBER |  "invalid"  | OWNER_NAME | 100000
    }

    def "with invalid deposit number ach normal transfer should return an exception"(){
        given:
        def boomApi = Common.TestBoomApi.withTestSession();
        def request = AchNormalTransferRequest.newBuilder()
                .withSourceDepositNumber(depositNumber)
                .withIbanNumber(ibanNumber)
                .withOwnerName(ownerName)
                .withAmount(amount)
                .build()
        when:
           Ach.normalTransfer(request, boomApi)

        then:
            def e = thrown(RestApiException)
            e.errorResponse.code == "004"
        where:
             depositNumber  | ibanNumber  | ownerName  | amount
              "124-4-750"   | IBAN_NUMBER | OWNER_NAME | 100000
    }
}
