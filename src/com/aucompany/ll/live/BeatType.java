package com.aucompany.ll.live;

/**
 * Created by zoe on 2015/6/27.
 * 打击乐符的种类
 */
public enum BeatType {
    Basic,
    Double,
    Star,
    Activity,
    Long;
    public static boolean isLong(BeatType t) {
        switch (t) {
            case Long: return true;
        }
        return false;
    }
}
