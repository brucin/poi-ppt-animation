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

import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;
import org.maptalks.poi.shape.TextBox;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * add by brucin 2017/11/14
 */
public class TestLineString {

    @Test
    public void test() throws Exception {
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

        // add lineString
//        XSLFConnectorShape lineShape = slide.createConnector();
//        XSLFFreeformShape lineString = slide.createFreeform();
//        lineString.setAnchor(new Rectangle2D.Double(200, 150, 1000, 600));
//        Double[][] points = {
//                {10.0, 200.0},
//                {380.0, 20.0},
//                {700.0, 210.0},
//                {900.0, 300.0},
//                {536.0, 100.0},
//                {10.0, 10.0},
//                {980.0, 160.0},
//                {213.0, 600.0},
//                {760.0, 560.0},
//                {310.0, 150.0}
//        };
//        lineString.setPath(this.getPath(points));
//        lineString.setLineColor(Color.BLUE);
//        lineString.setFillColor(Color.CYAN);
//        lineString.setLineWidth(6.0);
//        lineString.setStrokeStyle(StrokeStyle.LineDash.DOT);


//        XSLFFreeformShape shape1 = slide.createFreeform();
//        Path2D.Double path1 = new Path2D.Double(new Rectangle2D.Double(150, 150, 300, 300));
//        path1.append(new Ellipse2D.Double(200, 200, 100, 50), false);
//        shape1.setPath(path1);

        String savePath = this.getClass().getResource("/ppt").getPath();
        FileOutputStream output = new FileOutputStream(savePath+"/lineString.pptx");
        pptx.write(output);
        output.close();
    }

    public GeneralPath getPath(Double[][] points) {
        GeneralPath path = new GeneralPath();
        int length = points.length, index = 0;
        if(points != null && length > 0) {
            for (Double[] point : points) {
                if(index == 0) {
                    path.moveTo(point[0], point[1]);
                } else {
                    path.lineTo(point[0], point[1]);
                }
                if (index == length -1) {
                    path.closePath();
                }
            }
        }
        return path;
    }
   
}