package com.aucompany.ll.live;

import com.aucompany.ll.player.Card;

/**
 * Created by zoe on 2015/6/26.
 * 选曲
 */
public class Song {
    int id;                 //id
    int level;              //难度
    String title;           //标题
    String albumCover;      //专辑封面
    String lastTime;        //持续时间
    Card.Property property; //属性

    int SScore ;            //SScore分数线

    public int getSScore() {
        return SScore;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public void setSScore(int SScore) {
        this.SScore = SScore;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
