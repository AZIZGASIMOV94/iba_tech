package com.company;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public void findCountries(int[][] grid) {
        int rows = grid.length;
        if (rows == 0)
            return;

        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Set<ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>> islands = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int start_x = i;
                    int start_y = j;
                    ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> island = new ArrayList<>();
                    find(grid, i, j, start_x, start_y, visited, island);
                    islands.add(island);
                }
            }
        }

        if (islands.size() > 0) {
            System.out.println("num of countries : " + islands.size());
        } else {
            System.out.println("no countries: ");
        }
    }


    public void find(int[][] grid, int row, int col, int start_x, int start_y, boolean[][] visited, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> island) {

        int height = grid.length;
        int width = grid[0].length;

        if (row < 0 || col < 0 || row >= height || col >= width || visited[row][col] )
            return;

        island.add(new AbstractMap.SimpleEntry<>(row - start_x, col - start_y));

        visited[row][col] = true;
        find(grid, row + 1, col, start_x, start_y, visited, island);
        find(grid, row - 1, col, start_x, start_y, visited, island);
        find(grid, row, col + 1, start_x, start_y, visited, island);
        find(grid, row, col - 1, start_x, start_y, visited, island);
    }


    public static void main(String[] args) {
	    // write your code here
        int[][] a = new int[7][3];
        a[0][0] = 5; a[0][1] = 4;a[0][2] = 4;//row 1
        a[1][0] = 4; a[1][1] = 3;a[1][2] = 4;//row 2
        a[2][0] = 3; a[2][1] = 2;a[2][2] = 4;//row 3
        a[3][0] = 2; a[3][1] = 2;a[3][2] = 2;//row 4
        a[4][0] = 3; a[4][1] = 3;a[4][2] = 4;//row 5
        a[5][0] = 1; a[5][1] = 4;a[5][2] = 4;//row 6
        a[6][0] = 4; a[6][1] = 1;a[6][2] = 1;//row 7


        Main m = new Main();
        m.findCountries(a);
    }
}
