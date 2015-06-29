package com.aucompany.ll.player;

import com.aucompany.ll.test.TestSuit;

import java.util.List;

/**
 * Created by zoe on 2015/6/29.
 * 卡组
 */
public class CardSet {
    int playerId;            //玩家Id
    int cardSetId;           //卡组Id
    int cardSetName;         //卡组名称
    List<PlayerCard> set;   //卡组
    PlayerCard center;      //卡组Center

    public List<PlayerCard> getSet() {
        return set;
    }

    public PlayerCard getCenter() {
        return center;
    }

    public CardSet(int playerId, int setId, int cardSetName) {
        this.playerId = playerId;
        this.cardSetId = setId;
        this.cardSetName = cardSetName;
    }

    /**
     * TODO 加载卡牌组
     * @return
     */
    private List<PlayerCard> loadCardSet(int playerId, int setId) {
        return TestSuit.getCardSet();
    }

    public int getPower() {
        int power=0;
        for(PlayerCard card : set) {
            power += card.power;
        }
        return power;
    }

    public int getPoint(Card.Property property) {
        int point = 0;
        for(PlayerCard card : set) {
            point += card.getPoint(property);
        }
        return point;
    }
}
