package com.aucompany.ll;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zoe on 2015/6/26.
 * 音轨
 */
public class Track {
    Queue<Beat> beats;          //节奏队列
    Queue<Beat> curBeat;        //当前屏幕上的节奏
    public PlaySinger btn;      //玩家按钮
    long animateTime;           //动画播放的时间
    long delayTime;             //最大延迟时间

    public void doTrackWork() {

        //到了下个乐符开始的时间没
        if(beats.peek()!= null && (beats.peek().rightTime - animateTime <= new Date().getTime())) {
            //如果是，取乐符，判断类型，播放滑行动画。
            System.out.print("Beat 出现在屏幕上" );
            curBeat.add(beats.poll());
        }
        //到了当前最先的乐符结束时间没
        if((curBeat.peek()!= null) && (curBeat.peek().rightTime + delayTime > new Date().getTime())) {
            System.out.print("Beat 超时！！！！！！！！！！！！！" );
            curBeat.peek().timeout = true;
        }

        //测试技能发动条件
        //如果是，发动技能动画
        System.out.println("Track loop");
    }

    public static Track forTest() {
        Track t = new Track();
        Queue q = new LinkedBlockingQueue<>();
        //TODO for
        q.add(new Beat(BeatType.Basic, 1500));
        t.beats = q;
        t.curBeat = new LinkedBlockingQueue<>();
        return t;
    }

}
