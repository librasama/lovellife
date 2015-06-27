package com.aucompany.ll;

import com.aucompany.ll.test.TestSuit;

import java.util.Date;
import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 曲子
 */
public class Tune implements Runnable {
    public PlayerData playerData;        //玩家数据
    public PlayerStatus playerStatus;    //玩家状态
    public int judgeInterval = 20;       //最小判定周期
    public int lastTime;                 //持续时间
    int hits;                           //总击打数
    public int id;                      //乐曲id
    public long animateTime;           //动画播放的时间
    //判定时间偏差值（ms）
    long badOffset = 500;
    long goodOffset = 300;
    long greatOffset = 200;
    long perfectOffset = 100;
    public long startTimestamp;        //开始时间戳
    public int currentSecond;          //当前播放秒数
    List<Track> tracks;                 //音轨们


    public void initTuneAndTracks(int id) {
        playerStatus = new PlayerStatus(5);
        tracks = findTracks(id);
        for(Track t : tracks) {
            t.animateTime = animateTime;
            t.delayTime = badOffset;
            for(Beat b : t.getBeats()) {
                hits++;
            }
        }
    }
    public void run() {
        playMusic();
        do {
            try{Thread.sleep(judgeInterval);} catch (Exception e) {System.err.println(e);}
            for(Track track : tracks) {
                track.doTrackWork(startTimestamp);
                Beat b = track.curBeat.peek();
                if(b != null && (b.hitlevel != HitLevel.None)) {
                    System.out.print("！！！判定！！！" );
                    // 结束滑行动画
                    removeBeatCircle();
                    evaluateHit(b);
                    // 播放结果动画
                    hitResultAnimate();
                    //释放乐符
                    track.curBeat.poll();
                }
            }
            //联合评价
            linkedEvaluate();
            //乐曲结束或者体力为0
        } while(!isEnd());
        System.out.println("音乐结束");
    }

    /**
     * 寻找乐谱对应的音轨们
     */
    private List<Track> findTracks(int id) {
        //TODO 真正查库表
        List<Track> list = TestSuit.getTrackList();
        System.out.println("【Tune】找到id=" + id + "音轨们了~，共"+list.size()+"条音轨");
        return list;
    }


    //播放背景音乐
    public void playMusic(){
        //TODO
        System.out.println("开始播放音乐~");
    }

    public int getHits() {
        return hits;
    }

    /**
     * 乐谱结束
     * @return
     */
    public boolean isEnd(){
        return (new Date().getTime() > startTimestamp + lastTime) || (playerStatus.power < 0);
    }

    /**
     * 评估节奏
     */
    protected void evaluateHit(Beat beat) {
        //等级判断
        HitLevel.getTips(playerStatus, beat.hitlevel);
        //增加Score
        int score = HitLevel.getScore(playerData, beat.hitlevel);
        playerStatus.score += score;
        System.out.println("增加Score点数："+score);
        //体力减少.TODO 联动判定
        if(beat.type == BeatType.Star && (beat.hitlevel != HitLevel.Good || beat.hitlevel != HitLevel.Perfect)) {
            playerStatus.power -= 2;
            playerStatus.combo = 0;
            playerStatus.perfect = 0;
        }
        if(beat.type == BeatType.Basic && (beat.hitlevel == HitLevel.Bad || beat.hitlevel == HitLevel.Miss)) {
            playerStatus.power -= 1;
            playerStatus.combo = 0;
            playerStatus.perfect = 0;
        }
        if(beat.hitlevel == HitLevel.Perfect) {
            playerStatus.perfect++;
        }
    }

    public void linkedEvaluate() {
        //System.out.println("联合判定");
    }
    /**
     * 结束滑行动画
     */
    protected void removeBeatCircle() {}
    /**
     * 播放结果动画
     */
    protected void hitResultAnimate() {}
}
