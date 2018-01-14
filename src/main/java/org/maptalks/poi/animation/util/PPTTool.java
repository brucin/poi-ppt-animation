package org.maptalks.poi.animation.util;

import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xslf.util.PPTX2PNG;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 2016/12/11.
 */
public class PPTTool {

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
        out.flush();
        out.close();
        xmlSlideShow.close();
    }

    public static ArrayList<String> getThumbnailFromPPT(String pptFilePath, String outputFolder, int picWidth, int picHeight) throws Exception{
        ArrayList<String> imageUrlList = new ArrayList<String>();
        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(pptFilePath));
        List<XSLFSlide> slides = xmlSlideShow.getSlides();
        File savePath = new File(outputFolder);
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        Dimension screenSize = xmlSlideShow.getPageSize();
        if(picWidth == 0) {
            picWidth = screenSize.width;
        }
        if(picHeight == 0) {
            picHeight = screenSize.height;
        }
        for (int i = 0; i < slides.size(); i++) {
            XSLFSlide slide = slides.get(i);
            List<XSLFShape> shapes = slide.getShapes();
            for (int j = 0; j < shapes.size(); j++) {
                XSLFShape shape = shapes.get(j);
                if(shape instanceof XSLFGroupShape) {
                    XSLFGroupShape group = (XSLFGroupShape)shape;
                    group.setInteriorAnchor(group.getAnchor());
                }
            }
            BufferedImage img = new BufferedImage(picWidth, picHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fill(new Rectangle2D.Float(0, 0, picWidth, picHeight));
            slide.draw(graphics);
            String picName = i + ".png";
            FileOutputStream out = new FileOutputStream(outputFolder + picName);
            javax.imageio.ImageIO.write(img, "png", out);
            out.flush();
            out.close();
            imageUrlList.add(picName);
        }
        xmlSlideShow.close();
        return imageUrlList;
    }
}
