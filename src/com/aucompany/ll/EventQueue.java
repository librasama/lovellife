package com.aucompany.ll;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

/**
 * Created by zoe on 2015/6/27.
 * 消息队列
 */
public class EventQueue {

    private static EventQueue instance;
    private Queue<Event> eQueue;

    private EventQueue() {
        eQueue = new ArrayDeque<>();
    }
    public static EventQueue getInstance() {
        if(instance == null)
            instance = new EventQueue();
        return instance;
    }

    /**
     * 增加消息
     * @param e
     */
    public void add(Event e) {
        System.out.println("【事件中心】增加事件："+e.eventType + "；相关数据："+e.eventInfo.toString());
        eQueue.add(e);
    }

    /**
     * 消费消息
     * @return
     */
    public Event deQueue() {
        Event e = eQueue.poll();
        if(e!= null) {
            System.out.println("【事件中心】事件被响应："+e.eventType + "；相关数据："+e.eventInfo.toString());
        }
        return e;
    }
}

