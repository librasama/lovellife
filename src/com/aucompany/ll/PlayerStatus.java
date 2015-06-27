package com.aucompany.ll;

/**
 * Created by zoe on 2015/6/26.
 * 玩家状态
 */
public class PlayerStatus {
    public PlayerStatus(int initPower) {
        this.initPower = initPower;
        this.power = initPower;
    }

    //单击统计
    int hit;
    int miss;
    int bad;
    int good;
    int great;
    int perfect;
    int score;           //分数统计
    int combo;          //连击统计
    int initPower;      //初始体力
    int power;
    int maxPerfect;
    int maxCombo;       //最大连击

    @Override
    public String toString() {
        return "PlayerStatus{" +
                "hit=" + hit +
                ", miss=" + miss +
                ", bad=" + bad +
                ", good=" + good +
                ", great=" + great +
                ", perfect=" + perfect +
                ", score=" + score +
                ", combo=" + combo +
                ", power=" + power +
                ", initPower=" + initPower +
                ", maxPerfect=" + maxPerfect +
                ", maxCombo=" + maxCombo +
                '}';
    }
}
