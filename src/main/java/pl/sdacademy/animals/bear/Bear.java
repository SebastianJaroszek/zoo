package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import pl.sdacademy.animals.Animal;
import pl.sdacademy.animals.time.Clock;
import pl.sdacademy.animals.time.JodaClock;


public abstract class Bear implements Animal {

    private int weight;
    private DateTime lastMealTime;
    private Clock clock;

    public Bear(int weight, Clock clock) {
        this(weight);
        this.clock = clock;
    }

    public Bear(int weight) {
        this.weight = weight;
        this.clock = new JodaClock();
        this.lastMealTime = clock.getCurrentTime();
    }

    @Override
    public boolean isAlive() {
        return new Duration(lastMealTime, clock.getCurrentTime()).isShorterThan(Duration.standardDays(10));
    }

    public void eat() {
        lastMealTime = clock.getCurrentTime();
    }

    public void eat(int weight){
        eat();
        this.weight = this.weight + weight;
    }

    public void drink(double waterWeight){
        int waterWeightAfterCast = (int)waterWeight;
        this.weight = this.weight + waterWeightAfterCast;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void poop(){
        this.weight = this.weight - (int)(this.weight * 0.05);
    }

}
