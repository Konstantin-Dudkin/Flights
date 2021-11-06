package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * An abstract class that is the parent of the search filters
 */
public abstract class Filter {
    public abstract boolean filtration(Flight currentFlight);
}

/**
 * A class that represents a search filter that finds
 * flights departing up to the current point in time
 */
class DepInPast extends Filter {

    @Override
    public boolean filtration(Flight currentFlight) {

        // find the number of segments
        int numberOfSegs = currentFlight.getSegments().size();

        // run through all segments
        for (int j = 0; j < numberOfSegs; j++) {

            // find the departure date and compare it with today's date.
            // If the condition is met, then we return true
            LocalDateTime depDate = currentFlight.getSegments().get(j).getDepartureDate();
            if (depDate.isBefore(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }
}

/**
 * A class that represents a search filter that finds
 * flights with an arrival time before departure
 */
class ArrBeforeDep extends Filter {

    @Override
    public boolean filtration(Flight currentFlight) {

        // find the number of segments
        int numberOfSegs = currentFlight.getSegments().size();

        // run through all segments
        for (int j = 0; j < numberOfSegs; j++) {

            // Find the departure date and compare it with the arrival date.
            // If the condition is met, then we return true
            LocalDateTime depDate = currentFlight.getSegments().get(j).getDepartureDate();
            LocalDateTime arrDate = currentFlight.getSegments().get(j).getArrivalDate();
            if (depDate.isEqual(arrDate) || depDate.isAfter(arrDate)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * A class that represents a search filter that finds
 * flights with a total time on the ground greater than 2 hours
 */
class Ground2Hours extends Filter {

    @Override
    public boolean filtration(Flight currentFlight) {

        // find the number of segments
        int numberOfSegs = currentFlight.getSegments().size();

        // Create a variable total ground time
        Duration groundTime = Duration.ofHours(0);

        // run through all segments
        for (int j = 0; j < numberOfSegs - 1; j++) {

            // We find the total time spent on the ground.
            // If the condition is met (more than 2 hours), then we return true
            LocalDateTime arrDate = currentFlight.getSegments().get(j).getArrivalDate();
            LocalDateTime nextDepDate = currentFlight.getSegments().get(j + 1).getDepartureDate();

            groundTime = groundTime.plus(Duration.between(arrDate, nextDepDate));

            if (groundTime.compareTo(Duration.ofHours(2)) == 1) {
                return true;
            }
        }
        return false;
    }
}