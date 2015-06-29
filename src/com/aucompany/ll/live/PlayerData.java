package com.aucompany.ll.live;

import com.aucompany.ll.player.PlayerCard;

import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 玩家数值
 */
public class PlayerData {
    int hitScore;               //每一击的力度，从队员计算出来
    int power;                  //体力值
    int fKizuno;                //绊值
    int teamId;                 //卡牌的teamId
    List<PlayerCard> cards ;    //卡牌们

    public PlayerData(int teamId) {
        this.teamId = teamId;

    }

    public int getHitScore() {
        return hitScore;
    }

    public int getPower() {
        return power;
    }

}
