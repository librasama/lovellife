package com.aucompany.ll;

/**
 * Created by zoe on 2015/6/26.
 * 节奏
 */
public class Beat {
    BeatType type;         //类型
    long rightTime;        //相对乐轨的位置
    int hitlevel = -1;     //打击结果
    long lastTime;         //持续时间

    boolean timeout = false; //超时

    //时间偏差值（ms）
    long badOffset = 1000;
    long goodOffset = 500;
    long greatOffset = 300;
    long perfectOffset = 100;

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
        if(relOffset <= perfectOffset || relOffset >= 0-perfectOffset) {
            hitlevel = 1;
        } else if (relOffset <= greatOffset || relOffset >= 0-greatOffset) {
            hitlevel = 2;
        } else if (relOffset <= goodOffset || relOffset >= 0-goodOffset) {
            hitlevel = 3;
        } else if (relOffset <= badOffset || relOffset >= 0-badOffset) {
            hitlevel = 4;
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
