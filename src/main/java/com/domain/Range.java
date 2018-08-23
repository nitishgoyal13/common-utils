package com.domain;

/***
 Created by nitish.goyal on 23/08/18
 ***/

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * User: Nitish Goyal
 * Date: 23/08/18
 * Time: 10:58 PM
 */

public class Range<T extends Comparable<T>> implements Serializable {

    private T from;
    private T upto;

    private boolean includeLower;
    private boolean includeUpper;

    public static <T extends Comparable<T>> Range<T> of(T from, T upto, boolean includeLower, boolean includeUpper) {
        return new Range<>(from, upto).includeLower(includeLower).includeUpper(includeUpper);
    }

    public Range() {
    }

    public Range(Range<T> range) {
        if (range != null) {
            this.from = range.from;
            this.upto = range.upto;
        }
    }


    public Range(T from, T upto) {
        this.from = from;
        this.upto = upto;
    }

    public Range<T> gt(T value) {
        return from(value).includeLower(false);
    }

    public Range<T> gte(T value) {
        return from(value).includeLower(true);
    }

    public Range<T> lt(T value) {
        return upto(value).includeUpper(false);
    }

    public Range<T> lte(T value) {
        return upto(value).includeUpper(true);
    }

    public Range<T> from(final T from) {
        this.from = from;
        return this;
    }

    public Range<T> upto(final T upto) {
        this.upto = upto;
        return this;
    }

    public Range<T> includeLower(final boolean includeLower) {
        this.includeLower = includeLower;
        return this;
    }

    public Range<T> includeUpper(final boolean includeUpper) {
        this.includeUpper = includeUpper;
        return this;
    }

    public T getFrom() {
        return from;
    }

    public void setFrom(T from) {
        this.from = from;
    }

    public T getUpto() {
        return upto;
    }

    public void setUpto(T upto) {
        this.upto = upto;
    }

    public boolean isIncludeLower() {
        return includeLower;
    }

    public void setIncludeLower(boolean includeLower) {
        this.includeLower = includeLower;
    }

    public boolean isIncludeUpper() {
        return includeUpper;
    }

    public void setIncludeUpper(boolean includeUpper) {
        this.includeUpper = includeUpper;
    }

    public boolean isWithinRange(T value) {
        if (value == null) {
            return false;
        }
        int cmp = value.compareTo(from);
        if (cmp < 0) {
            return false;
        }
        cmp = value.compareTo(upto);
        return cmp <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Range range = (Range) o;

        if (includeLower != range.includeLower) {
            return false;
        }
        if (includeUpper != range.includeUpper) {
            return false;
        }
        if (from != null ? !from.equals(range.from) : range.from != null) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (upto != null ? !upto.equals(range.upto) : range.upto != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (upto != null ? upto.hashCode() : 0);
        result = 31 * result + (includeLower ? 1 : 0);
        result = 31 * result + (includeUpper ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Range{" +
               "from=" + from +
               ", upto=" + upto +
               ", includeLower=" + includeLower +
               ", includeUpper=" + includeUpper +
               '}';
    }

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isValid() {
        return from != null || upto != null;
    }
}
