package ir.boommarket

import spock.lang.Specification
import spock.lang.Unroll

class AssertsTest extends Specification {
    @Unroll
    def "Asserts.notBlank would do nothing for non-empty values like '#value'"(String value) {
        given:
            def dummyMessage = ""

        when:
            Asserts.notBlank(value, dummyMessage)

        then:
            println("I've got nothing to assert")

        where:
           value  | _
            "a"   | _
            "ab"  | _
            "acd" | _
    }

    def "Asserts.notBlank would raise IllegalArgumentException for blank values"() {
        given:
            def message = "Value can't be blank"

        when:
            Asserts.notBlank(value, message)

        then:
            IllegalArgumentException ex = thrown()
            ex.getMessage() == message

        where:
            value  | _
             null  | _
              ""   | _
             "  "  | _
    }

    def "Asserts.notNull would do nothing for any non-null input"() {
        given:
            def dummyMessage = ""
        when:
            Asserts.notNull(value, dummyMessage)
        then:
            println("I've got nothing to assert")
        where:
            value        | _
            "some value" | _
    }

    def "Asserts.notNull would raise an IllegalArgumentException for null values"() {
        given:
            def message = "The given parameter can't be null"

        when:
            Asserts.notNull(null, message)

        then:
            IllegalArgumentException ex = thrown()
            ex.message == message
    }
}