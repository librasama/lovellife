package com.aucompany.ll;

/**
 * Created by zoe on 2015/6/26.
 *  难度（一般设定）
 */
public enum Level {
    Easy, Noraml, Hard, Expert;
    public float getLastTime(Tune tune, int l) {
        switch (l) {
            //查找设定
            case Easy: return 3;
            case Noraml: return 2;
            case Hard: return new Float(1.5);
            case Expert: return 1;
        }
        return 2;
    }
}
