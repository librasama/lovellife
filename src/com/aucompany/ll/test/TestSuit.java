package com.aucompany.ll.test;

import com.aucompany.ll.player.Card;
import com.aucompany.ll.player.PlayerCard;
import com.aucompany.ll.live.*;

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
        ControlButton singer2 = new ControlButton(t2, x, y);
        t2.playBtn = singer2 ;
        System.out.println("++++++++++++++设定Track" + pos + "节奏++++++++++++");
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
        PlayerData p = new PlayerData(1);
        return p;
    }


    public static Card getCard(int id) {
//        Property property, int maxLevel, int[] coolArr,int[] pureArr,int[] smileArr, int[] powerLevel
        Card c = new Card(Card.Property.Cool, 3, new int[]{300, 200, 100}, new int[]{150, 100, 50},
                new int[]{150, 100, 50}, new int[]{1,2});
        Skill s = new Skill();
        c.setSkill(s);
        return c;
    }

    public static Map<String, Integer> getPlayerCard(int id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("kizuno", 30);
        map.put("cardid", 1);//模板id
        return map;
    }

    public static List<PlayerCard> getCardSet() {
        List<PlayerCard> list = new ArrayList<>();
        PlayerCard c1 = new PlayerCard(1,1);
        PlayerCard c2 = new PlayerCard(1,2);
        list.add(c1);
        list.add(c2);
        return list;
    }
}
