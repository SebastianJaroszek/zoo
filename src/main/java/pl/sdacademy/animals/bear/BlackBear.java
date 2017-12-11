package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import pl.sdacademy.animals.time.Clock;

public class BlackBear extends Bear {

    private final String startHibernatingDate = "asd";
    private final String endHibernatingDate = "asd";

    public BlackBear(int weight) {
        super(weight);
    }

    public BlackBear(int weight, Clock clock) {
        super(weight, clock);
    }

    @Override
    public boolean isHibernating() {
        return isHibernatingWithAllArgs(20, 11, 15, 3);
    }
}