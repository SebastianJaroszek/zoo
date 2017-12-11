package pl.sdacademy.animals.bear

import org.joda.time.DateTime
import pl.sdacademy.animals.bear.time.TestClock
import pl.sdacademy.animals.time.Clock
import spock.lang.Specification

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

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
        Clock clock = Mock(Clock)
        clock.getCurrentTime() >> date
        Bear bear = new BlackBear(weight, clock)

        when:
        boolean result = bear.isHibernating()

        then:
        assertThat(result).isFalse()

        where:
        date << [new DateTime(2017, 05, 01, 14, 0),
                 new DateTime(2017, 11, 19, 14, 0),
                 new DateTime(2017, 03, 16, 14, 0)]
    }

    def "PolarBear should be hibernating from 5 may to 10 october"() {
        given:
        int weight = 3
        Clock clock = Mock(Clock)
        clock.getCurrentTime() >> date
        Bear bear = new PolarBear(weight, clock)

        when:
        boolean result = bear.isHibernating()

        then:
        assertThat(result).isFalse()

        where:
        date << [new DateTime(2017, 05, 04, 14, 0),
                 new DateTime(2017, 10, 11, 14, 0),
                 new DateTime(2017, 12, 12, 14, 0)]
    }

    def "Bear should throw exception when he eat while he is hibernating"() {
        given:
        int weight = 3
        Clock clock = Mock(Clock)
        clock.getCurrentTime() >> date
        Bear bear = new BlackBear(weight, clock)

        when:
        bear.eat()

        then:
        thrown BearHibernatingException

        where:
        date << [new DateTime(2017, 11, 22, 14, 0),
                 new DateTime(2017, 02, 11, 14, 0)]
    }

    def "Bear should throw exception when he drink while he is hibernating"() {
        given:
        int weight = 3
        Clock clock = Mock(Clock)
        clock.getCurrentTime() >> date
        Bear bear = new BlackBear(weight, clock)

        when:
        bear.drink(3.2)

        then:
        thrown BearHibernatingException

        where:
        date << [new DateTime(2017, 11, 22, 14, 0),
                 new DateTime(2017, 02, 11, 14, 0)]
    }

}
