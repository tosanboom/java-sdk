package com.tosanboom.banks

import com.tosanboom.Bank
import spock.lang.Specification

import static commons.Common.TestBoomApi

class BranchListTest extends Specification {
    def "with valid parameters, get branches should return a list of branches"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new BranchListRequest(bank, length, offset)

        when:
            def res = Banks.getBranches(request, boomApi)

        then:
            res != null
            res.branches() != null
            if (length != null) res.branches().size() <= length

        where:
            bank       | length      | offset
            Bank.ANSAR | null        | null
            Bank.ANSAR | 5L          | null
            Bank.ANSAR | 5L          | 1L
    }
}