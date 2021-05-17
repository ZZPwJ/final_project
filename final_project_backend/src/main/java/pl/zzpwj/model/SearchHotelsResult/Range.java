package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Range {
    public Min min;
    public Max max;
    public int increments;

    public Range() {
    }

    public Min getMin() {
        return min;
    }

    public void setMin(Min min) {
        this.min = min;
    }

    public Max getMax() {
        return max;
    }

    public void setMax(Max max) {
        this.max = max;
    }

    public int getIncrements() {
        return increments;
    }

    public void setIncrements(int increments) {
        this.increments = increments;
    }
}
