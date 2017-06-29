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

import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;
import org.maptalks.poi.animation.in.ZoomIn;
import org.maptalks.poi.animation.out.FlyOut;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestAllVector {
    
    @Test
    public void testAllVector() throws Exception {
        XMLSlideShow ppt = new XMLSlideShow();

        String pathStr = this.getClass().getResource("/images").getPath();
        XSLFSlide blankSlide = ppt.createSlide();
        // blank slide
        // there can be multiple masters each referencing a number of layouts
        // for demonstration purposes we use the first (default) slide master
        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);

        // title slide
        XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE);
        // fill the placeholders
        XSLFSlide slide1 = ppt.createSlide(titleLayout);
        XSLFTextShape title1 = slide1.getPlaceholder(0);
        title1.setText("First Title");

        // title and content
        XSLFSlideLayout titleBodyLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
        XSLFSlide slide2 = ppt.createSlide(titleBodyLayout);

        XSLFTextShape title2 = slide2.getPlaceholder(0);
        title2.setText("Second Title");

        XSLFTextShape body2 = slide2.getPlaceholder(1);
        body2.clearText(); // unset any existing text
        body2.addNewTextParagraph().addNewTextRun().setText("First paragraph");
        body2.addNewTextParagraph().addNewTextRun().setText("Second paragraph");
        body2.addNewTextParagraph().addNewTextRun().setText("Third paragraph");
        System.out.println("pathStr:"+pathStr);
        FileOutputStream out = new FileOutputStream(pathStr+"/vector.pptx");
        ppt.write(out);
        out.close();   
    }
   
}