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

    public static Tune getTune(Director d) {
        t = new Tune(d);
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
        Track t2 = new Track(pos, x, y);
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

    /**
     * 获取卡牌模板
     * @param id
     * @return
     */
    public static Card getCard(int id) {
        Card c = new Card(Card.Property.Cool, 3, new int[]{300, 200, 100}, new int[]{150, 100, 50},
                new int[]{150, 100, 50}, new int[]{1,2});
        //Combo为2，技能分数加10000点
        Skill s = new Skill(Skill.DriveType.Combo, 0.9, 2, Skill.Type.Score, 10000, "Woo~", "www");
        c.setSkill(s);
        return c;
    }

    public static List<PlayerCard> getCardSet(int playerId, int cardSetid) {
        List<PlayerCard> list = new ArrayList<>();
        PlayerCard c1 = new PlayerCard(1,1);
        PlayerCard c2 = new PlayerCard(1,2);
        PlayerCard c3 = new PlayerCard(1,3);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list;
    }

    public static PlayerData getPlayerData() {
        PlayerData player = new PlayerData(1,1,Card.Property.Cool,100, 200);
        return player;
    }

    public static Map<String, Integer> getPlayerCard(int id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("kizuno", 30);
        map.put("cardid", 1);//模板id
        return map;
    }

}
