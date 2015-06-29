package com.aucompany.ll.live;

import java.util.Date;
import java.util.Random;

/**
 * Created by zoe on 2015/6/26.
 *  玩家按钮
 */
public class ControlButton {

    float x;                //X坐标
    float y;                //Y坐标
    float offsetRadius = 10; //点击位置偏差半径
    Track track;            //音轨
    Skill skill;            //技能
    PlayerStatus st;        //玩家状态
    boolean onSkill;        //是否正在发动技能
    Beat currBeat;          //当前节奏点

    public ControlButton(Track track, float x, float y) {
        this.track = track;
        this.x = x;
        this.y = y;
    }

    //触摸
    public void onTouchIn() {
        //更新血量条
        //更新体力条
    }

    //轮询更新状态
    public void updateStatus() {
        tagSkill();
    }

    public void detechBeat() {
        Beat beat = track.beats.peek();
        currBeat = beat;
    }
    //标记是否发动技能
    public void tagSkill(){
        boolean enableDrive = Skill.DriveType.drive(st, skill) && new Random(1).nextFloat() >= skill.rate;
        if(enableDrive) {
            skill.startTime = new Date().getTime();
            onSkill = true;
        } else if(onSkill && skill.timeout()) {
            onSkill = false;
        }
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