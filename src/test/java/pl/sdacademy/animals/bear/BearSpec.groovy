package pl.sdacademy.animals.bear

import pl.sdacademy.animals.bear.time.TestClock
import spock.lang.Specification

class BearSpec extends Specification {

    def "Bear should be alive immediately after creation"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

        when:
        boolean result = bear.isAlive()

        then:
        result
    }

    def "Bear should be alive if it has eaten within 10 days"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

        when:
        bear.eat()

        then:
        bear.isAlive()
    }

    def "Bear should not be alive if it has eaten within more than 10 days"() {
        given:
        int weight = 3
        def clock = new TestClock()
        Bear bear = new BlackBear(weight, clock)
        bear.eat()

        when:
        clock.changeTimeByDays(10)

        then:
        bear.isAlive() == false
    }

    def "Bear should reborn after eat"() {
        given:
        int weight = 3
        TestClock clock = new TestClock()
        Bear bear = new BlackBear(weight, clock)
        bear.eat()
        clock.changeTimeByDays(10)
        assert !bear.isAlive()

        when:
        bear.eat()

        then:
        bear.isAlive()
    }

    def "Bear should gained by meal weight"() {
        given:
        int weight = 3
        int weightOfMeal = 3
        Bear bear = new BlackBear(weight)

        when:
        bear.eat(weightOfMeal)

        then:
        bear.getWeight() == weight + weightOfMeal
    }

    def "Bear should gained by 3/4 water weight"() {
        given:
        int weight = 3
        double waterWeight = 1.5
        Bear bear = new BlackBear(weight)

        when:
        bear.drink(waterWeight)

        then:
        bear.getWeight() == weight + (int) (waterWeight * 0.75)
    }

    def "Bear should lost weight by 5% after poop"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

        when:
        bear.poop()

        then:
        bear.getWeight() == weight - (int) (weight * 0.05)
    }

    def "BlackBear should be hibernating from 20 november to 15 march"() {
        given:
        int weight = 3
        def clock = new TestClock()
        Bear bear = new BlackBear(weight, clock)

        when:
        boolean result = bear.isHibernating()

        then:
        result
    }

}
