package com.aucompany.ll;

import java.util.*;

/**
 * Created by zoe on 2015/6/26.
 */
public class Game {
    public static void main(String[] args) {
        Director director = new Director(1);
        director.init();
        director.play();
    }
}

