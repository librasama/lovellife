package com.aucompany.ll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 曲子
 */
public class Tune implements Runnable {
    int minimumBeat = 20;       //最小节拍
    int lastTime;               //持续时间
    int id;                     //乐曲id
    long startTimestamp;        //开始时间戳
    long pauseTimestamp;        //暂停时间戳
    List<Track> tracks = findTracks(id);
    public void run() {
        playMusic();
        do {
            //休眠一段时间sleep(); 20ms
            for(Track track : tracks) {
                track.doTrackWork();
                Beat b = track.curBeat;
                if(b.timeout || b.hitlevel != -1) {
                    // 结束滑行动画
                    removeBeatCircle();
                    evaluateHit(b);
                    // 播放结果动画
                    hitResultAnimate();
                }
            }
            //联合评价
            linkedEvaluate();
            //乐曲结束
        } while(true);

    }

    /**
     * 测试用
     * @return
     */
    public static Tune forTest() {
        Tune t = new Tune();
        t.id = 1;
        t.lastTime = 10000;//10秒
        t.minimumBeat = 10;
        return t;
    }

    /**
     * 寻找乐谱对应的音轨们
     */
    public List<Track> findTracks(int id) {
        //TODO 真正查库表
        if(id == 1) {
            System.out.println("找到id="+id+"音轨们了~");
            List<Track>  list = new ArrayList<>();
            list.add(new Track());
            return list;
        }
        return null;
    }

    //播放背景音乐
    public void playMusic(){
        //TODO
        System.out.println("开始播放音乐~");
    }

    private void pause() {
        pauseTimestamp = new Date().getTime();
    }

    private void play() {
        startTimestamp += new Date().getTime() - pauseTimestamp;
    }

    /**
     * 评估节奏
     */
    protected void evaluateHit(Beat beat) {
        //等级判断
        //Score增加
        //体力减少
        //连击 Combo
    }

    public void linkedEvaluate() {

    }
    /**
     * 打击等级
     * @return
     */
    private int hitLevel(Beat curBeat) {
        return 2;
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
