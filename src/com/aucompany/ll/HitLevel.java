package com.aucompany.ll;

import java.util.Map;

/**
 * Created by zoe on 2015/6/27.
 */
public enum HitLevel {

    None, Miss, Bad, Good, Great, Perfect;

    public static void getTips(PlayerStatus status, HitLevel hit) {
        status.hit++;
        switch (hit) {
            case Miss:
                status.miss++;
                System.out.println("Miss!");
                break;
            case Bad:
                status.bad++;
                System.out.println("Bad!");
                break;
            case Good:
                status.good++;
                System.out.println("Good!");
                break;
            case Great:
                status.great++;
                System.out.println("Great!");
                break;
            case Perfect:
                status.perfect++;
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
