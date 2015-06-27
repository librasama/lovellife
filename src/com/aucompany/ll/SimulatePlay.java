package com.aucompany.ll;

import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/27.
 * 模拟界面点击。消息生产者
 */
public class SimulatePlay implements Runnable{

    Tune tune;
    public SimulatePlay(Director director) {
        this.tune = tune;

    }

    public void addEvent(String eventType, Map<String, Object> eventInfo) {
        EventQueue.getInstance().add(new Event(eventType, eventInfo));
    }

    @Override
    public void run() {
        //模拟打击
    }
}
