package pl.sdacademy.animals.bear

import org.joda.time.DateTime
import pl.sdacademy.animals.time.Clock
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
        bear.eat()

        when:
        boolean result = bear.isAlive()

        then:
        result
    }

    def "Bear should not be alive if it has eaten within more than 10 days"() {
        given:
        int weight = 3
        def clock = new TestClock()
        Bear bear = new BlackBear(weight, clock)
        bear.eat()
        clock.changeTimeByDays(10)

        when:
        boolean result = bear.isAlive()

        then:
        result == false
    }

    class TestClock implements Clock {

        private DateTime time = DateTime.now();

        @Override
        DateTime getCurrentTime() {
            return DateTime.now()
        }

        public void changeTimeByDays(int days) {
            time = time.plusDays(days)
        }
    }

    def "Bear should be alive after eat"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

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
}
