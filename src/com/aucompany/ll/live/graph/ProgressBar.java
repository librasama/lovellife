package com.aucompany.ll.live.graph;

/**
 * Created by zoe on 2015/6/27.
 * 进度条
 */
public class ProgressBar {

    public float x;
    public float y;
    private int total = 1; //总长度
    private int current;//当前进度
    private double percent;//进度百分比
    private String label; //显示文字
    private String colors; //进度条颜色

    public ProgressBar(int total, String label) {
        this.total = total;
        this.label = label;
    }
    public double setProgress(int newProgress) {
        if(newProgress <= total) {
            current = newProgress;
        } else {
            current = total;
        }
        percent = current / total;
        System.out.println(label+"进度已经置为："+newProgress);
        return percent;
    }

    public double getPercent() {
        return percent;
    }

    public double addProgress(int increment) {
        return setProgress(current+increment);
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ProgressBar{" +
                "total=" + total +
                ", current=" + current +
                ", percent=" + percent +
                ", label='" + label + '\'' +
                ", colors='" + colors + '\'' +
                '}';
    }
}
