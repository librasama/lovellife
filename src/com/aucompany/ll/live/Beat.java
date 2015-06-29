package com.aucompany.ll.live;

/**
 * Created by zoe on 2015/6/26.
 * 节奏
 */
public class Beat {

    public Beat(){}
    public Beat(Tune tune, BeatType type, long rightTime){
        this.type = type;
        this.rightTime = rightTime;
    }

    BeatType type;         //类型
    long showTime;         //显示的时间
    long rightTime;        //相对乐轨的位置
    long lastTime;         //持续时间
    boolean timeout = false; //超时
    HitLevel hitlevel = HitLevel.None;       //打击结果

    int loss;
    int bloodBuff;
    String perfectAudio;
    String greatAudio;
    String goodAudio;
    String badAudio;

    /**
     * 是否是长击打
     * @return
     */
    public boolean isLastBeat() {
        return BeatType.isLong(type);
    }

    public long getRightTime() {
        return rightTime;
    }
}
