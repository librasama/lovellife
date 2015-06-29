package com.aucompany.ll.live;

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
    int score;          //分数统计
    int combo;          //连击统计
    int initPower;      //初始体力
    int power;          //现存体力
    int comboPerfect;   //连击Perfect
    int maxCombo;       //最大连击
    long playtime;      //游戏时间(秒计时)


    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }

    public int getBad() {
        return bad;
    }

    public int getGood() {
        return good;
    }

    public int getGreat() {
        return great;
    }

    public int getPerfect() {
        return perfect;
    }

    public int getScore() {
        return score;
    }

    public int getCombo() {
        return combo;
    }

    public int getInitPower() {
        return initPower;
    }

    public int getPower() {
        return power;
    }

    public int getComboPerfect() {
        return comboPerfect;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public long getPlaytime() {
        return playtime;
    }

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
                ", comboPerfect=" + comboPerfect +
                ", maxCombo=" + maxCombo +
                '}';
    }
}
