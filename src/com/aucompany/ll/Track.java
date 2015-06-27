package com.aucompany.ll;

import java.util.Date;
import java.util.Queue;

/**
 * Created by zoe on 2015/6/26.
 * 音轨
 */
public class Track {
    Queue<Beat> beats;
    Beat curBeat;               //当前最靠前的节奏
    public PlaySinger btn;      //玩家按钮

    public void doTrackWork() {
        do {
            //到了下个乐符开始的时间没
            //如果是，取乐符，判断类型，播放滑行动画。

            //到了当前最先的乐符结束时间没
            //如果是，置为timeout。

            //测试技能发动条件
            //如果是，发动技能动画
        } while(true);
    }


}
