package com.aucompany.ll;

import java.util.Map;

/**
 * Created by zoe on 2015/6/27.
 */
public enum HitLevel {

    None, Miss, Bad, Good, Great, Perfect;

    public static void getTips(HitLevel hit) {
        switch (hit) {
            case Miss:
                System.out.println("Miss!");
                break;
            case Bad:
                System.out.println("Bad!");
                break;
            case Good:
                System.out.println("Good!");
                break;
            case Great:
                System.out.println("Great!");
                break;
            case Perfect:
                System.out.println("Perfect!");
                break;
        }
    }

    public static int getScore(PlayerData playerData, HitLevel hitlevel) {
        double rate = 1;
        switch (hitlevel) {
            case Miss:
                rate = 0;
                break;
            case Bad:
                rate = 0.6;
                break;
            case Good:
                rate = 0.8;
                break;
            case Great:
                rate = 1;
                break;
            case Perfect:
                rate = 1.1;
                break;
        }
        return (int) Math.ceil(rate * playerData.hitScore);
    }

}
