package com.aucompany.ll.player;

import com.aucompany.ll.test.TestSuit;

import java.util.Map;

/**
 * Created by zoe on 2015/6/29.
 * 玩家的卡牌
 */
public class PlayerCard {
    int id;         //Id
    int level;      //当前等级
    Card card;      //模板卡面
    int kizuno;     //绊值
    int power;      //当前体力值
    int coolPoint;
    int purePoint;
    int smilePoint;

    public PlayerCard(int id, int level) {
        this.id = id;
        Map<String, Integer> map = getPlayerCardInfo(id);
        int cardid = map.get("cardid");
        kizuno = map.get("kizuno");
        card = getTemplateCard(cardid);
        this.level = level;
        this.coolPoint = Card.getPoint(card, Card.Property.Cool, level);
        this.purePoint = Card.getPoint(card, Card.Property.Pure, level);
        this.smilePoint = Card.getPoint(card, Card.Property.Smile, level);
        this.power = Card.getPower(card, level);
    }

    public int getPoint(Card.Property type) {
        if(type == Card.Property.Cool) return this.coolPoint;
        if(type == Card.Property.Pure) return this.purePoint;
        if(type == Card.Property.Smile) return this.smilePoint;
        return 0;
    }

    /**
     * TODO 获取玩家卡牌信息
     * @param id
     * @return
     */
    private Map<String, Integer> getPlayerCardInfo(int id) {
        return TestSuit.getPlayerCard(id);
    }

    /**
     * TODO
     * 玩家CardId查询模板Card信息
     * @param id
     * @return
     */
    private Card getTemplateCard(int id) {
        return TestSuit.getCard(id);
    }
}
