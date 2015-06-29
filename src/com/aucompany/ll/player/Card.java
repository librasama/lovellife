package com.aucompany.ll.player;

import com.aucompany.ll.live.Skill;

/**
 * Created by zoe on 2015/6/29.
 * 卡面
 */
public class Card {

    int id;              //模板Id
    int maxKizuno;       //满绊值
    int[] coolPoint;     //Cool点数
    int[] smilePoint;    //最大Smile点数
    int[] purePoint;     //最大Pure点数
    int[] powerLevel;    //体力值界限数组

    int maxLevel;       //最大等级
    boolean isSenior;   //是否是觉醒卡
    int relatedId;      //关联的(未)觉醒卡牌id

    Skill skill;    //技能
    Property property;  //卡面属性
    Type type;          //卡牌类型


    public Card() {}
    public Card(Property property, int maxLevel, int[] coolArr,int[] pureArr,int[] smileArr, int[] powerLevel) {
        this.property = property;
        this.maxLevel = maxLevel;
        this.coolPoint = coolArr;
        this.smilePoint = smileArr;
        this.purePoint = pureArr;
        this.powerLevel = powerLevel;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public static int getPoint(Card c,Property p, int level) {
        switch (p) {
            case Cool: return c.coolPoint[level-1];
            case Pure: return c.purePoint[level-1];
            case Smile: return c.smilePoint[level-1];
        }
        return 0;
    }

    public static int getPower(Card c, int level) {
        int i = 1;
        for(int edge : c.powerLevel) {
            if(level < edge) {break;}
            i++;
        }
        return i;
    }

    /**
     * 最大体力值
     * @return
     */
    public int getMaxPower() {
        return this.powerLevel.length +1;
    }

    public enum Property {
        Cool, Pure, Smile;
    }

    public enum Type  {
        N, R, SR, UR
    }

}


