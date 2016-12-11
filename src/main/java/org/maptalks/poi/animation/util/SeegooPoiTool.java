package org.maptalks.poi.animation.util;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

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

    public static ArrayList<String> getThumbnailFromPPT(String pptFilePath, String outputFolder, String saveFolder, int picWidth, int picHeight) throws Exception{
        ArrayList<String> imageUrlList = new ArrayList<String>();
        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(pptFilePath));
        List<XSLFSlide> slides = xmlSlideShow.getSlides();
        long createTime = System.currentTimeMillis();
        if(saveFolder == null || saveFolder.length() == 0) {
            saveFolder = createTime+"";
        }
        File savePath = new File(outputFolder + saveFolder);
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        String picPath = createTime + File.separator;
        Dimension screenSize = xmlSlideShow.getPageSize();
        if(picWidth == 0) {
            picWidth = screenSize.width;
        }
        if(picHeight == 0) {
            picHeight = screenSize.height;
        }
        for (int i = 0; i < slides.size(); i++) {
            XSLFSlide slide = slides.get(i);
            BufferedImage img = new BufferedImage(picWidth, picHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fill(new Rectangle2D.Float(0, 0, picWidth, picHeight));
            slide.draw(graphics);
            String picName = "slide_"+i+".png";
            FileOutputStream out = new FileOutputStream(outputFolder + picPath + picName);
            javax.imageio.ImageIO.write(img, "png", out);
            out.close();
            imageUrlList.add(picPath+picName);
        }
        return imageUrlList;
    }
}
