package com.tosanboom.banks

import com.tosanboom.Bank
import spock.lang.Specification

class BranchListRequestTest extends Specification {
    def "Given valid parameter, BranchListRequest should work smoothly"() {
        when:
            new BranchListRequest(bank, length, offset)

        then:
            println("Nothing to assert")

        where:
            bank       | length    | offset
            Bank.ANSAR | null      | null
            Bank.ANSAR | 1L        | null
            Bank.ANSAR | 1L        | 0L
    }

    def "Missing bank parameter will make one-arg constructor to complain with an exception"() {
        when:
            new BranchListRequest(null)

        then:
            IllegalArgumentException ex = thrown()
            ex.getMessage() == "bank can't be null"
    }

    def "Given invalid parameters, three arg constructor should complain with an IllegalArgumentException"() {
        when:
            new BranchListRequest(bank, length, offset)

        then:
            thrown(IllegalArgumentException)

        where:
            bank       | length    | offset
            null       | null      | null
            Bank.ANSAR | 0L        | null
            Bank.ANSAR | -2L       | null
            Bank.ANSAR | 2L        | -1L
    }
}