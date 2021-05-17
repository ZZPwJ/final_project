package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Miscellaneous {
    public String pageViewBeaconUrl;
    public boolean showLegalInfoForStrikethroughPrices;
    public String legalInfoForStrikethroughPrices;

    public Miscellaneous() {
    }

    public String getPageViewBeaconUrl() {
        return pageViewBeaconUrl;
    }

    public void setPageViewBeaconUrl(String pageViewBeaconUrl) {
        this.pageViewBeaconUrl = pageViewBeaconUrl;
    }

    public boolean isShowLegalInfoForStrikethroughPrices() {
        return showLegalInfoForStrikethroughPrices;
    }

    public void setShowLegalInfoForStrikethroughPrices(boolean showLegalInfoForStrikethroughPrices) {
        this.showLegalInfoForStrikethroughPrices = showLegalInfoForStrikethroughPrices;
    }

    public String getLegalInfoForStrikethroughPrices() {
        return legalInfoForStrikethroughPrices;
    }

    public void setLegalInfoForStrikethroughPrices(String legalInfoForStrikethroughPrices) {
        this.legalInfoForStrikethroughPrices = legalInfoForStrikethroughPrices;
    }
}
