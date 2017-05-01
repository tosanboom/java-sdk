package com.tosanboom.bills

import com.tosanboom.RestApiException
import commons.Common
import spock.lang.Specification

import static commons.Common.Bill.BILL_ID
import static commons.Common.Bill.PAY_ID

/**
 * @author Mona Mohamadinia
 */
class BillInfoTest extends Specification{
    def "with valid parameter get bill info should return bill information"(){
        given:
            def boomApi = Common.TestBoomApi.withTestSession();
            def request = new BillInfoRequest(BILL_ID, PAY_ID)

        when:
            def billInfo = Bills.getBillInfo(request, boomApi)

        then:
            billInfo != null
    }

    def "with invalid parameter get bill info should throw an exception"(){
        given:
            def boomApi = Common.TestBoomApi.withTestSession();
            def request = new BillInfoRequest(billId, payId)

        when:
            Bills.getBillInfo(request, boomApi)

        then:
            def e = thrown(RestApiException)
             e.errorResponse.code == "071"

        where:
                 billId   |    payId
                "invalid" |    PAY_ID
                 BILL_ID  |   "invalid"
                "invalid" |   "invalid"
    }
}
