package com.aucompany.ll.live;

import com.aucompany.ll.live.event.Event;
import com.aucompany.ll.live.event.EventQueue;
import com.aucompany.ll.live.event.IEventCallback;
import com.aucompany.ll.live.graph.Button;
import com.aucompany.ll.live.graph.Label;
import com.aucompany.ll.live.graph.ProgressBar;
import com.aucompany.ll.live.graph.ScoreBar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 * 舞台
 */
public class Stage implements Runnable {
    String bgImage; //背景图
    String bgCover; //背景点缀
    ProgressBar powerbar; //体力条
    ProgressBar scorebar;
    ProgressBar sc;
    Label scoreTip;
    Label comboTip;
    Label mNote;
    Button pause;

    private Director d;

    public Stage(Director d) {
        this.d = d;
    }
    public void initStage() {
        //初始化各个元素，放置到正确的位置
        //为元素添加事件监听
        initEventListener();
    }

    private void initEventListener() {
        addEventCallback("Score", new IEventCallback() {
            @Override
            public void handleEvent(Map<String, Object> eventInfo) {
                System.out.print("sb");
            }
        });
    }


    @Override
    public void run() {
        while(d.onScreenListener) {
            //消费事件
            Event e = EventQueue.getInstance().deQueue();
            if(e != null && listenerMap.containsKey(e.getEventType())) {
                for(Map<String, Object> invokeTarget: listenerMap.get(e.getEventType())) {
                    if(invokeTarget.containsKey("callback")) {
                        IEventCallback cb = (IEventCallback)invokeTarget.get("callback");
                        if(cb != null) cb.handleEvent(e.getEventInfo());
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

    private Map<String, List<Map>> listenerMap = new HashMap<String, List<Map>>();

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

    public void removeEventListner(String eventType, Object listener, String methodName) {

    }
}
