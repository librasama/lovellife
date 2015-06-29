package com.aucompany.ll.live;

import com.aucompany.ll.live.event.Event;
import com.aucompany.ll.live.event.EventBus;
import com.aucompany.ll.live.event.IEventCallback;
import com.aucompany.ll.player.PlayerCard;
import com.aucompany.ll.test.TestSuit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zoe on 2015/6/26.
 * 曲子
 */
public class Tune implements Runnable {
    Director d;
    GameEvalue evalue;
    public int judgeInterval = 20;       //最小判定周期
    public int lastTime;                 //持续时间
    int hits;                            //总击打数
    public int id;                       //乐曲id
    public long animateTime;             //动画播放的时间
    //判定时间偏差值（ms）
    long badOffset = 500;
    long goodOffset = 300;
    long greatOffset = 200;
    long perfectOffset = 100;

    List<Track> tracks;                 //音轨们
    List<PlayerCard> cards;             //卡牌们

    public Tune(Director director) {
        this.d = director;
        this.evalue = this.d.gameEvalue;
    }

    public int initTuneAndTracks(int id) {
        tracks = findTracks(id);
        cards = d.player.getPlayerCards();
        int i = 0;
        for(Track t : tracks) {
            t.setPlayCard(cards.get(i));
            t.animateTime = animateTime;
            t.delayTime = badOffset;
            for(Beat b : t.getBeats()) {
                hits++;
            }
            i++;
        }
        return hits;
    }

    public void run() {
        EventBus.getInstance().add(new Event("Start", null));
        d.startTimestamp = new Date().getTime();
        do {
            try{Thread.sleep(judgeInterval);} catch (Exception e) {System.err.println(e);}
            for(Track track : tracks) {
                track.doTrackWork(d.status, d.getStartTimestamp());
                Beat b = track.curBeat.peek();
                if(b != null && (b.hitlevel != HitLevel.None)) {
                    System.out.print("！！！判定！！！");
                    // 结束滑行动画
                    track.removeBeatCircle(b);
                    evalue.evaluateHit(b);
                    //释放乐符
                    track.curBeat.poll();
                }
            }
            //联合评价
            evalue.evalueRound();
            // 播放结果动画
            hitResultAnimate();
            //乐曲结束或者体力为0
        } while(!d.isEnd());
        System.out.println("音乐结束");
    }

    /**
     * 寻找乐谱对应的音轨们
     */
    private List<Track> findTracks(int id) {
        //TODO 真正查库表
        List<Track> list = TestSuit.getTrackList();
        System.out.println("【Tune】找到id=" + id + "音轨们了~，共"+list.size()+"条音轨");
        return list;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    //播放背景音乐
    public void playMusic(){
        //TODO
        System.out.println("开始播放音乐~");
    }

    public int getHits() {
        return hits;
    }

    /**
     * 结束滑行动画
     */
    protected void removeBeatCircle() {}
    /**
     * 播放结果动画
     */
    protected void hitResultAnimate() {}
}
