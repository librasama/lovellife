package com.aucompany.ll.live;

import com.aucompany.ll.player.Card;
import com.aucompany.ll.player.CardSet;
import com.aucompany.ll.player.PlayerCard;

import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 玩家数值
 */
public class PlayerData {
    int playerId;               //玩家Id
    int cardSetId;              //卡组的Id
    int hitScore;               //每一击的力度，从队员计算出来
    int power;                  //体力值
    int fKizuno;                //友情绊值
    int fHit;                   //友情Score
    CardSet cards ;             //卡牌们

    public PlayerData(int playerId, int cardSetId, Card.Property property, int fKizuno, int fHit) {
        this.playerId = playerId;
        this.cardSetId = cardSetId;
        cards = new CardSet(playerId, cardSetId, "测试卡组");
        this.fKizuno = fKizuno;
        this.fHit = fHit;
        power = cards.getPower();
        this.hitScore = getHitScore(property);
    }

    public int getPower() {
        return power;
    }

    protected int getHitScore(Card.Property property) {
        return Integer.valueOf((cards.getPoint(property) + fHit) / cards.getCardNum());
    }

    public List<PlayerCard> getPlayerCards() {
        return cards.getSet();
    }
}
