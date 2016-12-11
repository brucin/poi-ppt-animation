package org.maptalks.poi.animation.util;

import org.apache.poi.xslf.usermodel.XSLFSlide;

/**
 * Created by wangjun on 2016/12/11.
 */
public class Slide {


    private int order = 0;

    private XSLFSlide xslfSlide;

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public XSLFSlide getXslfSlide() {
        return xslfSlide;
    }

    public void setXslfSlide(XSLFSlide xslfSlide) {
        this.xslfSlide = xslfSlide;
    }

    public Slide(int order, XSLFSlide xslfSlide) {
        this.order = order;
        this.xslfSlide = xslfSlide;
    }
}
