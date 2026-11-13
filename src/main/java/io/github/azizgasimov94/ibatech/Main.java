package io.github.azizgasimov94.ibatech;

import io.github.azizgasimov94.ibatech.countries.CountryCounter;
import io.github.azizgasimov94.ibatech.parking.CarParkingRoof;

/** Runs both tasks on their example inputs. */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        long[] cars = {6, 2, 12, 7};
        int k = 3;
        System.out.printf("Car Parking Roof: cars=%s, k=%d -> %d%n",
                java.util.Arrays.toString(cars), k, CarParkingRoof.minRoofLength(cars, k));

        int[][] map = {
                {5, 4, 4},
                {4, 3, 4},
                {3, 2, 4},
                {2, 2, 2},
                {3, 3, 4},
                {1, 4, 4},
                {4, 1, 1}
        };
        System.out.println("Count Countries: " + CountryCounter.countCountries(map));
    }
}
