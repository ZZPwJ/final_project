package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnhancedChoice {
    public String label;
    public String itemMeta;
    public List<Choice> choices;

    public EnhancedChoice() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getItemMeta() {
        return itemMeta;
    }

    public void setItemMeta(String itemMeta) {
        this.itemMeta = itemMeta;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
