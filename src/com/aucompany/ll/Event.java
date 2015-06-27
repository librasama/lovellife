package com.aucompany.ll;

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
}
