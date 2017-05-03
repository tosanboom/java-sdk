package com.tosanboom.bills

import spock.lang.Specification
import static commons.Common.Bill.BILL_ID
import static commons.Common.Bill.PAY_ID

/**
 * @author Mona Mohamadinia
 */
class BillInfoRequestTest extends Specification {
    def "with null parameter get bill info should throw an exception" (){
        when:
            new BillInfoRequest(billId, payId)

        then:
           thrown(IllegalArgumentException)

        where:
            billId  |  payId
             null   | PAY_ID
            BILL_ID | null
              " "   | PAY_ID
            BILL_ID |  " "
    }
}