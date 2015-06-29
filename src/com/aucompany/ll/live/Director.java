package com.aucompany.ll.live;

import com.aucompany.ll.live.event.EventHandler;
import com.aucompany.ll.test.SimulatePlay;
import com.aucompany.ll.test.TestSuit;

import java.util.Date;
import java.util.List;

/**
 * Created by zoe on 2015/6/26.
 * 导演
 */
public class Director {

    int difficulty;                         //难度
    int id;                                 //曲子id
    boolean isRace = false;                 //是否是竞赛模式（不允许暂停）
    int hits;                               //总击打数
    boolean isReady4Input = true;           //监听屏幕输入

    Song song;                              //乐曲信息
    Tune tune;                              //乐谱信息
    Stage stage;                            //舞台信息
    PlayerData player;                      //玩家信息
    PlayerStatus status;                    //玩家状态值
    EventHandler eventHandler;              //事件响应
    GameEvalue gameEvalue;                  //游戏结果评级

    boolean isPlay = false;                 //正在播放
    long pauseTimestamp;                    //暂停时间戳
    long startTimestamp;                    //开始时间戳
    public int currentSecond;               //当前播放秒数

    public Director(int songId) {
        this.id = songId;
    }

    /**
     * 初始化
     */
    public void init(){
        player = loadPlayerData();          //加载玩家数据
        song = loadSong(id);                //乐曲基本信息
        this.eventHandler = new EventHandler(this); //事件分发中心
        stage = new Stage(this);                    //舞台
        tune = new Tune(this);                      //乐谱
        gameEvalue = new GameEvalue(this);          //评级
    }

    /**
     * 播放
     */
    public void play() {
        showSongInfo(song);             //显示乐曲基本信息
        loadMask();                     //加载过场--蒙版
        tune = loadTune(id);            //加载歌曲
        hits = tune.initTuneAndTracks(id);
        cancleMask();                   //撤销蒙版
        Thread event = new Thread(eventHandler);
        Thread tt = new Thread(tune); //播放歌曲
        Thread play = new Thread(new SimulatePlay(this));   //模拟输入
        event.start();
        tt.start();
        play.start();
        try {
            tt.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        isReady4Input = false;
        System.out.println("Live完成呵呵呵");
        //演奏成功失败蒙版
        gameEvalue.evalueGame();
        //撤销演奏页面
        //显示分数统计页面
    }

    /**
     * 暂停
     */
    public void onPause() {
        if(isPlay) {
            pauseTimestamp = new Date().getTime();
        } else {
            this.startTimestamp += new Date().getTime() - pauseTimestamp;
        }
    }

    /**
     * 正在演奏
     * @return
     */
    public boolean getIsPlay() {
        return isPlay;
    }

    /**
     * 乐谱结束
     * @return
     */
    public boolean isEnd(){
        return (new Date().getTime() > startTimestamp + tune.lastTime) || (status.power < 0);
    }

    /**
     * 获取起始时间
     * @return
     */
    public long getStartTimestamp(){
        return this.startTimestamp;
    }
    public Tune getTune() {
        return tune;
    }
    public List<Track> getTracks() {
        return tune.getTracks();
    }
    public boolean getIsReady4Input() {
        return isReady4Input;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
    /**
     * TODO 加载乐曲
     */
    protected Tune loadTune(int id) {
        return TestSuit.getTune(this);
    }
    /**
     * TODO 加载基本信息
     */

    protected Song loadSong(int id) {
        System.out.println("加载歌曲信息： " + id + "...");
        return new Song();
    }
    /**
     * TODO 加载玩家信息
     */
    protected PlayerData loadPlayerData() {
        System.out.println("加载玩家信息：...");
        PlayerData data = TestSuit.getPlayerData();
        status = new PlayerStatus(data.getPower());
        return data;
    }


    protected void showSongInfo(Song song) {

    }

    public int getSScore() {
        return song.getSScore();
    }

    public int getPower() {
        return player.getPower();
    }

    private void loadMask() {
        System.out.println("loadMask...");
    }
    private void cancleMask(){
        System.out.println("cancleMask...");
    }

}

