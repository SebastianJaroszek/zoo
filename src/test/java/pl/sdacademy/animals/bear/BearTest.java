package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.animals.bear.time.TestClock;
import pl.sdacademy.animals.time.Clock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BearTest {
    @Test
    void bearShouldBeAliveImmediatelyAfterCreation() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result);
    }

    @Test
    void bearShouldBeAliveIfItHasEatenWithin10Days() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        bear.eat();

        //then:
        assertTrue(bear.isAlive());
    }

    @Test
    void bearShouldNotBeAliveIfItHasEatenWithinMoreThan10Days() {
        //given:
        int weight = 3;
        TestClock clock = new TestClock();
        Bear bear = new BlackBear(weight, clock);
        bear.eat();

        //when:
        clock.changeTimeByDays(10);

        //then:
        assertTrue(bear.isAlive() == false);
    }

    @Test
    void bearShouldRebornAfterEat() {
        //given:
        int weight = 3;
        TestClock clock = new TestClock();
        Bear bear = new BlackBear(weight, clock);
        bear.eat();
        clock.changeTimeByDays(10);
        assert !bear.isAlive();

        //when:
        bear.eat();

        //then:
        assertTrue(bear.isAlive());
    }

    @Test
    void bearShouldGainedByMealWeight() {
        //given:
        int weight = 3;
        int weightOfMeal = 3;
        Bear bear = new BlackBear(weight);

        //when:
        bear.eat(weightOfMeal);

        //then:
        assertTrue(bear.getWeight() == weight + weightOfMeal);
    }

    @Test
    void bearShouldGainedBy3of4WaterWeight() {
        //given:
        int weight = 3;
        double waterWeight = 1.5;
        Bear bear = new BlackBear(weight);

        //when:
        bear.drink(waterWeight);

        //then:
        assertTrue(bear.getWeight() == weight + (int) (waterWeight * 0.75));
    }

    @Test
    void bearShouldLostWeightBy5PercentAfterPoop() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        bear.poop();

        //then:
        assertTrue(bear.getWeight() == weight - (int) (weight * 0.05));
    }

    /*@ParameterizedTest
    @ValueSource(months = {5, 11, 3})
    @ValueSource(days = {1, 19, 16})
    void blackBearShouldBeHibernatingFrom20NovemberTo15March(int month, int day) {
        //given:
        int weight = 3;
        TestClock clock = new TestClock();
        clock.getCurrentTime() >> date
        Bear bear = new BlackBear(weight, clock);

        when:
        boolean result = bear.isHibernating()

        then:
        assertThat(result).isFalse()

        where:
        date << [new DateTime(2017, 05, 01, 14, 0),
                new DateTime(2017, 11, 19, 14, 0),
                new DateTime(2017, 03, 16, 14, 0)]
    }*/

}