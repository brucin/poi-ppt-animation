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
import org.junit.Test;
import org.maptalks.poi.animation.util.MergeConfig;
import org.maptalks.poi.animation.util.SeegooPoiTool;
import org.maptalks.poi.animation.util.Slide;

import java.io.FileInputStream;
import java.util.ArrayList;


/**
 * Created by wangjun on 16/2/18.
 */
public class TestGetThumbnailFromPPT {
    
    @Test
    public void testMerge() throws Exception {
        String pptFilePath = this.getClass().getResource("/ppt").getPath()+"/old.pptx";
        String outputPath = this.getClass().getResource("/ppt").getPath()+"/slides/";

        ArrayList<String> picUrlList = SeegooPoiTool.getThumbnailFromPPT(pptFilePath, outputPath, null, 0, 0);
        for (int i = 0; i < picUrlList.size(); i++) {
            System.out.println(picUrlList.get(i));
        }
    }


}