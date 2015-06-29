package com.aucompany.ll.test;

import com.aucompany.ll.live.Beat;
import com.aucompany.ll.live.Director;
import com.aucompany.ll.live.Track;
import com.aucompany.ll.live.event.Event;
import com.aucompany.ll.live.event.EventBus;

import java.util.*;

/**
 * Created by zoe on 2015/6/27.
 * 模拟界面点击。消息生产者
 */
public class SimulatePlay implements Runnable{

    Director d;
    public SimulatePlay(Director director) {
        this.d = director;
    }

    /**
     * 发生事件
     * @param eventType
     * @param eventInfo
     */
    public static void comeupEvent(String eventType, Map<String, Object> eventInfo) {
        EventBus.getInstance().add(new Event(eventType, eventInfo));
    }

    private List<List<Event>> eventQueueList = new ArrayList<List<Event>>();

    /**
     * 收集数据模拟打击
     */
    private void init() {
        System.out.println("++++++++++++++预定事件++++++++++++");
        for(Track t : d.getTracks()) {
            System.out.println("++++++++++++++音轨"+t.getPos()+"++++++++++++");
            List<Event> eventQueue = new ArrayList<Event>();
            for(Beat b : t.getBeats()) {
                Map<String, Object> map = new HashMap<>();
                map.put("time", b.getRightTime()+new Random().nextInt(200));
                map.put("x", t.controlBtn.getX());
                map.put("y", t.controlBtn.getY());
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
                        long rightTime = d.getStartTimestamp() + new Long(e.getEventInfo().get("time").toString());
                        try {
                            long sleepTime = rightTime - new Date().getTime();
                            if(sleepTime > 0) {
                                Thread.sleep(sleepTime);
                            }
                            comeupEvent(e.getEventType(), e.getEventInfo());
                            index++;
                        } catch (Exception xx) {
                            System.out.print(xx);
                        }
                    } while(index < eventQueue.size() && !d.isEnd());
                }
            }));
        }
        for(Thread t: threads) {t.start();}
    }
}
