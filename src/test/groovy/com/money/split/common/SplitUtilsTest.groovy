package com.money.split.common

import spock.lang.Specification

class SplitUtilsTest extends Specification {
    def "makeToken test"() {
        expect:
        String token = SplitUtils.makeToken(LENGTH)

        token.length() == SIZE
        println(token)

        where:
        LENGTH | SIZE
        3      | 3
        10     | 10
    }

    def "distributeMoney test"() {
        when:
        List<Long> list = SplitUtils.distributeMoney(MONEY, COUNT)

        then:
        list.size() == COUNT
        list.sum() == MONEY

        list.forEach({ it -> print(it + " ") })
        println()

        where:
        MONEY | COUNT
        100   | 3
        100   | 2
        10    | 4
    }
}
