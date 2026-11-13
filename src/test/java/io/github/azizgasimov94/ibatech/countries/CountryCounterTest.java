package io.github.azizgasimov94.ibatech.countries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryCounterTest {

    @Test
    void exampleFromTask() {
        int[][] map = {
                {5, 4, 4},
                {4, 3, 4},
                {3, 2, 4},
                {2, 2, 2},
                {3, 3, 4},
                {1, 4, 4},
                {4, 1, 1}
        };
        // regression: the original solution ignored colours and returned 1
        assertEquals(11, CountryCounter.countCountries(map));
    }

    @Test
    void separateCountriesWithSameShapeAreCountedSeparately() {
        // regression: the original solution de-duplicated countries by shape
        int[][] map = {
                {1, 2, 1},
                {2, 2, 2},
                {1, 2, 1}
        };
        assertEquals(5, CountryCounter.countCountries(map));
    }

    @Test
    void diagonalCellsAreNotConnected() {
        int[][] map = {
                {1, 2},
                {2, 1}
        };
        assertEquals(4, CountryCounter.countCountries(map));
    }

    @Test
    void singleColourIsOneCountry() {
        assertEquals(1, CountryCounter.countCountries(new int[][]{{7, 7}, {7, 7}}));
    }

    @Test
    void emptyMapHasNoCountries() {
        assertEquals(0, CountryCounter.countCountries(new int[0][0]));
        assertEquals(0, CountryCounter.countCountries(new int[3][0]));
    }

    @Test
    void largeMapDoesNotOverflowStack() {
        // a recursive DFS would throw StackOverflowError here
        int[][] map = new int[1000][1000];
        assertEquals(1, CountryCounter.countCountries(map));
    }

    @Test
    void rejectsJaggedMap() {
        int[][] jagged = {{1, 2}, {3}};
        assertThrows(IllegalArgumentException.class, () -> CountryCounter.countCountries(jagged));
    }

    @Test
    void rejectsNull() {
        assertThrows(NullPointerException.class, () -> CountryCounter.countCountries(null));
    }
}
