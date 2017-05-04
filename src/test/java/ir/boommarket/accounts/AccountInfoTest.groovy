package ir.boommarket.accounts

import spock.lang.Specification

import static commons.Common.TestBoomApi

class AccountInfoTest extends Specification {
    def "passing a null value as the boomApi to Accounts.getInfo will be prevented"() {
        when:
            Accounts.getInfo(null, null)

        then:
            IllegalArgumentException ex = thrown()
            ex.getMessage() == "boomApi can't be null"
    }

    def "Passing null as the request params, will result in an account info without any address"() {
        given:
            def boomApi = TestBoomApi.withTestSession()

        when:
            def res = Accounts.getInfo(null, boomApi)

        then:
            res != null
            res.addresses().isEmpty()
    }

    def "showAddress should make the service to actually list addresses"() {
        given:
            def boomApi = TestBoomApi.withTestSession()
            def request = AccountInfoRequest.newBuilder().showAddresses().build()

        when:
            def res = Accounts.getInfo(request, boomApi)

        then:
            res != null
            !res.addresses().isEmpty()
    }
}