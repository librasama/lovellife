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
    public PlaySinger playBtn;      //玩家按钮
    long animateTime;           //动画播放的时间
    long delayTime;             //最大延迟时间

    public void doTrackWork(long startTimestamp) {
        long current = new Date().getTime();
        //到了下个乐符开始的时间没
        if(beats.peek()!= null && (beats.peek().rightTime - animateTime <= current-startTimestamp)) {
            //如果是，取乐符，判断类型，播放滑行动画。
            System.out.println("=========信息开始=========");
            System.out.println("Beat 出现在屏幕上");
            System.out.println("正确节拍："+beats.peek().rightTime );
            System.out.println("动画时间：" + animateTime);
            System.out.println("当前时间："+current);
            System.out.println("起始时间："+startTimestamp );
            System.out.println("=========信息结束=========");
            curBeat.add(beats.poll());
        }
        //到了当前最先的乐符结束时间没
        if((curBeat.peek()!= null) && (curBeat.peek().rightTime + delayTime < current-startTimestamp)) {
            System.out.println("=========信息开始=========");
            System.out.println("Beat 超时！！！！！！！！！！！！！" );
            System.out.println("正确节拍："+curBeat.peek().rightTime );
            System.out.println("延时信息：" + delayTime);
            System.out.println("当前时间："+current);
            System.out.println("起始时间："+startTimestamp );
            System.out.println("=========信息结束=========");
            curBeat.peek().timeout = true;
            curBeat.peek().hitlevel = HitLevel.Miss;
        }

        //测试技能发动条件
        //如果是，发动技能动画
        //System.out.println("Track loop");
    }

    public static Track forTest() {
        Track t = new Track();
        t.animateTime = 1000;
        t.delayTime = 500;
        PlaySinger singer = new PlaySinger();
        singer.x = 100;
        singer.y = 100;
        t.playBtn = singer ;
        Queue q = new LinkedBlockingQueue<>();
        //TODO for
        q.add(new Beat(BeatType.Basic, 1500));
        t.beats = q;
        t.curBeat = new LinkedBlockingQueue<>();
        return t;
    }

}
