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

import org.apache.poi.sl.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestTextAndTable {
    
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
        XSLFPictureData pictureData = pptx.addPicture(content, org.apache.poi.sl.usermodel.PictureData.PictureType.PNG);
        XSLFPictureShape picShape = slide.createPicture(pictureData);
        picShape.setLineWidth(0);
        picShape.setAnchor(new Rectangle2D.Double(0, 0, width, height));
        out.close();

        //add lable
        XSLFTextBox shape = slide.createTextBox();
        Rectangle2D textAnchor = new Rectangle2D.Double(14, 41, 91, 36);
        shape.setAnchor(textAnchor);

        shape.setLineColor(new Color(205,205,205));
        shape.setFillColor(new Color(105,155,252));
        shape.setVerticalAlignment(VerticalAlignment.MIDDLE);
        XSLFTextRun textRun = shape.setText("文本标签");
        textRun.setFontColor(new Color(132,108,226));
        textRun.setFontSize(18.);
        textRun.setFontFamily("microsoft yahei");

        //add table
        XSLFTable table = slide.createTable();
        table.setAnchor(new Rectangle2D.Double(550, 280, 300, 83));
//        XSLFTableRow header = table.addRow();
        String[] headers = {"序号","表头","表头","表头"};
        this.addRow(headers, table, new Color(58,63,69), new Color(255,255,255));

        String[] first = {"1","A","B","C"};
        this.addRow(first, table, new Color(255,0,0), new Color(0,0,0));

        String[] second = {"2","甲","乙","丙"};
        this.addRow(second, table, new Color(250,252,72), new Color(0,0,0));

        String[] third = {"3","测试","测试","测试"};
        this.addRow(third, table, new Color(0,0,255), new Color(255,255,255));

        String savePath = this.getClass().getResource("/ppt").getPath();
        FileOutputStream output = new FileOutputStream(savePath+"/text_and_table.pptx");
        pptx.write(output);
        output.close();
    }

    private void addRow(String[] array, XSLFTable table, Color fill, Color fontColor) {
        XSLFTableRow row = table.addRow();
        row.setHeight(12.);
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            XSLFTableCell cell = row.addCell();
            cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell.setHorizontalCentered(true);
            cell.setPlaceholder(Placeholder.TITLE);
            cell.setTextPlaceholder(TextShape.TextPlaceholder.CENTER_TITLE);

            cell.setBorderColor(TableCell.BorderEdge.top, new Color(205,205,205));
            cell.setBorderColor(TableCell.BorderEdge.left, new Color(205,205,205));
            cell.setBorderColor(TableCell.BorderEdge.bottom, new Color(205,205,205));
            cell.setBorderColor(TableCell.BorderEdge.right, new Color(205,205,205));
            cell.setFillColor(fill);

            XSLFTextRun text = cell.setText(str);
            text.setFontColor(fontColor);
            text.setFontSize(16.);
            text.setFontFamily("microsoft yahei");
        }
    }
   
}