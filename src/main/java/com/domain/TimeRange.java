package com.domain;

import org.joda.time.DateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: Nitish Goyal
 * Date: 23/08/18
 * Time: 11:54 PM
 */

@SuppressWarnings("all")
public class TimeRange extends Range<Long> implements Comparable<TimeRange> {

    private long offset = 0;

    public TimeRange() {
    }

    public TimeRange(Long from, Long upto) {
        super(from, upto);
    }

    public static TimeRange max() {
        return of(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static TimeRange of(long from, long upto) {
        return of(from, upto, 0);
    }

    public static TimeRange of(DateTime from, DateTime upto) {
        TimeRange timeRange = of(from.getMillis(), upto.getMillis(), 0);
        timeRange.includeUpper(false);
        return timeRange;
    }

    public static TimeRange of(long from, long upto, long offset) {
        TimeRange timeRange = new TimeRange();
        timeRange.offset(offset).from(from).upto(upto).includeLower(true).includeUpper(true);
        return timeRange;
    }

    public static TimeRange of(long from, long upto, long offset, boolean includeLower, boolean includeUpper) {
        TimeRange timeRange = new TimeRange();
        timeRange.offset(offset).from(from).upto(upto).includeLower(includeLower).includeUpper(includeUpper);
        return timeRange;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public TimeRange offset(long offset) {
        this.setOffset(offset);
        return this;
    }


    @Override
    public int compareTo(TimeRange timeRange) {
        if (timeRange == null) {
            throw new IllegalArgumentException("Invalid Params");
        }
        int value = compare(this.getFrom(), timeRange.getFrom());
        if (value == 0) {
            return compare(this.getUpto(), timeRange.getUpto());
        }
        return value;
    }

    private int compare(Long value1, Long value2) {
        if (value1 == null && value2 == null) {
            return 0;
        } else if (value1 != null && value2 == null) {
            return 1;
        } else if (value1 == null) {
            return -1;
        } else {
            return value1.compareTo(value2);
        }
    }

    public Iterable<TimeRange> breakDown(long interval) {
        return () -> new TimeRangeIterator(interval);
    }

    class TimeRangeIterator implements Iterator<TimeRange> {

        private long rangeStart;
        private final long start;
        private final long end;
        private final long interval;

        public TimeRangeIterator(long interval) {
            this.start = getFrom();
            this.end = getUpto();
            this.interval = interval;
            rangeStart = start;
        }

        @Override
        public boolean hasNext() {
            return rangeStart <= end;
        }

        @Override
        public TimeRange next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            long rangeEnd = Math.min(rangeStart + interval - 1, end);

            TimeRange range = TimeRange.of(rangeStart, rangeEnd);
            rangeStart = rangeEnd + 1;
            return range;
        }
    }
}