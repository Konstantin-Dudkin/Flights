package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that processing the flight list
 */
public class ListProcessing {

    public static List<Flight> listFiltration(List<Flight> flightList1, Filter... filters) {

        List<Flight> flightList = new ArrayList<>(flightList1);
        List<Flight> newFlightList = new ArrayList<>();

        for (int i = 0; i < flightList.size(); i++) {

            // highlight the current flight
            Flight currentFlight = flightList.get(i);

            boolean deleteFlight = false;

            // flight processing by a set of filters
            for (int j = 0; j < filters.length; j++) {
                if (filters[j].filtration(currentFlight)) {
                    deleteFlight = true;
                    break;
                }
            }

            if (!deleteFlight) {
                newFlightList.add(flightList.get(i));
            }

        }
        return newFlightList;
    }
}
