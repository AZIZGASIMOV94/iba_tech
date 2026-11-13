package io.github.azizgasimov94.ibatech.parking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarParkingRoofTest {

    private static final long[] EXAMPLE = {6, 2, 12, 7};

    @Test
    void exampleFromTask() {
        // sorted: 2 6 7 12 -> best window of 3 is [2..7]
        assertEquals(6, CarParkingRoof.minRoofLength(EXAMPLE, 3));
    }

    @Test
    void worksForEveryK() {
        // regression: the original solution hard-coded k = 3
        assertEquals(1, CarParkingRoof.minRoofLength(EXAMPLE, 1));
        assertEquals(2, CarParkingRoof.minRoofLength(EXAMPLE, 2));
        assertEquals(11, CarParkingRoof.minRoofLength(EXAMPLE, 4));
    }

    @Test
    void doesNotModifyInput() {
        long[] cars = EXAMPLE.clone();
        CarParkingRoof.minRoofLength(cars, 2);
        assertArrayEquals(EXAMPLE, cars);
    }

    @Test
    void handlesLargePositionsWithoutOverflow() {
        long[] cars = {1, 100_000_000_000_000L};
        assertEquals(100_000_000_000_000L, CarParkingRoof.minRoofLength(cars, 2));
    }

    @Test
    void singleCar() {
        assertEquals(1, CarParkingRoof.minRoofLength(new long[]{42}, 1));
    }

    @Test
    void rejectsInvalidK() {
        assertThrows(IllegalArgumentException.class, () -> CarParkingRoof.minRoofLength(EXAMPLE, 0));
        assertThrows(IllegalArgumentException.class, () -> CarParkingRoof.minRoofLength(EXAMPLE, 5));
        assertThrows(IllegalArgumentException.class, () -> CarParkingRoof.minRoofLength(new long[0], 1));
    }

    @Test
    void rejectsNull() {
        assertThrows(NullPointerException.class, () -> CarParkingRoof.minRoofLength(null, 1));
    }
}
