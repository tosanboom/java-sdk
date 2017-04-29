package com.tosanboom.cards

import spock.lang.Specification

class CardListRequestTest extends Specification {
    def "Trying to build an invalid instance of CardListRequest will be prevented by throwing an exception"() {
        when:
            CardListRequest.newBuilder()
                    .withPan(pan)
                    .withDepositNumber(deposit)
                    .withOffset(offset)
                    .withLength(length)
                    .build()

        then:
            thrown(IllegalArgumentException)

        where:
            pan    | deposit   | offset    | length
            ""     | null      | null      | null
            "  "   | null      | null      | null
            "pan"  | ""        | null      | null
            "pan"  | "  "      | null      | null
            "pan"  | "dep"     | -5L       | null
            "pan"  | "dep"     | 15L       | 0L
            "pan"  | "dep"     | 15L       | -42L
    }
}