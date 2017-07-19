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
import org.maptalks.poi.animation.util.MergeConfig;
import org.maptalks.poi.animation.util.PPTTool;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.maptalks.poi.animation.util.Slide;


/**
 * Created by wangjun on 16/2/18.
 */
public class TestMergePPT {
    
    @Test
    public void testMerge() throws Exception {
        String pptFilePath = this.getClass().getResource("/ppt").getPath()+"/old.pptx";
        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(pptFilePath));
        String outputPath = this.getClass().getResource("/ppt").getPath()+"/merge.pptx";
        XSLFSlideMaster defaultMaster = xmlSlideShow.getSlideMasters().get(0);
        for(XSLFSlideLayout layout : defaultMaster.getSlideLayouts()){
            System.out.println(layout.getType());
            System.out.println(layout.getName());
            System.out.println("########################");
        }

        XSLFSlide slide = xmlSlideShow.createSlide(defaultMaster.getLayout(SlideLayout.TWO_TX_TWO_OBJ));
//        XSLFSlide slide1 = xmlSlideShow.createSlide();
        ArrayList<Slide> slides = new ArrayList<Slide>();
        slides.add(new Slide(1, slide));
//        slides.add(new Slide(3, slide1));
        int[] removeIds = new int[] {1};
        MergeConfig config = new MergeConfig(removeIds, slides);
        PPTTool.mergeSlidesToPPT(xmlSlideShow, config, outputPath);
    }


}