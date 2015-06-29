package com.aucompany.ll.live.event;

import com.aucompany.ll.live.Director;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/29.
 * 事件处理中心
 */
public class EventHandler implements Runnable {

    Director d;
    private Map<String, List<Map>> listenerMap = new HashMap<String, List<Map>>();

    public EventHandler(Director d) {
        this.d = d;
    }

    @Override
    public void run() {
        while(d.getIsReady4Input()) {
            //消费事件
            Event e = EventQueue.getInstance().deQueue();
            if(e != null && listenerMap.containsKey(e.getEventType())) {
                for(Map<String, Object> invokeTarget: listenerMap.get(e.getEventType())) {
                    if(invokeTarget.containsKey("callback")) {
                        IEventCallback cb = (IEventCallback)invokeTarget.get("callback");
                        if(cb != null) cb.handleEvent(e.getEventType(), e.getEventInfo());
                    } else {
                        Object targetObject = invokeTarget.get("target");
                        Class clz = (Class)invokeTarget.get("class");
                        String method = (String)invokeTarget.get("method");
                        try {
                            Method m = clz.getMethod(method, Map.class);
                            m.invoke(targetObject, e.getEventInfo());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            }
        }
    }


    /**
     * 增加消息监听，反射方式
     * @param eventType
     * @param listener
     * @param clz
     * @param methodName
     */
    public void addEventListener(String eventType, Object listener, Class clz, String methodName) {
        if(!listenerMap.containsKey(eventType)) {
            List<Map> list = new ArrayList<>();
            listenerMap.put(eventType, list);
        }
        List<Map> list = listenerMap.get(eventType);
        Map<String, Object> listenerInfo = new HashMap<String, Object>();
        listenerInfo.put("target", listener);
        listenerInfo.put("class", clz);
        listenerInfo.put("method", methodName);
        list.add(listenerInfo);
    }

    /**
     * 消息监听，回调方式
     * @param eventType
     * @param callback
     */
    public void addEventCallback(String eventType, IEventCallback callback) {
        if(!listenerMap.containsKey(eventType)) {
            List<Map> list = new ArrayList<>();
            listenerMap.put(eventType, list);
        }
        List<Map> list = listenerMap.get(eventType);
        Map<String, Object> listenerInfo = new HashMap<String, Object>();
        listenerInfo.put("callback", callback);
        list.add(listenerInfo);
    }

    /**
     * 移除监听
     * @param eventType
     * @param listener
     * @param methodName
     */
    public void removeEventListner(String eventType, Object listener, String methodName) {

    }
}
