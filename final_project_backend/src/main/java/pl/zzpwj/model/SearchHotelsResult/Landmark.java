package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Landmark {
    public String label;
    public String distance;
    public List<Object> selectedOrder;
    public List<Item> items;

    public Landmark() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Object> getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(List<Object> selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
