package com.aucompany.ll.live.event;

import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 * 事件回调
 */
public interface IEventCallback {

    public void handleEvent(String eventType, Map<String, Object> eventInfo);
}
