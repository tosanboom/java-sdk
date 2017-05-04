package ir.boommarket.accounts

import spock.lang.Specification

class AccountInfoRequestTest extends Specification {
    def "With invalid parameters, AccountInfoRequest's constructor complain with an exception"() {
        when:
            AccountInfoRequest.newBuilder().withLength(length).withOffset(offset).build()

        then:
            thrown(IllegalArgumentException)

        where:
            length | offset
              0L   |  null
              -1L  |  null
              42L  |  -1L
    }
}