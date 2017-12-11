package pl.sdacademy.animals.bear;

import pl.sdacademy.animals.time.Clock;

public class PolarBear extends Bear {
    public PolarBear(int weight) {
        super(weight);
    }

    public PolarBear(int weight, Clock clock) {
        super(weight, clock);
    }

    @Override
    public boolean isHibernating() {
        return isHibernatingWithAllArgs(5, 5, 10, 10);
    }
}
