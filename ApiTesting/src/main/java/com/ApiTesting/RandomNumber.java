
package com.ApiTesting;

import java.awt.Color;

import java.util.Random;



public class RandomNumber {
    
    static int getRandomNumber() {
        int x;
        Random rand = new Random();
        x = rand.nextInt(6) + 1;
        return x;
    }
    
    static Color getColor(int n) {
        switch (n) {
            case 1:
                return Color.red;
            case 2:
                return Color.GRAY;
            case 3:
                return Color.blue;
            case 4:
                return Color.orange;
            case 5:
                return Color.PINK;
            case 6:
                return Color.DARK_GRAY;
            default:
                return Color.MAGENTA;
        }
        
//        return Color.CYAN;
    }
    
    static boolean isUsed(int n, int[] arr) {
        for(int ar : arr) {
            if(n == ar) {
                return true;
            }
        }
        return false;
    }
    
}
