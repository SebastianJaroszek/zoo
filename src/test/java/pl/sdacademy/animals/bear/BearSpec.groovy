package pl.sdacademy.animals.bear

import spock.lang.Specification

import java.time.LocalDate

class BearSpec extends Specification {

    def "Bear should be alive immediately after creation"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

        when:
        boolean  result = bear.isAlive()

        then:
        result
    }

    def "Bear should be alive if it has eaten within 10 days"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)
        bear.eat()

        when:
        boolean result = bear.isAlive()

        then:
        result
    }

    def "Bear should not be alive if it has eaten within more than 10 days"() {
        given:
        int daysToSubtract = 10
        int weight = 3
        Bear bear = new BlackBear(weight)
        LocalDate lastMealDate = LocalDate.now().minusDays(daysToSubtract)
        bear.setLastMealDate(lastMealDate)

        when:
        boolean result = bear.isAlive()

        then:
        result

    }

}
