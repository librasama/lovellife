package com.aucompany.ll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 曲子
 */
public class Tune implements Runnable {
    PlayerData playerData;        //玩家数据
    int minimumBeat = 20;       //最小节拍
    int lastTime;               //持续时间
    int id;                     //乐曲id
    long startTimestamp;        //开始时间戳

    List<Track> tracks = findTracks(id);
    public void run() {
        playMusic();
        do {
            try{Thread.sleep(minimumBeat);} catch (Exception e) {System.err.println(e);}
            for(Track track : tracks) {
                track.doTrackWork(startTimestamp);
                Beat b = track.curBeat.peek();
                if(b != null && (b.timeout || b.hitlevel != -1)) {
                    System.out.print("审判！！！！" );
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
            //乐曲结束
        } while(!isEnd());
        System.out.println("音乐结束");
    }

    /**
     * 测试用
     * @return
     */
    public static Tune forTest() {
        Tune t = new Tune();
        t.id = 1;
        t.lastTime = 4000;//10秒
        t.minimumBeat = 20;
        return t;
    }

    /**
     * 寻找乐谱对应的音轨们
     */
    public List<Track> findTracks(int id) {
        //TODO 真正查库表
        List<Track>  list = new ArrayList<>();
        list.add(Track.forTest());
        System.out.println("找到id=" + id + "音轨们了~，共"+list.size()+"条音轨");
        return list;
    }

    //播放背景音乐
    public void playMusic(){
        //TODO
        System.out.println("开始播放音乐~");
    }

    /**
     * 乐谱结束
     * @return
     */
    public boolean isEnd(){
        return new Date().getTime() > startTimestamp + lastTime;
    }

    /**
     * 评估节奏
     */
    protected void evaluateHit(Beat beat) {
        //等级判断
        HitLevel.getTips(beat.hitlevel);
        //增加Score
        int score = HitLevel.getScore(playerData, beat.hitlevel);
        System.out.println("增加Score点数："+score);
        //体力减少
        //连击 Combo
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
