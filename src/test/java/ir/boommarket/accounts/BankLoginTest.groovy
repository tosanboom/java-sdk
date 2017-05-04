package ir.boommarket.accounts

import ir.boommarket.RestApiException
import spock.lang.Specification

import static commons.Common.NetBank.PASSWORD
import static commons.Common.NetBank.USERNAME
import static commons.Common.TestBoomApi

class BankLoginTest extends Specification {
    def "Given null parameters, bank login will throw an exception"() {
        when:
            Accounts.bankLogin(request, api)

        then:
            thrown(IllegalArgumentException)

        where:
            request         |   api
              null          |   null
             dummyRequest() |   null
    }

    def "With valid credentials, bank login should return a session id successfully"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new BankLoginRequest(USERNAME, PASSWORD)

        when:
            def res = Accounts.bankLogin(request, boomApi)

        then:
            res != null
            res.sessionId() != null
    }

    def "With invalid credentials, bank login should fail with a 4xx error"() {
        given:
            def boomApi = TestBoomApi.forCardService()
            def request = new BankLoginRequest("invalid", "invalid")

        when:
            Accounts.bankLogin(request, boomApi)

        then:
            RestApiException ex = thrown()
            ex.getErrorResponse().code == "037"
    }

    private static BankLoginRequest dummyRequest() {
        return new BankLoginRequest("user", "pass");
    }
}