package com.aucompany.ll;

import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by zoe on 2015/6/26.
 */
public class Game {
    public static void main(String[] args) {
        Director director = new Director();
    }
}

/**
 * 导演
 */
public class Director {
    public Director(int songId) {
        this.id = songId;
    }
    int level;//难度
    int id; //曲子id
    boolean isRace = false;//是否是竞赛
    PlayerData player;
    Song song;
    Tune tune;
    public void init(){
        player = loadPlayerData();//加载玩家数据
        song = loadSong(id);//选曲
        loadBgScene(level);//加载背景场景
        loadScoreBar(song);//加载分数条
        loadPowerBar(player);//加载体力条
        loadPlayBtn(isRace);//加载暂停按钮
        tune = loadTune(level);//加载歌曲
    }

    public void play() {
        tune.play();
        while(tune.end()) {
            //刷新时间条
            this.sleep(1000);
        }
    }
    public void loadPlayBtn(boolean isRace) {
        if(isRace) {
            //disable掉
        }
    }
    //暂停按钮
    public void onPauseBtnClick() {
        if(playStatus == 1) {

        }
    }

    private Song loadSong(int id) {
        return new Song();
    }

    private PlayerData loadPlayerData() {
        PlayerData player = new PlayerData();

        return player;
    }
}

/**
 * 玩家数值
 */
public class PlayerData {
    int score;
    int power;
    List<PlaySinger> singers ;
}

/**
 * 玩家状态
 */
public class PlayerStatus {
    int combo;
    int perfect;
    float time;
    int power;
}

/**
 * 玩家按钮
 */
public class PlaySinger {
    int pos;//位置
    Track track; //音轨
    Skill skill; //技能
    PlayerStatus st;//玩家状态
    boolean onSkill; //是否正在发动技能
    Beat currBeat;//当前节奏点

    //触摸
    public void onTouchIn() {
        //更新血量条
        //更新体力条
    }
    //离开
    public void onTouchOut() {
        //长按Beat
        if(currBeat.isLastBeat()) {
            //更新血量条
            //更新体力条
        }
    }
    //轮询更新状态
    public void updateStatus() {
        detechBeat();
        tagSkill();
    }

    public void detechBeat() {
        Beat beat = track.beats.peek();
        currBeat = beat;
    }
    //标记是否发动技能
    public void tagSkill(){
        boolean enableDrive = DriveSkillType.drive(st, skill) && new Random(1).nextFloat() >= skill.rate;
        if(enableDrive) {
            skill.startTime = new Date().getTime();
            onSkill = true;
        } else if(onSkill && skill.timeout()) {
            onSkill = false;
        }
    }
}
/**
 * 难度（一般设定）
 */
public enum Level {
    Easy, Noraml, Hard, Expert;
    public float getLastTime(Tune tune, int l) {
        switch (l) {
            //查找设定
            case Easy: return 3.0;
            case Noraml: return 2.0;
            case Hard: return 1.5;
            case Expert: return 1.0;
        }
        default:return 2.0;
    }
}

/**
 * 选曲
 */
public class Song {
    String title;
    String lastTime;
    int level;//难度

    int CScore ;
    int BScore;
    int AScore ;
    int SScore ;

}
/**
 * 曲子
 */
public class Tune {
   float MinimumBeat;//最小节拍
   int lastTime;//持续时间


    List<Track> tracks = findTracks();
    public void play() {
        playMusic();
        for(Track track : tracks) {
            new Thread(track).run();
        }
    }
    //播放背景音乐
    public void playMusic(){
    }

}

/**
 * 音轨
 */
public class Track implements Runnable {
    Queue<Beat> beats;
    public PlaySinger btn;
    @Override
    public void run() {
        for(Beat beat:beats ){
            getCircleType(wave, track); //不同类型的圈圈
            track.freeBeatCircle();//动画
            track.detechCrash(); //碰撞检测
        }
    }
}


/**
 * 节奏
 */
public class Beat {
    float startOffset;
    float lastTime;
    MusicNote note;
    public boolean isLastBeat() {
        return MusicNoteType.isLong(note.type);
    }
}


/**
 * 乐符
 */
public class MusicNote {
    MusicNoteType type;
    int loss;
    int bloodBuff;
    String perfectAudio;
    String greatAudio;
    String goodAudio;
    String badAudio;
}

/**
 * 乐符类型
 */
public enum MusicNoteType {
    None, Basic, Double, Star, Activity, Long;
    public static boolean isLong(MusicNoteType t) {
        switch (t) {
            case Long: return true;
        }
        return false;
    }
}
/**
 * 技能
 */
public class Skill {
    DriveSkillType driveType;//发动类型
    float rate;//发动概率
    SkillType skillType; //技能类型
    int skillVal;//技能数值
    int testCount;//已测试次数
    String audio;//发动技能声音
    String image;//卡面
    float lastTime;//技能持续时间
    float startTime;//发动开始时间
    public boolean timeout(){
        return (new Date().getTime() - startTime > lastTime);
    }
}

/**
 * 发动技能条件类型
 */
public enum DriveSkillType {
    Inteval,Perfect, Combo;
    public static boolean drive(PlayerStatus st, Skill sk) {
        switch (sk.skillType) {
            case Inteval:
                return st.time >= sk.skillVal;
            case Perfect:
                return st.perfect >= sk.skillVal;
            case Combo:
                return st.combo >= sk.skillVal;
            default:
                return true;
        }

    }
}
