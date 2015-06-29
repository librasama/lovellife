package com.aucompany.ll.live;

import java.util.Date;

/**
 * Created by zoe on 2015/6/26.
 * 技能
 */
public class Skill {
    DriveType driveType;            //发动类型
    Type skillType;                 //技能类型
    float rate;                     //发动概率
    int skillVal;                   //技能数值
    int testCount;                  //已测试次数
    String audio;                   //发动技能声音
    String image;                   //卡面
    float lastTime;                 //技能持续时间
    float startTime;                //发动开始时间

    public boolean timeout(){
        return (new Date().getTime() - startTime > lastTime);
    }

    /**
     * 发动技能条件类型
     */
    public enum DriveType {
        Inteval,Perfect, Combo;

        /**
         * 是否可以发动
         * @param st
         * @param sk
         * @return
         */
        public static boolean drive(PlayerStatus st, Skill sk) {
            switch (sk.driveType) {
            case Inteval:
                return st.getPlaytime() >= sk.skillVal;
                case Perfect:
                    return st.getComboPerfect() >= sk.skillVal;
                case Combo:
                    return st.getCombo() >= sk.skillVal;
                default:
                    return true;
            }

        }
    }

    public enum Type {

    }
}


