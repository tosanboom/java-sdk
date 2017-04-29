package com.tosanboom.cards

import spock.lang.Specification

/**
 * @author Mona Mohamadinia
 */
class CardTransferRequestTest extends Specification {
    def "give null parameters/ should return exception"() {
        when:
             new CardTransferRequest(pan, pin, cvv2, expDate, destination, destinationType, amount)

        then:
           thrown(IllegalArgumentException)

        where:
                     pan       |   pin    | cvv2  | expDate |     destination    |      destinationType        |     amount
                     null      | "831483" | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" |   null   | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | null  | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" |  null   | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" | "9911"  |        null        | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" | "9911"  | "6393461031211721" |           null              | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN |      null
                   " "         | "831483" | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" |   "  "   | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" |  " "  | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" | "9911"  |         " "        | TransferDestinationType.PAN | new BigDecimal(1000)
            "6393461031211713" | "831483" | "917" | "9911"  | "6393461031211721" | TransferDestinationType.PAN | new BigDecimal(0)
    }
}