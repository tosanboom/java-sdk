package ir.boommarket

import spock.lang.Specification

/**
 * @author Ali Dehghani
 */
class BoomApiTest extends Specification {
    def "Passing an invalid base url would result in an exception"() {
        when:
            BoomApi.newBuilder().withBaseUrl(invalidUrl).build()

        then:
            IllegalArgumentException ex = thrown()
            ex.getMessage() == "Base URL is not a valid URL: " + invalidUrl

        where:
            invalidUrl                  | _
               " "                      | _
               null                     | _
               "this is invalid"        | _
    }

    def "Base urls are always ends with a trailing slash"() {
        when:
            def boomApi = BoomApi.newBuilder().withBaseUrl(url).build()

        then:
            boomApi.baseUrl().endsWith("/")

        where:
            url                                 | _
            "http://localhost:8080/code"        | _
            "http://localhost:8080/code/"       | _
            "http://app.tosanboom.com:4432/v1"  | _
            "http://app.tosanboom.com:4432/v1/" | _
    }
}