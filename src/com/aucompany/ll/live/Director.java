package com.aucompany.ll.live;

import com.aucompany.ll.live.event.EventHandler;
import com.aucompany.ll.test.SimulatePlay;
import com.aucompany.ll.test.TestSuit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/26.
 * 导演
 */
public class Director {

    int difficulty;                         //难度
    int id;                                 //曲子id
    boolean isRace = false;                 //是否是竞赛模式（不允许暂停）
    Song song;                              //乐曲信息
    boolean isReady4Input = true;           //监听屏幕输入
    Tune tune;                              //乐谱信息
    Stage stage;                            //舞台信息
    PlayerData player;                      //玩家信息
    PlayerStatus status;                    //玩家状态值
    EventHandler eventHandler;              //事件响应
    GameEvalue gameEvalue;                  //游戏结果评级

    boolean isPlay = false;                 //正在播放
    long pauseTimestamp;                    //暂停时间戳

    public Director(int songId) {
        this.id = songId;
    }

    /**
     * 初始化
     */
    public void init(){
        player = loadPlayerData();//加载玩家数据
        song = loadSong(id);//乐曲基本信息
        this.eventHandler = new EventHandler(this);
        stage = new Stage(this);
    }

    /**
     * 模拟界面
     */
    private void simulate() {

    }

    /**
     * 播放
     */
    public void play() {
        showSongInfo(song);//显示乐曲基本信息
        loadMask();         //加载过场--蒙版
        tune = loadTune(id);//加载歌曲
        tune.playerData = player;
        tune.startTimestamp = new Date().getTime();
        tune.initTuneAndTracks(id);
        cancleMask();       //撤销蒙版
        Thread song = new Thread(tune);//播放歌曲
        song.start();
        new Thread(new SimulatePlay(this)).start();
        try {
            song.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        isReady4Input = false;
        System.out.println("父进程呵呵呵");
        //演奏成功失败蒙版
        PlayerStatus status = tune.playerStatus;
        if(status.hit < tune.hits) {
            System.out.println("演奏失败~~~Niconiconi~");
        } else {
            System.out.println("演奏成功！！！Makimakima！");
        }
        //撤销演奏页面
        //显示分数统计页面
        System.out.println("结果发表："+status.toString());

    }

    /**
     * 暂停
     */
    public void onPause() {
        if(isPlay) {
            pauseTimestamp = new Date().getTime();
        } else {
            tune.startTimestamp += new Date().getTime() - pauseTimestamp;
        }
    }

    public boolean getIsPlay() {
        return isPlay;
    }

    /**
     * TODO 加载乐曲
     */
    protected Tune loadTune(int id) {
        return TestSuit.getTune();
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
        PlayerData player = new PlayerData();
        System.out.println("加载玩家信息：...");
        player.hitScore = 300;
        return player;
    }

    protected void showSongInfo(Song song) {

    }
    public Tune getTune() {
        return tune;
    }
    public List<Track> getTracks() {
        return tune.getTracks();
    }


    private void loadMask() {
        System.out.println("lloadMask...");
    }
    private void cancleMask(){
        System.out.println("cancleMask...");
    }


    public boolean getIsReady4Input() {
        return isReady4Input;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

}

