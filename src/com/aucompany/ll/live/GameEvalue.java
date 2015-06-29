package com.aucompany.ll.live;

import com.aucompany.ll.live.event.IEventCallback;

import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 * 游戏结果评级
 */
public class GameEvalue {

    PlayerData playerData;                  //玩家信息
    PlayerStatus status;                    //玩家状态
    Tune tune;                              //乐谱信息

    long badOffset;
    long goodOffset;
    long greatOffset;
    long perfectOffset;

    public GameEvalue(Director director){
        this.tune = director.tune;
        this.status = director.status;
        this.playerData = director.player;

        this.badOffset = tune.badOffset;
        this.goodOffset = tune.goodOffset;
        this.greatOffset = tune.greatOffset;
        this.perfectOffset = tune.perfectOffset;
        initEventListener();
    }
    private void initEventListener() {
        tune.d.eventHandler.addEventCallback("SkillScore", new IEventCallback() {
            @Override
            public void handleEvent(String eventType, Map<String, Object> eventInfo) {
                int value = (Integer)eventInfo.get("val");
                status.score += value;
            }
        });
    }
    /**
     * 判定节奏
     * @param beat
     * @param hitTime
     */
    public HitLevel judgeBeat(Beat beat, long hitTime) {
        HitLevel hitlevel = HitLevel.None;
        long rightTime = beat.getRightTime();
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
        beat.hitlevel = hitlevel;
        return hitlevel;
    }


    /**
     * 评估节奏
     */
    public void evaluateHit(Beat beat) {
        //等级判断
        HitLevel.getTips(status, beat.hitlevel);
        //增加Score
        int score = HitLevel.getScore(playerData, beat.hitlevel);
        status.score += score;
        System.out.println("增加Score点数："+score);
        //判定是否达到下一评级，是则更新评级条

        //体力减少.TODO 联动判定
        if(beat.type == BeatType.Star && (beat.hitlevel != HitLevel.Good || beat.hitlevel != HitLevel.Perfect)) {
            status.power -= 2;
            status.combo = 0;
            status.perfect = 0;
        }
        if(beat.type == BeatType.Basic && (beat.hitlevel == HitLevel.Bad || beat.hitlevel == HitLevel.Miss)) {
            status.power -= 1;
            status.combo = 0;
            status.perfect = 0;
        }
        if(beat.hitlevel == HitLevel.Perfect) {
            status.perfect++;
        }
        if(beat.hitlevel == HitLevel.Great || beat.hitlevel == HitLevel.Perfect) {
            status.combo++;
            status.maxCombo = Math.max(status.maxCombo, status.combo);
        }

        //给Stage发送事件！！更新界面
    }

    /**
     * 每轮评级（联合评价/修正结果）
     */
    public void evalueRound() {

    }

    /**
     * 游戏整体评级
     */
    public void evalueGame() {
        //演奏成功失败蒙版
        if(status.hit < tune.getHits()) {
            System.out.println("演奏失败~~~Niconiconi~");
        } else {
            System.out.println("演奏成功！！！Makimakima！");
        }
        //撤销演奏页面
        //显示分数统计页面
        System.out.println("结果发表："+status.toString());
    }
}
