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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 16/2/18.
 */
public class TesTextWithWrapWidth {

    @Test
    public void testTextWithWrapWidth() throws Exception {
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
        symbol.setLineColor(new Color(205,205,205));
        symbol.setTextWeight("normal");
        symbol.setTextStyle("normal");
        symbol.setHorizontal("middle");
        symbol.setVertical("middle");
        symbol.setFillOpacity(0.0);
        Double[] padding = {0.0, 0.0, 0.0, 0.0};
        symbol.setPadding(padding);
        symbol.setLineSpacing(1.0);

        System.out.println(symbol.toString());

//        List<String> rows = new ArrayList<String>();
//        rows.add("第一行：测试文本。");
//        rows.add("第二行：测试。");
//        rows.add("Third: 3.");
        String text = "B1F商业面积5040㎡：天虹超市\n1F商业面积6081㎡ ：化妆品、黄金珠宝、钟表、鞋类皮具\n2F商业面积6826㎡ ：女装\n3F商业面积7714㎡ ：男装、运动休闲\n4F商业面积6000㎡ ：万达影院、餐饮";
        XSLFTextBox textBox = new TextBox(text, 14.0, 41.0, 250.0, 136.0, symbol)
                .convertTo(slide.createTextBox());

        String savePath = this.getClass().getResource("/ppt").getPath();
        FileOutputStream output = new FileOutputStream(savePath+"/vector_texts.pptx");
        pptx.write(output);
        output.close();
    }
   
}