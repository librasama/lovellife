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
    boolean isPlay = false;         //正在播放
    long pauseTimestamp;            //暂停时间戳
    PlayerData player;
    Song song;
    Tune tune;
    boolean onScreenListener = true;    //监听屏幕输入

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
     * 模拟界面
     */
    private void simulate() {
        GameScreen screen = new GameScreen(this);
        screen.addEventListener("TouchIn", this, Director.class, "onScreenTouchIn");
        screen.addEventListener("TouchOut", this, Director.class, "onScreenTouchIn");
        new Thread(new SimulatePlay(this)).start();
        new Thread(screen).start();
    }

    /**
     * 播放
     */
    public void play() {
        showSongInfo(song);//显示乐曲基本信息
        loadMask(); //加载过场--蒙版
        tune = loadTune(id);//加载歌曲
        tune.playerData = player;
        cancleMask(); //撤销蒙版
        isPlay = true;
        tune.startTimestamp = new Date().getTime();
        Thread song = new Thread(tune);//播放歌曲
        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!tune.isEnd()) {
                    try{
                        //刷新时间条。每次都按时间计算，不能累加。有暂停的问题
                        System.out.println("更新时间条中");
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
        });
        //乐曲结束
        song.start();
        process.start();
        simulate();
        try {
            song.join();
            process.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        onScreenListener = false;
        System.out.println("父进程呵呵呵");
        //演奏成功失败蒙版
        //撤销演奏页面
        //显示分数统计页面
    }

    /**
     * 屏幕点击事件
     * param time
     * param x
     * param y
     */
    public void onScreenTouchIn(Map<String, Object> e) {
        long time = new Long(String.valueOf(e.get("time")));
        //获取点击到的音轨
        Track track = getTrack(new Float(e.get("x").toString()), new Float(e.get("y").toString()));
        if(track.curBeat != null && track.curBeat.peek() != null) {
            //判断当前，是否在最前一个beat的范围守备内
            track.curBeat.peek().tryHit(time);
        }
    }

    /**
     * 屏幕点击离开事件
     */
    public void onScreenTouchOut(Map<String, Object> e) {

    }

    /**
     * 暂停按钮
     */
    public void onPauseBtnClick() {
        if(isPlay) {
            pauseTimestamp = new Date().getTime();
            //按钮更换为play
            //音乐暂停
            //动画暂停
        } else {
            //恢复音乐
            //恢复动画
            //按钮更换为pause
            tune.startTimestamp += new Date().getTime() - pauseTimestamp;
        }
    }


    //来一款测试用的
    public Tune loadTune(int id) {
        return Tune.forTest();
    }


    protected Song loadSong(int id) {
        System.out.println("loading Song whose id is "+id+"...");
        return new Song();
    }
    protected PlayerData loadPlayerData() {
        PlayerData player = new PlayerData();
        System.out.println("loading Player Data...");
        player.hitScore = 300;
        return player;
    }

    protected void showSongInfo(Song song) {

    }

    /**
     * 坐标获取最靠近的音轨
     * @param x
     * @param y
     * @return
     */
    private Track getTrack(float x, float y) {
        for(Track t : tune.tracks) {
            if(t.playBtn.inScope(x, y)) {
                return t;
            }
        }
        return null;
    }

    private void loadBgScene(int level) {
        System.out.println("loading Background Scene...");
    }

    private void loadScoreBar(Song song) {
        System.out.println("loadingScoreBar...");
    }
    private void loadPowerBar(PlayerData player) {
        System.out.println("loading Powerbar...");
    }

    private void loadMask() {
        System.out.println("lloadMask...");
    }
    private void cancleMask(){
        System.out.println("cancleMask...");
    }
    public void loadPlayBtn(boolean isRace) {
        if(isRace) {
            //disable掉暂停按钮
        }
        System.out.println("loading Play button...");
    }
}
