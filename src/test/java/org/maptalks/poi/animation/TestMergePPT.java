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

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.*;
import org.junit.Test;
import org.maptalks.poi.animation.in.FlyIn;
import org.maptalks.poi.animation.util.MergeConfig;
import org.maptalks.poi.animation.util.SeegooPoiTool;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.maptalks.poi.animation.util.Slide;
import org.maptalks.poi.animation.util.MergeConfig;



/**
 * Created by wangjun on 16/2/18.
 */
public class TestMergePPT {
    
    @Test
    public void testMerge() throws Exception {
        String pptFilePath = this.getClass().getResource("/ppt").getPath()+"/old.pptx";
        XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(pptFilePath));
        String outputPath = this.getClass().getResource("/ppt").getPath()+"/merge.pptx";

        XSLFSlide slide = xmlSlideShow.createSlide();
        XSLFSlide slide1 = xmlSlideShow.createSlide();
        ArrayList<Slide> slides = new ArrayList<Slide>();
        slides.add(new Slide(1, slide));
        slides.add(new Slide(3, slide1));
        int[] removeIds = new int[] {0,2,3};
        MergeConfig config = new MergeConfig(removeIds, slides);
        SeegooPoiTool.mergeSlidesToPPT(xmlSlideShow, config, outputPath);
    }


}