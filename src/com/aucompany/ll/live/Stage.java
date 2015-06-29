package com.aucompany.ll.live;

import com.aucompany.ll.live.event.EventHandler;
import com.aucompany.ll.live.event.IEventCallback;
import com.aucompany.ll.live.graph.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/28.
 * 舞台
 */
public class Stage {

    private Director d;                      //导演类
    private EventHandler eventHandler;      //事件处理

    String bgImage;                 //背景图
    String bgCover;                 //背景点缀
    PowerBar powerbar;              //体力条
    ScoreBar scorebar;              //分数条
    TimeBar timebar;                //时间条
    Label hitTip;                   //打击结果提示（Great、Perfect等）
    Label comboTip;                 //连击结果提示（5 Combo）
    Label mNote;                    //乐符提示（变红警告）
    Button pauseBtn;                //暂停按钮
    List<ControlButton> ctlBtns;    //玩家按钮

    boolean inited = false;         //是否已经初始化

    public Stage(Director d) {
        this.d = d;
        this.eventHandler = d.getEventHandler();
        init(d.difficulty, d.getSScore(), d.getPower(), d.isRace);
    }

    public void init(int level, int maxScore, int initPower, boolean isRace) {
        if(!inited) {
            //初始化各个元素，放置到正确的位置
            loadBgScene(level);             //加载背景场景
            loadScoreBar(maxScore);         //加载分数条
            loadPowerBar(initPower);        //加载体力条
            loadPlayBtn(isRace);            //加载暂停按钮
            timebar = new TimeBar((this.d));
            //为元素添加事件监听
            initEventListener();
            inited = true;
        }
    }

    private void initEventListener() {
        eventHandler.addEventCallback("Start", new IEventCallback() {
            @Override
            public void handleEvent(String eventType, Map<String, Object> eventInfo) {
                new Thread(timebar).start();
            }
        });
        eventHandler.addEventCallback("TouchIn", new TouchInHandler());
        eventHandler.addEventCallback("TouchOut", new TouchOutHandler());
        eventHandler.addEventCallback("Pause", new PauseHandler());
        eventHandler.addEventCallback("Score", scorebar);
        eventHandler.addEventCallback("Hit", hitTip);
        eventHandler.addEventCallback("Power", powerbar);
        eventHandler.addEventCallback("Combo", comboTip);
        eventHandler.addEventCallback("Combo", new IEventCallback() {
            @Override
            public void handleEvent(String eventType, Map<String, Object> eventInfo) {
                //满多少换背景
            }
        });
        eventHandler.addEventCallback("Power", new IEventCallback() {
            @Override
            public void handleEvent(String eventType, Map<String, Object> eventInfo) {
                //体力低于多少时，MusicNote变红
            }
        });

    }
    public void loadPlayBtn(boolean isRace) {
        if(isRace) {
            //disable掉暂停按钮
        }
        System.out.println("loading Play button...");
    }
    private void loadBgScene(int level) {
        System.out.println("loading Background Scene...");
    }

    private void loadScoreBar(int maxScore) {
        scorebar = new ScoreBar(maxScore, "分数条");
        System.out.println("loadingScoreBar...");
    }
    private void loadPowerBar(int power) {

        System.out.println("loading Powerbar...");
    }

    /**
     * 屏幕点击事件
     * param time
     * param x
     * param y
     */
    private class TouchInHandler implements IEventCallback {
        public void handleEvent(String eventType, Map<String, Object> eventInfo) {
            long time = new Long(String.valueOf(eventInfo.get("time")));
            //获取点击到的音轨
            Track track = getTrack(new Float(eventInfo.get("x").toString()), new Float(eventInfo.get("y").toString()));
            //发动技能
            track.tryDriveSkill();
            if(track.curBeat != null && track.curBeat.peek() != null) {
                //判断当前Beat打击结果
                d.gameEvalue.judgeBeat(track.curBeat.peek(), time);
            }

        }
    }

    private class TouchOutHandler implements IEventCallback {
        public void handleEvent(String eventType, Map<String, Object> eventInfo) {

        }
    }
    /**
     * 暂停/播放事件处理
     */
    private class PauseHandler implements IEventCallback {
        public void handleEvent(String eventType, Map<String, Object> eventInfo) {
            d.onPause();
            if(d.getIsPlay()) {
                //按钮更换为play
                //音乐暂停
                //动画暂停
            } else {
                //恢复音乐
                //恢复动画
                //按钮更换为pause
            }
        }

    }

    /**
     * 坐标获取最靠近的音轨
     * @param x
     * @param y
     * @return
     */
    private Track getTrack(float x, float y) {
        for(Track t : d.getTracks()) {
            if(t.inScope(x, y)) {
                return t;
            }
        }
        return null;
    }

}
