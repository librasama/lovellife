package com.aucompany.ll.live.graph;

import com.aucompany.ll.live.event.IEventCallback;

import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 */
public class ScoreBar extends ProgressBar implements IEventCallback {

    private double CPercent = 0.45;
    private double BPercent = 0.60;
    private double APercent = 0.75;
    private double SPercent = 0.90;

    private double[] percents = {CPercent, BPercent, APercent, SPercent};
    private String[] colors = {"blue", "green", "yellow", "silver", "white"};
    public ScoreBar(int total, String label) {
        super(total, label);
    }


    @Override
    public void handleEvent(String eventType, Map<String, Object> eventInfo) {
        int score = new Integer(eventInfo.get("Score").toString());
        double percent = addProgress(score);
        int i =0;
        for(;i<percents.length;) {
            if(percent<percents[i]) {
                break;
            } else {
                i++;
            }
        }
        setColors(colors[i]);
    }
}
