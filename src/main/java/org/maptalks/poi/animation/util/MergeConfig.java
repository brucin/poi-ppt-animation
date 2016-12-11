package org.maptalks.poi.animation.util;

import java.util.ArrayList;

/**
 * Created by wangjun on 2016/12/11.
 */
public class MergeConfig {

    private int[] removeIds;

    private ArrayList<Slide> slides = new ArrayList<Slide>();

    public int[] getRemoveIds() {
        return removeIds;
    }

    public ArrayList<Slide> getSlides() {
        return slides;
    }

    public void setRemoveIds(int[] removeIds) {
        this.removeIds = removeIds;
    }

    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }

    public MergeConfig(int[] removeIds, ArrayList<Slide> slides) {
        this.removeIds = removeIds;
        this.slides = slides;
    }
}
