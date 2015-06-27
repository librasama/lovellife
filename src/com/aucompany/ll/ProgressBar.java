package com.aucompany.ll;

import java.util.Date;

/**
 * Created by zoe on 2015/6/27.
 * 时间进度条
 */
public class ProgressBar implements Runnable {

    Tune tune ;
    public ProgressBar(Tune tune) {
        this.tune = tune;
    }
    @Override
    public void run() {
        do {
            try{
                if(Math.ceil((new Date().getTime()-tune.startTimestamp)/1000) != tune.currentSecond) {
                    tune.currentSecond++;
                    System.out.println("歌曲已播放：" + tune.currentSecond + "秒");
                }
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        } while(!tune.isEnd());
    }
}
