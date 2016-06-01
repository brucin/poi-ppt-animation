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
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.Test;
import org.maptalks.poi.animation.in.FlyIn;
import org.maptalks.poi.animation.out.FlyOut;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 16/2/18.
 */
public class TestXSLFAnimationGroup {
    
    @Test
    public void testAnimationGroup() throws Exception {
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
        String nodeType = "clickEffect";
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

            if(i>1) {
                nodeType = "afterEffect";
                XSLFAnimationType animationType = new FlyIn(picShape, directions[i], nodeType);
                animationTypes.get(1).addChild(animationType);
            } else {
                XSLFAnimationType animationType = new FlyIn(picShape, directions[i], nodeType);
                animationTypes.add(animationType);
            }
        }
        animation.addAnimationToSlide(slide, animationTypes);
        FileOutputStream out = new FileOutputStream(pathStr+"/group.pptx");
        pptx.write(out);
        out.close();   
    }
   
}