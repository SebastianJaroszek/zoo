package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
        if (isHibernating()){
            throw new BearHibernatingException();
        }
        lastMealTime = clock.getCurrentTime();
    }

    public void eat(int weight) {
        eat();
        this.weight = this.weight + weight;
    }

    public void drink(double waterWeight) {
        if (isHibernating()){
            throw new BearHibernatingException();
        }
        int waterWeightAfterCast = (int) waterWeight;
        this.weight = this.weight + waterWeightAfterCast;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void poop() {
        this.weight = this.weight - (int) (this.weight * 0.05);
    }

    public abstract boolean isHibernating();

    public boolean isHibernatingWithAllArgs(int startDayOfMonth, int startMonth, int endDayOfMonth, int endMonth) {
        DateTime currentTime = clock.getCurrentTime();
        LocalDate currentDate = currentTime.toLocalDate();
        int currentYear = currentTime.getYear();

        LocalDate startHibernatingDate = new LocalDate(currentYear, startMonth, startDayOfMonth);
        LocalDate endHibernatingDate = new LocalDate(currentYear, endMonth, endDayOfMonth);

        /*if (endHibernatingDate.isBefore(startHibernatingDate)) {
            endHibernatingDate = endHibernatingDate.plusYears(1);
        }*/

        if (endHibernatingDate.isBefore(startHibernatingDate)) {
            return currentDate.isAfter(startHibernatingDate) || currentDate.isBefore(endHibernatingDate);
        }

        /*System.out.println("start hibernating date: " + startHibernatingDate.getYear() + "-" + startHibernatingDate.getMonthOfYear());
        System.out.println("end hibernating date: " + endHibernatingDate.getYear() + "-" + endHibernatingDate.getMonthOfYear());
        System.out.println("current date: " + currentDate.getYear() + "-" + currentDate.getMonthOfYear());
        System.out.println("warunek1: " + currentDate.isAfter(startHibernatingDate));
        System.out.println("warunek2: " + currentDate.isBefore(endHibernatingDate));*/

        return currentDate.isAfter(startHibernatingDate) && currentDate.isBefore(endHibernatingDate);

        //return currentDate.isAfter(startHibernatingDate) ||
        //       currentDate.isBefore(endHibernatingDate);
    }

}
