package ir.boommarket

import spock.lang.Specification

class QueryParamsTest extends Specification {
    def "Passing no parameters to with method, will be converted to an empty string"() {
        when:
            def queryParam = QueryParams.newQuery().toString()

        then:
            queryParam == ""
    }

    def "Key-value pairs with null values will be ignored"() {
        when:
            def queryParam = QueryParams.newQuery().with("length", null).with("offset", null).toString()

        then:
            queryParam == ""
    }

    def "Valid parameters should generate valid query params"() {
        when:
            def queryParam = QueryParams.newQuery()
            def keyValues = [keys, values].transpose()
            keyValues.each {
                queryParam.with(it.get(0), it.get(1))
            }

        then:
            queryParam.toString() == expected
        where:
            keys                           | values                             | expected
            ["length", "txt"]              | [10L, "long text"]                 | "?length=10&txt=long+text"
            ["oh_null", "very_long", "yet"]| [null, "متن فارسی", "this is > 2"]| "?very_long=%D9%85%D8%AA%D9%86+%D9%81%D8%A7%D8%B1%D8%B3%DB%8C&yet=this+is+%3E+2"
    }
}