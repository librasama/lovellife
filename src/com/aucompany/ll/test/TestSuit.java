package com.aucompany.ll.test;

import com.aucompany.ll.*;

import java.util.*;

/**
 * Created by zoe on 2015/6/27.
 */
public class TestSuit {

    private static int tuneLastTime = 10000;    //10秒
    private static int tuneAnimateTime = 1000;  //1秒
    private static Tune t ;

    public static Song getSong() {
        Song s = new Song();
        return  s;
    }

    public static Tune getTune() {
        t = new Tune();
        t.id = 1;
        t.lastTime = tuneLastTime;
        t.animateTime = tuneAnimateTime;
        t.judgeInterval = 20;
        return t;
    }
    public static List<Track> getTrackList() {
        List<Track> list = new ArrayList<>();
        list.add(getTrack(1, 100, 100));
        list.add(getTrack(2, 200, 100));
        list.add(getTrack(3, 300, 100));
        return list;
    }

    private static Track getTrack(int pos, float x, float y) {
        Track t2 = new Track(pos);
        PlaySinger singer2 = new PlaySinger(t2, x, y);
        t2.playBtn = singer2 ;
        System.out.println("++++++++++++++设定Track"+pos+"节奏++++++++++++");
        t2.setBeats(getBeats());
        System.out.println();
        return t2;
    }
    public static Queue<Beat> getBeats() {
        Queue q = new LinkedList<>();
        int count = new Random().nextInt(9)+1;
        int interval = t.lastTime / count;
        for(int i=0;i<count;i++) {
            long rightTime = new Long(interval*(i)+ new Random(200).nextInt(200));
            System.out.print(rightTime+", ");
            q.add(new Beat(t, BeatType.Basic, rightTime));
        }
        return q;
    }

    public PlayerData getPlayerData() {
        PlayerData p = new PlayerData();
        return p;
    }
}
