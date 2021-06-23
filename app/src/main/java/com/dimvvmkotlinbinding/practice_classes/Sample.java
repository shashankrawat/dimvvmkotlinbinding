package com.dimvvmkotlinbinding.practice_classes;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Sample {

    public Sample(){

    }

    public void check1() {
        String v = "camelCase";
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < v.length(); i++) {
            int ascVal = v.charAt(i);
            if (ascVal >= 65 && ascVal <= 90) {
                char lowerCase = Character.toLowerCase(v.charAt(i));
                char lowerCase1 = (char) (ascVal ^ 32);
                char lowerCase2 = (char) (ascVal + 32);
                char lowerCase3 = (char) (v.charAt(i) + 32);
                Log.e("SNAKE_CASE", "" + lowerCase1 + ", " + lowerCase2 + ", " + lowerCase3);
                snakeCase.append("_").append(lowerCase1);
            } else {
                char upperCase = (char) (v.charAt(i) - 32);
                char upperCase1 = (char) (v.charAt(i) & 95);
                Log.e("SNAKE_CASE", "" + upperCase + ", " + upperCase1);
                snakeCase.append(v.charAt(i));
            }
        }

        Log.e("SNAKE_CASE", "" + snakeCase);
    }

    int[] arr = {6, 3, 2, 4, 5, 1, 19, 10, 23, 45, 7, 11, 8, -31, 40};
//    int[] arr = {1, 3, 2};


    public void check2() {
        int firstSmallest = arr[0];
        int size = arr.length;
        int secondSmallest = arr[size - 1];
        for (int i = 1; i < size; i++) {
            if (firstSmallest > arr[i]) {
                secondSmallest = firstSmallest;
                firstSmallest = arr[i];
            } else if (secondSmallest > arr[i]) {
                secondSmallest = arr[i];
            }
        }

        Log.e("SECOND_SMALL", "" + firstSmallest + ", " + secondSmallest);
    }

    public void check3() {
        int max = arr[0];
        int size = arr.length;
        int mid = (int) Math.abs(size / 2);
        int k = size - 1;
        int secondMax = arr[k];
        for (int i = 1; i <= mid; i++) {
            if (max < arr[i]) {
                secondMax = max;
                max = arr[i];
            } else if (secondMax < arr[i]) {
                secondMax = arr[i];
            }
            if (max < arr[k]) {
                secondMax = max;
                max = arr[k];
            } else if (secondMax < arr[k]) {
                secondMax = arr[k];
            }
            k--;
        }

        Log.e("MAX", "" + max + ", " + secondMax);
    }


    public void check4() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<Map.Entry<Integer, Integer>> values = map.entrySet();
        map.keySet();
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            item.getValue();
        }
        for(Integer key : map.keySet()){
            int value = map.get(key);
        }

    }

}
