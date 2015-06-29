package com.aucompany.ll.live;

import com.aucompany.ll.live.event.Event;
import com.aucompany.ll.live.event.EventBus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zoe on 2015/6/26.
 * 技能
 */
public class Skill {
    DriveType driveType;            //发动类型
    double rate;                     //发动概率
    int threshold;                  //发动阈值

    Type skillType;                 //技能类型
    int skillval;                   //技能数值

    int testCount;                  //已测试次数
    String audio;                   //发动技能声音
    String image;                   //卡面
    float lastTime;                 //技能持续时间
    float startTime;                //发动开始时间

    public Skill(DriveType driveType, double rate, int threshold, Type skillType, int skillval, String audio, String image) {
        this.driveType = driveType;
        this.rate = rate;
        this.threshold = threshold;
        this.skillType = skillType;
        this.skillval = skillval;
        this.audio = audio;
        this.image = image;
    }

    public boolean timeout(){
        return (new Date().getTime() - startTime > lastTime);
    }

    /**
     * 发动技能条件类型
     */
    public enum DriveType {
        Inteval ,Perfect, Combo;

        /**
         * 是否可以发动
         * @param st
         * @param sk
         * @return
         */
        public static boolean enableDrive(PlayerStatus st, Skill sk) {
            switch (sk.driveType) {
            case Inteval:
                return st.getPlaytime() >= sk.threshold;
                case Perfect:
                    return st.getComboPerfect() >= sk.threshold;
                case Combo:
                    return st.getCombo() >= sk.threshold;
                default:
                    return true;
            }

        }
    }

    /**
     * 发动技能
     */
    public void successSkill() {
        switch (this.skillType) {
            case Score:
                Map<String, Object> map = new HashMap<>();
                map.put("val", skillval);
                EventBus.getInstance().add(new Event("SkillScore", map));
                break;
            case Power:
                break;
            case Judge:
                break;
        }
    }

    public enum Type {
        Score, Power, Judge;

    }
}


