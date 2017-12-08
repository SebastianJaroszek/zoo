package pl.sdacademy.animals.bear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearTest {
    @Test
    void BearShouldBeAliveImmediatelyAfterCreation() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result);
    }

    @Test
    void BearShouldBeAliveIfItHasEatenWithin10Days() {
        //given:
        int weight = 3;
        Bear bear = new BlackBear(weight);
        bear.eat();

        //when:
        boolean result = bear.isAlive();

        //then:
        assertTrue(result);
    }

}