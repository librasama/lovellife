package com.aucompany.ll;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by zoe on 2015/6/27.
 * 仿游戏界面。消息消费中心
 */
public class GameScreen implements Runnable{

    private Director d;
    public GameScreen(Director d) {
        this.d = d;
    }
    @Override
    public void run() {
        while(d.onScreenListener) {
            //消费事件
            Event e = EventQueue.getInstance().deQueue();
            if(e != null && listenerMap.containsKey(e.eventType)) {
                for(Map<String, Object> invokeTarget: listenerMap.get(e.eventType)) {
                    Object targetObject = invokeTarget.get("target");
                    Class clz = (Class)invokeTarget.get("class");
                    String method = (String)invokeTarget.get("method");
                    try {
                        Method m = clz.getMethod(method, Map.class);
                        m.invoke(targetObject, e.eventInfo);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }
        }
    }

    private Map<String, List<Map>> listenerMap = new HashMap<String, List<Map>>();

    /**
     * 增加消息监听
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

    public void removeEventListner(String eventType, Object listener, String methodName) {

    }

}
