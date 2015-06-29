package com.aucompany.ll.live;

import java.util.Date;

/**
 * Created by zoe on 2015/6/26.
 * 技能
 */
public class Skill {
    DriveSkillType driveType;       //发动类型
    float rate;                     //发动概率
    SkillType skillType;            //技能类型
    int skillVal;                   //技能数值
    int testCount;                  //已测试次数
    String audio;                   //发动技能声音
    String image;                   //卡面
    float lastTime;                 //技能持续时间
    float startTime;                //发动开始时间

    public boolean timeout(){
        return (new Date().getTime() - startTime > lastTime);
    }
}
