package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Autosuggest;
import pl.zzpwj.model.SearchHotelsResult.Item;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    public Item item;
    public Autosuggest autosuggest;

    public Name() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Autosuggest getAutosuggest() {
        return autosuggest;
    }

    public void setAutosuggest(Autosuggest autosuggest) {
        this.autosuggest = autosuggest;
    }
}
