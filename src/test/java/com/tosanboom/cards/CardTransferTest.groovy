package com.tosanboom.cards

import com.tosanboom.Bank
import com.tosanboom.BoomApi
import com.tosanboom.RequestFailedException
import spock.lang.Specification

/**
 * @author Mona Mohamadinia
 */
class CardTransferTest extends Specification {
    def "with valid parameter/ transfer should return register receiver number "() {
        given:
            def request = new CardTransferRequest("6393461031211713", "831483", "917", "9911",
                "6393461031211721", TransferDestinationType.PAN, new BigDecimal(10000))

        when:
            def res = Cards.transfer(request, getBoomApi())

        then:
            res.switchResponseRRN() != null
            res.availableBalance() != null
            res.ledgerBalance() != null
            res.currency() != null
    }

    def "with invalid card credentials/ authentication should be failed"(){
        given:
            def request = new CardTransferRequest(pan, pin, cvv2, expDate, destination, destinationType, amount)

        when:
            Cards.transfer(request, getBoomApi())

        then:
            def e = thrown(RequestFailedException)
            e.errorResponse.code == "037"

        where:
             pan         |    pin    |  cvv2  | expDate  |     destination    |       destinationType       |      amount
          "invalid"      | "invalid" | "917"  |  "9911"  | "6393461031211721" | TransferDestinationType.PAN |  new BigDecimal(1000)
        "63934610312117" | "831483"  | "917"  | "invalid"| "6393461031211721" | TransferDestinationType.PAN |  new BigDecimal(1000)
    }

    def "with invalid parameter/ transfer should be failed"(){
        given:
            def request = new CardTransferRequest(pan, pin, cvv2, expDate, destination, destinationType, amount)

        when:
            Cards.transfer(request, getBoomApi())

        then:
            def e = thrown(RequestFailedException)
            e.errorResponse.code == "071"

        where:
               pan         |    pin   |  cvv2    |  expDate  |   destination      |         destinationType         |      amount
        "6393461031211713" | "831483" |   "917"  |  "9911"   |    "invalid"       |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" |   "917"  | "invalid" | "6393461031211721" |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" | "invalid"|  "9911"   | "6393461031211721" |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" |   "917"  |  "9911"   | "63934610312117"   |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" |   "917"  | "991111"  | "6393461031211721" |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" | "917111" |  "9911"   | "6393461031211721" |   TransferDestinationType.PAN   | new BigDecimal(1000)
        "6393461031211713" | "831483" |   "917"  | "9911"    | "6393461031211721" |  TransferDestinationType.DEPOSIT| new BigDecimal(1000)
    }

    def "with same source and destination pan/ transfer should be failed"(){
        given:
            def request = new CardTransferRequest(pan, pin, cvv2, expDate, destination, destinationType, amount)
        when:
            Cards.transfer(request, getBoomApi())

        then:
            def e = thrown(RequestFailedException)
           e.errorResponse.code == "1012"

        where:
               pan           |   pin   | cvv2  | expDate |    destination    |        destinationType      |      amount
        "6393461031211713"   |"831483" | "917" | "9911"  |"6393461031211713" | TransferDestinationType.PAN | new BigDecimal(1000)
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