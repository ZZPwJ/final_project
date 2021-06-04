package pl.zzpwj.model.comparator;

import pl.zzpwj.model.Flight;

import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getMinPrice().compareTo(o2.getMinPrice());
    }
}
