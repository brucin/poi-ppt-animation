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

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.Test;
import org.maptalks.poi.animation.in.FlyIn;
import org.maptalks.poi.animation.out.FlyOut;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestXSLFAnimationShape {
    
    @Test
    public void testAnimationSlide() throws Exception {
        XMLSlideShow pptx = new XMLSlideShow();
        XSLFSlide slide = pptx.createSlide();
        Dimension pageSize = pptx.getPageSize();
        Rectangle2D rectangle = new Rectangle2D.Double(0,0,pageSize.getWidth(),pageSize.getHeight());
        List<XSLFAnimationType> animationTypes = new ArrayList<XSLFAnimationType>();
        XSLFAnimation animation = new XSLFAnimation();
        String[] directions = new String[5];
        directions[0] = MoveDirection.BOTTOM;
        directions[1] = MoveDirection.LEFT;
        directions[2] = MoveDirection.TOP;
        directions[3] = MoveDirection.RIGHT;
        directions[4] = MoveDirection.BOTTOM;
        String pathStr = this.getClass().getResource("/images").getPath();
        for (int i = 0; i < 3; i++) {
            String fileName = pathStr+"/"+(i+1)+".png";
            File downloadeFile = new File(fileName);
            BufferedInputStream in = new  BufferedInputStream(new FileInputStream(downloadeFile));
            ByteArrayOutputStream out=new ByteArrayOutputStream(1024);
            int size=0;
            byte[] temp=new byte[1024];
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();
            byte[] content=out.toByteArray();
            XSLFPictureData pictureData = pptx.addPicture(content, org.apache.poi.sl.usermodel.PictureData.PictureType.PNG);
            XSLFPictureShape picShape=slide.createPicture(pictureData);
            picShape.setAnchor(rectangle);
            picShape.setLineWidth(0);
            XSLFAnimationType animationType = new FlyIn(picShape, directions[i]);
            animationTypes.add(animationType);

            XSLFAnimationType animationOutType = new FlyOut(picShape, directions[i]);

            animationTypes.add(animationOutType);
        }
        slide = animation.addAnimationToSlide(slide, animationTypes); 
        FileOutputStream out = new FileOutputStream(pathStr+"/temp.pptx");
        pptx.write(out);
        out.close();   
    }
   
}