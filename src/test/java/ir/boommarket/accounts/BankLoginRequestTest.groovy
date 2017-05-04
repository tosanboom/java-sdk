package ir.boommarket.accounts

import spock.lang.Specification

class BankLoginRequestTest extends Specification {
    def "If either of parameters were missing or invalid, BankLoginRequest will throw an exception"() {
        when:
            new BankLoginRequest(username, password)

        then:
            thrown(IllegalArgumentException)

        where:
            username    |    password
              null      |      null
               ""       |      null
              "  "      |      null
             "user"     |       ""
             "user"     |      "  "
    }
}