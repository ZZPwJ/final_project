package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Item;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentPreference {
    public List<Item> items;

    public PaymentPreference() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
