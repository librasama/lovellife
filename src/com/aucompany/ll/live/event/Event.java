package com.aucompany.ll.live.event;

import java.util.Map;

/**
 * Created by zoe on 2015/6/27.
 */
public class Event {
    String eventType;
    Map<String, Object> eventInfo ;
    public Event(String type,  Map<String, Object> info) {
        this.eventType = type;
        this.eventInfo = info;
    }

    public Map<String, Object> getEventInfo() {
        return eventInfo;
    }

    public String getEventType() {
        return eventType;
    }
}
