package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] cars = {6, 2, 12, 7};
        int k = 3;
        System.out.println(carParkingRoof(cars,k));

    }

    public static int carParkingRoof(int[] carSpots,int kMinLen){
        Arrays.sort(carSpots);
        List<Integer> ls = new ArrayList<>();
        List<Integer> lenLs = new ArrayList<>();
        int len = 0;

        for(int i = 0; i < carSpots.length; i++){
            ls.add(carSpots[i]);
            if(ls.size() == kMinLen){
                len = ls.get(2) - ls.get(0) + 1;
                lenLs.add(len);
                len = 0;
                ls.removeAll(ls);
                i = i-(kMinLen - 1);
            }
        }
        Collections.sort(lenLs);
        return lenLs.get(0);
    }
}
