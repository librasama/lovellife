package com.aucompany.ll;

import java.util.Date;
import java.util.Random;

/**
 * Created by zoe on 2015/6/26.
 *  玩家按钮
 */
public class PlaySinger {
    int pos;//位置
    Track track; //音轨
    Skill skill; //技能
    PlayerStatus st;//玩家状态
    boolean onSkill; //是否正在发动技能
    Beat currBeat;//当前节奏点

    //触摸
    public void onTouchIn() {
        //更新血量条
        //更新体力条
    }
    //离开
    public void onTouchOut() {
        //长按Beat
        if(currBeat.isLastBeat()) {
            //更新血量条
            //更新体力条
        }
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
        boolean enableDrive = DriveSkillType.drive(st, skill) && new Random(1).nextFloat() >= skill.rate;
        if(enableDrive) {
            skill.startTime = new Date().getTime();
            onSkill = true;
        } else if(onSkill && skill.timeout()) {
            onSkill = false;
        }
    }
}