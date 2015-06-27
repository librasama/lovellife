package com.aucompany.ll;

import java.util.Map;

/**
 * Created by zoe on 2015/6/27.
 */
public enum HitLevel {

    Miss, Bad, Good, Great, Perfect;

    public static void getTips(int hit) {
        switch (hit) {
            case -1:
                System.out.println("Miss!");
                break;
            case 1:
                System.out.println("Bad!");
                break;
            case 2:
                System.out.println("Good!");
                break;
            case 3:
                System.out.println("Great!");
                break;
            case 4:
                System.out.println("Perfect!");
                break;
        }
    }

    public static int getScore(PlayerData playerData, int hitlevel) {
        double rate = 1;
        switch (hitlevel) {
            case -1:
                rate = 0;
                break;
            case 1:
                rate = 0.6;
                break;
            case 2:
                rate = 0.8;
                break;
            case 3:
                rate = 1;
                break;
            case 4:
                rate = 1.1;
                break;
        }
        return (int) Math.ceil(rate * playerData.hitScore);
    }

}
