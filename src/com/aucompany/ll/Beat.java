package com.aucompany.ll;

/**
 * Created by zoe on 2015/6/26.
 * 节奏
 */
public class Beat {

    public Beat(){}
    public Beat(Tune tune, BeatType type, long rightTime){
        this.type = type;
        this.rightTime = rightTime;
        this.badOffset = tune.badOffset;
        this.goodOffset = tune.goodOffset;
        this.greatOffset = tune.greatOffset;
        this.perfectOffset = tune.perfectOffset;
    }

    BeatType type;         //类型
    long showTime;         //显示的时间
    long rightTime;                         //相对乐轨的位置
    HitLevel hitlevel = HitLevel.None;     //打击结果
    long lastTime;         //持续时间
    boolean timeout = false; //超时
    long badOffset;
    long goodOffset;
    long greatOffset;
    long perfectOffset;

    int loss;
    int bloodBuff;
    String perfectAudio;
    String greatAudio;
    String goodAudio;
    String badAudio;

    /**
     * 打击结果
     * @param hitTime
     * @return
     */
    public void tryHit(long hitTime) {
        long relOffset = hitTime-rightTime;
        System.out.println("打击时间的偏差值："+relOffset);
        if(relOffset <= perfectOffset && relOffset >= 0-perfectOffset) {
            hitlevel = HitLevel.Perfect;
        } else if (relOffset <= greatOffset && relOffset >= 0-greatOffset) {
            hitlevel = HitLevel.Great;
        } else if (relOffset <= goodOffset && relOffset >= 0-goodOffset) {
            hitlevel = HitLevel.Good;
        } else if (relOffset <= badOffset && relOffset >= 0-badOffset) {
            hitlevel = HitLevel.Bad;
        }
    }
    /**
     * 是否是长击打
     * @return
     */
    public boolean isLastBeat() {
        return BeatType.isLong(type);
    }
}
