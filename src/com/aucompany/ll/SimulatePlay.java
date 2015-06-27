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

    private List<List<Event>> eventQueueList = new ArrayList<List<Event>>();

    /**
     * 收集数据模拟打击
     */
    private void init() {
        System.out.println("++++++++++++++预定事件++++++++++++");
        for(Track t : this.tune.tracks) {
            System.out.println("++++++++++++++音轨"+t.pos+"++++++++++++");
            List<Event> eventQueue = new ArrayList<Event>();
            for(Beat b : t.beats) {
                Map<String, Object> map = new HashMap<>();
                map.put("time", b.rightTime+new Random().nextInt(600));
                map.put("x", t.playBtn.x);
                map.put("y", t.playBtn.y);
                eventQueue.add(new Event("TouchIn", map));
                System.out.print(map.get("time") + ",   ");
            }
            System.out.println("");
            eventQueueList.add(eventQueue);
        }
    }

    @Override
    public void run() {
        init();
        List<Thread> threads = new ArrayList<>();
        for(List<Event> eventQueue : eventQueueList) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    int index = 0;
                    do {
                        Event e = eventQueue.get(index);
                        long rightTime = tune.startTimestamp + new Long(e.eventInfo.get("time").toString());
                        try {
                            long sleepTime = rightTime - new Date().getTime();
                            if(sleepTime > 0) {
                                Thread.sleep(sleepTime);
                            }
                            comeupEvent(e.eventType, e.eventInfo);
                            index++;
                        } catch (Exception xx) {
                            System.out.print(xx);
                        }
                    } while(index < eventQueue.size() && !tune.isEnd());
                }
            }));
        }
        for(Thread t: threads) {t.start();}
    }
}
