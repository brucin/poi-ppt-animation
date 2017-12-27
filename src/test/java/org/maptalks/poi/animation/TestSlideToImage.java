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
import org.maptalks.poi.animation.util.PPTTool;
import org.maptalks.poi.shape.Table;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestSlideToImage {

    @Test
    public void testSlideToImage() throws Exception {
        String pptPath = "/Users/wangjun/git/GitHub/poi-ppt-animation/src/test/resources/ppt/ppt2image/group_shape.pptx";
        String outputPath = "/Users/wangjun/git/GitHub/poi-ppt-animation/src/test/resources/ppt/ppt2image/group/";
        int width = 0, height = 0;
        PPTTool.getThumbnailFromPPT(pptPath, outputPath, width, height);
    }
   
}