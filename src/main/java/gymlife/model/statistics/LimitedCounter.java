package gymlife.model.statistics;

public class LimitedCounter extends Counter{
    private final int limit;

    public LimitedCounter(final int count, final int limit) {
        super(count);
        this.limit = limit;
    }

    public LimitedCounter(final int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void increment() {
        if (super.getCount() < limit) {
            super.increment();
        }
    }

    @Override
    public void decrement() {
        if (super.getCount() > 0) {
            super.decrement();
        }
    }
    @Override
    public void multiIncrement(final int value) {
        super.multiIncrement(value);
        if (super.getCount() > limit) {
            super.setCount(limit);
        }else if (super.getCount() < 0) {
            super.resetCount();
        }
    }
    public boolean isMax() {
        return limit == super.getCount();
    }
}
