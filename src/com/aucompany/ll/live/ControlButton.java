package com.aucompany.ll.live;

import com.aucompany.ll.player.PlayerCard;

import java.util.Date;
import java.util.Random;

/**
 * Created by zoe on 2015/6/26.
 *  玩家按钮
 */
public class ControlButton {

    float x;                     //X坐标
    float y;                     //Y坐标
    float offsetRadius = 10;     //点击位置偏差半径
    PlayerCard card;             //卡牌
    Skill skill;                 //技能
    boolean onSkill;             //是否正在发动技能

    public ControlButton(PlayerCard card, float x, float y) {
        this.card = card;
        this.skill = card.getCardSkill();
        this.x = x;
        this.y = y;
    }

    /**
     * 发动/结束技能
     */
    public void checkSkill(PlayerStatus st){
        boolean enableDrive = Skill.DriveType.enableDrive(st, skill) && new Random(1).nextFloat() < skill.rate;
        if(enableDrive && !onSkill) {
            System.out.println("技能已触发，等待猛击中");
            skill.startTime = new Date().getTime();
            onSkill = true;
            startSkillAnime();
        }
        if(onSkill && skill.timeout()) {
            endSkillAnime();
        }

    }

    /**
     * 开始技能动画
     */
    public void startSkillAnime() {
        //播放声音 skill.audio;
        //换背景 skill.image;
    }

    /**
     * 结束技能动画
     */
    public void endSkillAnime() {
        System.out.println("技能结束");
        onSkill = false;
    }

    /**
     * 判断是否点击到
     * @param touchX
     * @param touchY
     * @return
     */
    public boolean inScope(float touchX, float touchY) {
        return Math.sqrt(Math.pow((touchX-x), 2)+Math.pow((touchY-y), 2)) <= offsetRadius;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
