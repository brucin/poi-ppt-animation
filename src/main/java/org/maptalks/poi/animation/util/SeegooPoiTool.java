package org.maptalks.poi.animation.util;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 2016/12/11.
 */
public class SeegooPoiTool {

    public static void mergeSlidesToPPT(XMLSlideShow xmlSlideShow, MergeConfig config, String outputPath) throws Exception {
        if(outputPath == null || outputPath.length() == 0) return;
        int[] removeIds = config.getRemoveIds();
        if(removeIds != null) {
            for (int i = 0; i < removeIds.length; i++) {
                xmlSlideShow.removeSlide(removeIds[i]);
            }
        }
        ArrayList<Slide> slides = config.getSlides();
        if (slides.size() > 0) {
            for (int i = 0; i < slides.size(); i++) {
                Slide slide = slides.get(i);
                xmlSlideShow.setSlideOrder(slide.getXslfSlide(), slide.getOrder());
            }
        }
        FileOutputStream out = new FileOutputStream(outputPath);
        xmlSlideShow.write(out);
    }
}
