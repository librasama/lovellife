package com.aucompany.ll;

import java.util.Date;
import java.util.Map;

/**
 * Created by zoe on 2015/6/26.
 * 导演
 */
public class Director {

    int level;                      //难度
    int id;                         //曲子id
    boolean isRace = false;         //是否是竞赛
    boolean isPlay = false;          //正在播放
    PlayerData player;
    Song song;
    Tune tune;

    public Director(int songId) {
        this.id = songId;
    }
    /**
     * 初始化
     */
    public void init(){
        player = loadPlayerData();//加载玩家数据
        song = loadSong(id);//乐曲基本信息
        loadBgScene(level);//加载背景场景
        loadScoreBar(song);//加载分数条
        loadPowerBar(player);//加载体力条
        loadPlayBtn(isRace);//加载暂停按钮
    }

    /**
     * 屏幕点击事件
     */
    public void onScreenTouchIn(Map<String, Float> e) {
        long time = new Long(String.valueOf(e.get("time")));
        //获取点击到的音轨
        Track track = getTrack(e.get("x"), e.get("y"));
        if(track != null && track.curBeat != null) {
            //判断当前，是否在最前一个beat的范围守备内
            track.curBeat.tryHit(time);
        }
    }

    /**
     * 屏幕点击离开事件
     */
    public void onScreenTouchOut() {

    }

    private void loadBgScene(int level) {

    }

    private void loadScoreBar(Song song) {

    }
    private void loadPowerBar(PlayerData player) {

    }

    private void loadMask() {

    }
    private void cancleMask(){

    }

    //来一款测试用的
    private Tune loadTune(int id) {
        return Tune.forTest();
    }


    /**
     * 播放
     */
    public void play() {
        showSongInfo(song);//显示乐曲基本信息
        loadMask(); //加载过场--蒙版
        tune = loadTune(id);//加载歌曲
        cancleMask(); //撤销蒙版
        isPlay = true;
        new Thread(tune).run();//播放歌曲
        //乐曲结束
        while(true) {
            //刷新时间条
            //this.sleep(1000);
        }
    }
    public void loadPlayBtn(boolean isRace) {
        if(isRace) {
            //disable掉暂停按钮
        }
    }
    //暂停按钮
    public void onPauseBtnClick() {
        if(isPlay == true) {

        }
    }

    private Song loadSong(int id) {
        return new Song();
    }

    private void showSongInfo(Song song) {}

    private PlayerData loadPlayerData() {
        PlayerData player = new PlayerData();

        return player;
    }

    /**
     * 坐标获取最靠近的音轨
     * @param x
     * @param y
     * @return
     */
    private Track getTrack(float x, float y) {
        return null;
    }
}
