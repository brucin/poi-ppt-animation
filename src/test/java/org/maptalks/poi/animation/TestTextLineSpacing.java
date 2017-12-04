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
import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;
import org.maptalks.poi.shape.TextBox;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestTextLineSpacing {

    @Test
    public void testTextLineSpacing() throws Exception {
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

        //add label
        TextBoxSymbol symbol = new TextBoxSymbol();
        symbol.setWordWrap(true);
        symbol.setLineColor(new Color(204, 204, 204));
        symbol.setLineOpacity(1.0);
        symbol.setTextWeight("normal");
        symbol.setTextStyle("normal");
        symbol.setHorizontal("left");
        symbol.setVertical("middle");
        symbol.setFillColor(new Color(47, 55, 62));
        symbol.setFillOpacity(1.0);
        symbol.setFontColor(Color.WHITE);
        symbol.setTextOpacity(1.0);
        symbol.setFontSize(14.0);
        Double[] padding = {12.0, 8.0};
        symbol.setPadding(padding);
        symbol.setLineSpacing(1.0);

        System.out.println(symbol.toString());
        String text = new String("交通变化：嘉闵高架建成，分流莘松路车流，\n                  拉动北部嘉定、松江板块至南部闵行地区。\n人口变化：无变化。\n商业变化：\n       2016.9亚繁亚乐城开业\n       2017.12彩生活时代广场开业。\n商圈结论：商圈稳定，容量1家。\n开发策略：结合社区内最大聚客点：彩生活时代广场布局。\n资产策略：莘松店---单一卖场，聚客能力弱，不在商圈中心，建议移店。");
        XSLFTextBox textBox = new TextBox(text, 72.5, 54.5, 477.0, 203.0, symbol)
                .convertTo(slide.createTextBox());

        String savePath = this.getClass().getResource("/ppt").getPath();
        FileOutputStream output = new FileOutputStream(savePath+"/text_line_spacing.pptx");
        pptx.write(output);
        output.close();
    }
   
}