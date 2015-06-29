package com.aucompany.ll.live.event;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by zoe on 2015/6/27.
 * 消息队列
 */
public class EventBus {

    private static EventBus instance;
    private Queue<Event>  eQueue;

    private EventBus() {
        eQueue = new ArrayDeque<>();
    }
    public static EventBus getInstance() {
        if(instance == null)
            instance = new EventBus();
        return instance;
    }

    /**
     * 增加消息
     * @param e
     */
    public synchronized void add(Event e) {
        String msg = "";
        if(e.eventInfo != null) msg =  e.eventInfo.toString();
        System.out.println("【事件中心】事件发生："+e.eventType + "；相关数据：" + msg);
        eQueue.add(e);
    }

    /**
     * 消费消息
     * @return
     */
    public synchronized Event deQueue() {
        Event e = eQueue.poll();
        if(e!= null) {
//            System.out.println("【事件中心】事件被响应："+e.eventType + "；相关数据："+e.eventInfo.toString());
        }
        return e;
    }
}

