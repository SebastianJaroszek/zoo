package pl.sdacademy.animals.bear.time;

import org.joda.time.DateTime;
import pl.sdacademy.animals.time.Clock;

public class TestClock implements Clock {

    private DateTime time = DateTime.now();

    @Override
    public DateTime getCurrentTime() {
        return time;
    }

    public void changeTimeByDays(int days) {
        time = time.plusDays(days);
    }
}
