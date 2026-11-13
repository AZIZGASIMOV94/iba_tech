package io.github.azizgasimov94.ibatech.parking;

import java.util.Arrays;
import java.util.Objects;

/**
 * <b>Car Parking Roof.</b>
 *
 * <p>Cars are parked at the given positions on a straight line (one car per
 * position). Find the length of the shortest roof that covers at least
 * {@code k} cars. A roof from position {@code a} to {@code b} has length
 * {@code b - a + 1}.</p>
 *
 * <p><b>Approach:</b> after sorting, the best roof always covers {@code k}
 * consecutive cars, so slide a window of size {@code k} over the sorted
 * positions and take the smallest span.</p>
 *
 * <p><b>Complexity:</b> O(n log n) time for the sort, O(n) extra space
 * (the input array is copied, not modified).</p>
 */
public final class CarParkingRoof {

    private CarParkingRoof() {
    }

    /**
     * @param carPositions positions of the parked cars, in any order
     * @param k            minimum number of cars the roof must cover, {@code 1 <= k <= carPositions.length}
     * @return the minimum roof length
     * @throws IllegalArgumentException if {@code k} is out of range
     */
    public static long minRoofLength(long[] carPositions, int k) {
        Objects.requireNonNull(carPositions, "carPositions must not be null");
        if (k < 1 || k > carPositions.length) {
            throw new IllegalArgumentException(
                    "k must be between 1 and " + carPositions.length + ", but was " + k);
        }

        long[] sorted = carPositions.clone();
        Arrays.sort(sorted);

        long best = Long.MAX_VALUE;
        for (int start = 0; start + k - 1 < sorted.length; start++) {
            long span = sorted[start + k - 1] - sorted[start] + 1;
            best = Math.min(best, span);
        }
        return best;
    }
}
