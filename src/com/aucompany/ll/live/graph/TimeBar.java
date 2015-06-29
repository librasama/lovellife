package com.aucompany.ll.live.graph;

import com.aucompany.ll.live.Director;
import com.aucompany.ll.live.Tune;

import java.util.Date;

/**
 * Created by zoe on 2015/6/28.
 */
public class TimeBar implements Runnable {
    Director director ;
    public TimeBar(Director d) {
        this.director = d;
    }
    @Override
    public void run() {
        do {
            try{
                if(Math.ceil((new Date().getTime()-director.getStartTimestamp())/1000) != director.currentSecond) {
                    director.currentSecond++;
                    System.out.println("歌曲已播放：" + director.currentSecond + "秒");
                }
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        } while(!director.isEnd());
    }
}
