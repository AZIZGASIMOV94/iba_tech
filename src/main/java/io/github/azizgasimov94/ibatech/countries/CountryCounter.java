package io.github.azizgasimov94.ibatech.countries;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * <b>Count Countries.</b>
 *
 * <p>A map is a rectangular grid where each cell holds a colour. A country
 * is a group of cells with the same colour connected horizontally or
 * vertically (not diagonally). Count the countries on the map.</p>
 *
 * <p><b>Approach:</b> scan every cell; when an unvisited cell is found, it
 * starts a new country, and a breadth-first search marks every cell of that
 * country as visited. The search uses an explicit queue instead of
 * recursion, so large maps can't overflow the call stack.</p>
 *
 * <p><b>Complexity:</b> O(rows &times; cols) time and space.</p>
 */
public final class CountryCounter {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private CountryCounter() {
    }

    /**
     * @param map rectangular grid of colours; may be empty
     * @return the number of countries
     * @throws IllegalArgumentException if the rows have different lengths
     */
    public static int countCountries(int[][] map) {
        Objects.requireNonNull(map, "map must not be null");
        if (map.length == 0) {
            return 0;
        }
        int rows = map.length;
        int cols = requireRectangular(map);
        if (cols == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[rows][cols];
        int countries = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col]) {
                    countries++;
                    markCountry(map, visited, row, col);
                }
            }
        }
        return countries;
    }

    /** Breadth-first search that marks every cell of the country containing (startRow, startCol). */
    private static void markCountry(int[][] map, boolean[][] visited, int startRow, int startCol) {
        int colour = map[startRow][startCol];
        Deque<int[]> queue = new ArrayDeque<>();
        visited[startRow][startCol] = true;
        queue.add(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < map.length && c >= 0 && c < map[0].length
                        && !visited[r][c] && map[r][c] == colour) {
                    visited[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
    }

    private static int requireRectangular(int[][] map) {
        int cols = Objects.requireNonNull(map[0], "map rows must not be null").length;
        for (int i = 1; i < map.length; i++) {
            if (map[i] == null || map[i].length != cols) {
                throw new IllegalArgumentException("map must be rectangular; row " + i
                        + " has a different length than row 0");
            }
        }
        return cols;
    }
}
