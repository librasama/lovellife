package com.aucompany.ll.live.graph;

import com.aucompany.ll.live.event.IEventCallback;

import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 */
public class PowerBar extends ProgressBar implements IEventCallback{

    public PowerBar(int total, String label) {
        super(total, label);
    }

    @Override
    public void handleEvent(String eventType, Map<String, Object> eventInfo) {

    }
}
