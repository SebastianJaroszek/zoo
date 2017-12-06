package pl.sdacademy.animals.bear;

import pl.sdacademy.animals.Animal;

import java.time.LocalDate;


public abstract class Bear implements Animal {

    private int weight;
    private LocalDate lastMealDate;

    public Bear(int weight) {
        this.weight = weight;
        this.lastMealDate = LocalDate.now();
    }

    @Override
    public boolean isAlive() {
        LocalDate now = LocalDate.now();
        return lastMealDate.compareTo(now.minusDays(10)) >= 0;
    }

    public void eat() {
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setLastMealDate(LocalDate lastMealDate) {
        this.lastMealDate = lastMealDate;
    }
}
