package com.aucompany.ll.live;

import com.aucompany.ll.player.PlayerCard;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zoe on 2015/6/26.
 * 音轨
 */
public class Track {
    Queue<Beat> beats;                           //节奏队列
    Queue<Beat> curBeat = new LinkedList<>();    //当前屏幕上的节奏
    public ControlButton controlBtn;             //玩家按钮
    long animateTime;                            //动画播放的时间
    long delayTime;                              //最大延迟时间
    int pos;                                     //第几条音轨
    float x;
    float y;

    public Track(int pos, float x, float y) {
        this.pos = pos;
        this.x = x;
        this.y = y;
    }

    public void setPlayCard(PlayerCard card) {
        this.controlBtn = new ControlButton(card, x, y);
    }

    /**
     * 每个节拍轮询
     * @param startTimestamp
     */
    public void doTrackWork(PlayerStatus status, long startTimestamp) {
        long current = new Date().getTime();
        //到了下个乐符开始的时间没
        if(beats.peek()!= null && (beats.peek().rightTime - animateTime <= current-startTimestamp)) {
            //如果是，取乐符，判断类型，播放滑行动画。
            addBeatCircle(beats.peek().type);
            curBeat.add(beats.poll());
        }
        //到了当前最先的乐符结束时间没
        if((curBeat.peek()!= null) && (curBeat.peek().rightTime + delayTime < current-startTimestamp)) {
            System.out.println("Beat 超时！信息："+curBeat.peek().getRightTime() );
            removeBeatCircle(curBeat.peek());
            curBeat.peek().timeout = true;
            curBeat.peek().hitlevel = HitLevel.Miss;
        }
        //技能发动/结束
        controlBtn.checkSkill(status);
    }

    /**
     * 尝试发动技能
     */
    public void tryDriveSkill() {
        if(controlBtn.onSkill) {
            controlBtn.skill.successSkill();
            controlBtn.endSkillAnime();
        }
    }

    /**
     * 结束最前Beat动画
     */
    public void removeBeatCircle(Beat beat) {
        //如果是长击，同时移除成对的音符

    }

    /**
     * 播放Beat动画
     * @param type
     */
    public void addBeatCircle(BeatType type) {

    }


    public int getPos() {
        return pos;
    }

    public boolean inScope(float x, float y) {
        return controlBtn.inScope(x, y);
    }

    /**
     * TODO 暂时先这么写，方便测试
     * @param beats
     */
    public void setBeats(Queue<Beat> beats) {
        this.beats = beats;
    }

    public Queue<Beat> getBeats() {
        return beats;
    }

    private void log1(){
        /*            System.out.println("=========信息开始=========");
            System.out.println("Beat 出现在屏幕上");
            System.out.println("正确节拍："+beats.peek().rightTime );
            System.out.println("动画时间：" + animateTime);
            System.out.println("当前时间："+current);
            System.out.println("起始时间："+startTimestamp );
            System.out.println("=========信息结束=========");*/
    }
    private void log2(){
    /*System.out.println("正确节拍："+curBeat.peek().rightTime );
            System.out.println("延时信息：" + delayTime);
            System.out.println("当前时间："+current);
            System.out.println("起始时间："+startTimestamp );
            System.out.println("=========信息开始=========");
            System.out.println("=========信息结束=========");*/
    }
}
