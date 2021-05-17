package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Option;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortResults {
    public List<Option> options;
    public double distanceOptionLandmarkId;

    public SortResults() {
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public double getDistanceOptionLandmarkId() {
        return distanceOptionLandmarkId;
    }

    public void setDistanceOptionLandmarkId(double distanceOptionLandmarkId) {
        this.distanceOptionLandmarkId = distanceOptionLandmarkId;
    }
}
