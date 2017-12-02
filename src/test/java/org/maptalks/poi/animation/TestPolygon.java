/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.maptalks.poi.animation;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;
import org.maptalks.poi.shape.symbol.StrokeSymbol;
import org.maptalks.poi.shape.Polygon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * add by brucin 2017/11/14
 */
public class TestPolygon {

    @Test
    public void testPoylgon() throws Exception {
        XMLSlideShow pptx = new XMLSlideShow();
        String imagePath = this.getClass().getResource("/images/text").getPath();
        XSLFSlide slide = pptx.createSlide();
        //add tile
        String tilePath = imagePath+"/tile.png";
        File tileFile = new File(tilePath);
        BufferedImage bufferedImage = ImageIO.read(tileFile);
        double width = bufferedImage.getWidth(), height = bufferedImage.getHeight();
        Dimension screenSize = new Dimension(1024, 768);
        screenSize.setSize(width, height);
        pptx.setPageSize(screenSize);

        BufferedInputStream in = new  BufferedInputStream(new FileInputStream(tileFile));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        int size=0;
        byte[] temp=new byte[1024];
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        XSLFPictureData pictureData = pptx.addPicture(content, PictureData.PictureType.PNG);
        XSLFPictureShape picShape = slide.createPicture(pictureData);
        picShape.setLineWidth(0);
        picShape.setAnchor(new Rectangle2D.Double(0, 0, width, height));
        out.close();

        // add polygon
        Double[][] points = {
                {10.0, 200.0},
//                {380.0, 20.0},
//                {700.0, 210.0},
//                {900.0, 300.0},
//                {536.0, 100.0},
//                {10.0, 10.0},
//                {980.0, 160.0},
//                {213.0, 600.0},
                {760.0, 560.0},
                {310.0, 150.0}
        };
        StrokeSymbol symbol = new StrokeSymbol();

//        symbol.setLineDash(StrokeStyle.LineDash.DASH);
//        symbol.setHeadDecoration(LineDecoration.DecorationShape.ARROW);
//        symbol.setHeadWidth(LineDecoration.DecorationSize.LARGE);
//        symbol.setHeadLength(LineDecoration.DecorationSize.LARGE);
//        symbol.setTailDecoration(LineDecoration.DecorationShape.ARROW);
//        symbol.setTailWidth(LineDecoration.DecorationSize.LARGE);
        symbol.setLineColor(Color.BLUE);
        symbol.setFillColor(Color.CYAN);
        symbol.setLineWidth(16.0);

        XSLFFreeformShape polygon = new Polygon(points, symbol).convertTo(slide.createFreeform());

        String savePath = this.getClass().getResource("/ppt").getPath();
        FileOutputStream output = new FileOutputStream(savePath+"/vector_polygon.pptx");
        pptx.write(output);
        output.close();
    }
   
}