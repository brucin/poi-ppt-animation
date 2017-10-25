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

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.junit.Test;
import org.maptalks.poi.animation.in.ZoomIn;
import org.maptalks.poi.animation.out.ZoomOut;
import org.maptalks.poi.shape.TextBox;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TestVectorZoomOut {
    
    @Test
    public void testVectorZoomOut() throws Exception {
        XMLSlideShow pptx = new XMLSlideShow();
        XSLFSlide slide = pptx.createSlide();

        List<XSLFAnimationType> animationTypes = new ArrayList<XSLFAnimationType>();
        XSLFAnimation animation = new XSLFAnimation();

        TextBoxSymbol symbol = new TextBoxSymbol();
        XSLFTextBox textBox = new TextBox("文本标签", 100.0, 160.0,91.0, 36.0, symbol)
                .convertTo(slide.createTextBox());
        XSLFAnimationType zoomOutAnimation = new ZoomOut(textBox, MoveDirection.TOP);
        animationTypes.add(zoomOutAnimation);

        animation.addAnimationToSlide(slide, animationTypes);

        String pathStr = this.getClass().getResource("/ppt").getPath();
        FileOutputStream out = new FileOutputStream(pathStr+"/zoom_out.pptx");
        pptx.write(out);
        out.close();   
    }
   
}