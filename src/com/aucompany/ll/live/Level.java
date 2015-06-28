package com.aucompany.ll.live;

/**
 * Created by zoe on 2015/6/26.
 *  难度（一般设定）
 */
public enum Level {
    Easy, Noraml, Hard, Expert;
    public float getLastTime(Tune tune, int l) {
        switch (l) {
            //查找设定
            case 1: return 3;
            case 2: return 2;
            case 3: return new Float(1.5);
            case 4: return 1;
        }
        return 2;
    }
}
