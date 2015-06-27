package com.aucompany.ll;

import java.util.*;

/**
 * Created by zoe on 2015/6/27.
 * 模拟界面点击。消息生产者
 */
public class SimulatePlay implements Runnable{

    Tune tune;
    public SimulatePlay(Director director) {
        this.tune = director.tune;
    }

    /**
     * 发生事件
     * @param eventType
     * @param eventInfo
     */
    public void comeupEvent(String eventType, Map<String, Object> eventInfo) {
        EventQueue.getInstance().add(new Event(eventType, eventInfo));
    }

    private List<Event> eventQueue = new ArrayList<Event>();

    /**
     * 收集数据模拟打击，全Perfect
     */
    private void init() {
        for(Track t : this.tune.tracks) {
            for(Beat b : t.beats) {
                Map<String, Object> map = new HashMap<>();
                map.put("time", b.rightTime);
                map.put("x", t.playBtn.x);
                map.put("y", t.playBtn.y);
                eventQueue.add(new Event("TouchIn", map));
            }
        }
    }

    @Override
    public void run() {
        init();
        int index = 0;
        do {
            Event e = eventQueue.get(index);
            long rightTime = tune.startTimestamp + new Long(e.eventInfo.get("time").toString());
            try {
                Thread.sleep(rightTime - new Date().getTime());
                comeupEvent(e.eventType, e.eventInfo);
                index++;
            } catch (Exception xx) {
                System.out.print(xx);
            }
        } while(index < eventQueue.size() && !tune.isEnd());
    }
}
