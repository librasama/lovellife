package com.aucompany.ll;

/**
 * Created by zoe on 2015/6/26.
 * 发动技能条件类型
 */
public class DriveSkillType {
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
