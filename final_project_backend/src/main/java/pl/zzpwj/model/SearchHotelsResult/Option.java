package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Choice;
import pl.zzpwj.model.SearchHotelsResult.EnhancedChoice;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Option {
    public String label;
    public String itemMeta;
    public List<Choice> choices;
    public List<EnhancedChoice> enhancedChoices;
    public String selectedChoiceLabel;

    public Option() {
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

    public List<EnhancedChoice> getEnhancedChoices() {
        return enhancedChoices;
    }

    public void setEnhancedChoices(List<EnhancedChoice> enhancedChoices) {
        this.enhancedChoices = enhancedChoices;
    }

    public String getSelectedChoiceLabel() {
        return selectedChoiceLabel;
    }

    public void setSelectedChoiceLabel(String selectedChoiceLabel) {
        this.selectedChoiceLabel = selectedChoiceLabel;
    }
}
