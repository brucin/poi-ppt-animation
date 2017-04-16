package org.maptalks.poi.animation.in;

import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.xmlbeans.XmlException;
import org.maptalks.poi.animation.MoveDirection;
import org.maptalks.poi.animation.XSLFAnimation;
import org.maptalks.poi.animation.XSLFAnimationType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by wangjun on 16/2/18.
 */
public class ZoomIn extends XSLFAnimationType {

    public ZoomIn(XSLFSimpleShape shape, String moveDirection) {
        this.shape = shape;
        this.moveDirection = moveDirection;
        this.nodeType = "clickEffect";
        this.presetSubtype = "16";
    }

    public ZoomIn(XSLFSimpleShape shape, String moveDirection, String nodeType) {
        this.shape = shape;
        this.moveDirection = moveDirection;
        this.nodeType = nodeType;
        this.presetSubtype = "16";
    }

    public Element toXml(Document document, XSLFAnimation animation) throws XmlException, ParserConfigurationException {
        String spid = Integer.toString(this.shape.getShapeId());
        
        Element element4 = document.createElementNS(XSLFAnimation.NS,"p:par");
        Element element5 = document.createElementNS(XSLFAnimation.NS,"p:cTn");
        element5.setAttribute("id", animation.getElementIdStr());
        element5.setAttribute("fill", "hold");

        Element element6 = document.createElementNS(XSLFAnimation.NS,"p:stCondLst");
        Element element7 = document.createElementNS(XSLFAnimation.NS,"p:cond");
        element7.setAttribute("delay", "0");
        element6.appendChild(element7);
        element5.appendChild(element6);
        element4.appendChild(element5);
        
        /**
         * <p:childTnLst>
              <p:par>
                <p:cTn id="5" presetID="2" presetClass="entr" presetSubtype="16" fill="hold" nodeType="clickEffect">
                  <p:stCondLst>
                    <p:cond delay="0"/>
                  </p:stCondLst>
                  <p:childTnLst>
         */
        Element childList2 = document.createElementNS(XSLFAnimation.NS,"p:childTnLst");
        Element element21 = document.createElementNS(XSLFAnimation.NS,"p:par");
        Element element22 = document.createElementNS(XSLFAnimation.NS,"p:cTn");
        element22.setAttribute("id", animation.getElementIdStr());
        element22.setAttribute("presetID", "53");
        element22.setAttribute("presetClass", "entr");
        element22.setAttribute("presetSubtype", this.presetSubtype);
        element22.setAttribute("fill", "hold");
        element22.setAttribute("nodeType", this.nodeType);
        
        Element element23 = document.createElementNS(XSLFAnimation.NS,"p:stCondLst");
        Element element24 = document.createElementNS(XSLFAnimation.NS,"p:cond");
        element24.setAttribute("delay", "0");
        element23.appendChild(element24);
        element22.appendChild(element23);
        element21.appendChild(element22);
        childList2.appendChild(element21);
        
        Element childList3 = document.createElementNS(XSLFAnimation.NS,"p:childTnLst");
        Element pSet = document.createElementNS(XSLFAnimation.NS,"p:set");
        Element pcBhvr = document.createElementNS(XSLFAnimation.NS,"p:cBhvr");
        Element element31 = document.createElementNS(XSLFAnimation.NS,"p:cTn");
        element31.setAttribute("id", animation.getElementIdStr());
        element31.setAttribute("fill", "hold");
        element31.setAttribute("dur", "1");
        Element element32 = document.createElementNS(XSLFAnimation.NS,"p:stCondLst");
        Element element33 = document.createElementNS(XSLFAnimation.NS,"p:cond");
        element33.setAttribute("delay", "500");
        element32.appendChild(element33);
        element31.appendChild(element32);
        pcBhvr.appendChild(element31);
        

        Element pTgtEl = document.createElementNS(XSLFAnimation.NS,"p:tgtEl");
        Element pSpTgt = document.createElementNS(XSLFAnimation.NS,"p:spTgt");
        pSpTgt.setAttribute("spid", spid);//shapeId
        pTgtEl.appendChild(pSpTgt);
        pcBhvr.appendChild(pTgtEl);
        
        Element attrNameLst = document.createElementNS(XSLFAnimation.NS,"p:attrNameLst");
        Element attrName = document.createElementNS(XSLFAnimation.NS,"p:attrName");
        Text text = document.createTextNode("style.visibility");
        attrName.appendChild(text);
        attrNameLst.appendChild(attrName);
        pcBhvr.appendChild(attrNameLst);
        
        Element pTo = document.createElementNS(XSLFAnimation.NS,"p:to");
        Element pStrVal = document.createElementNS(XSLFAnimation.NS,"p:strVal");
        pStrVal.setAttribute("val", "visible");
        pTo.appendChild(pStrVal);
        pSet.appendChild(pcBhvr);
        pSet.appendChild(pTo);
        childList3.appendChild(pSet);
        
        childList3.appendChild(createAnimationXml(animation,document, spid, "ppt_w"));
        childList3.appendChild(createAnimationXml(animation,document, spid, "ppt_h"));
        element22.appendChild(childList3);
        element5.appendChild(childList2);
//        firstElement.appendChild(element1);
//        root.appendChild(firstElement);
        return element4;
    }
    
    public Element createAnimationXml(XSLFAnimation animation,Document document, String spid, String locTag) {
        Element panim = document.createElementNS(XSLFAnimation.NS,"p:anim");
        panim.setAttribute("calcmode", "lin");
        panim.setAttribute("valueType", "num");
        
        Element pcBhvr = document.createElementNS(XSLFAnimation.NS,"p:cBhvr");
        pcBhvr.setAttribute("additive", "base");
        Element pcTn = document.createElementNS(XSLFAnimation.NS,"p:cTn");
        pcTn.setAttribute("id", animation.getElementIdStr());
        pcTn.setAttribute("dur", "1500");
        pcTn.setAttribute("fill", "hold");
        pcBhvr.appendChild(pcTn);
        
        Element pTgtEl = document.createElementNS(XSLFAnimation.NS,"p:tgtEl");
        Element pSpTgt = document.createElementNS(XSLFAnimation.NS,"p:spTgt");
        pSpTgt.setAttribute("spid", spid);//shapeId
        pTgtEl.appendChild(pSpTgt);
        pcBhvr.appendChild(pTgtEl);
        
        Element attrNameLst = document.createElementNS(XSLFAnimation.NS,"p:attrNameLst");
        Element attrName = document.createElementNS(XSLFAnimation.NS,"p:attrName");
        Text text = document.createTextNode(locTag);
        attrName.appendChild(text);
        attrNameLst.appendChild(attrName);
        pcBhvr.appendChild(attrNameLst);
        panim.appendChild(pcBhvr);
        
        Element ptavLst = document.createElementNS(XSLFAnimation.NS,"p:tavLst");
        String startLoc = this.getStartLocation(locTag);
        String endLoc = this.getEndLocation(locTag);
        ptavLst.appendChild(createShapeLocation(document, "0", startLoc));
        ptavLst.appendChild(createShapeLocation(document, "100000", endLoc));
        panim.appendChild(ptavLst);
        return panim;
    }
    
    private String getStartLocation(String locTag) {
        return "0";
    }
    
    private String getEndLocation(String locTag) {
        String result = new String("");
        if(locTag.equals("ppt_w")) {
            result = "#ppt_w";
        } else {
            result = "#ppt_h";
        }
        return result;
    }
    
    /**
     * <p:tav tm="0">
          <p:val>
            <p:strVal val="#ppt_w"/>
          </p:val>
        </p:tav>
     *
     * @param tm
     * @param location
     * @return
     */
    public Element createShapeLocation(Document doc, String tm, String location) {
        Element ptav = doc.createElementNS(XSLFAnimation.NS,"p:tav");
        ptav.setAttribute("tm", tm);
        Element pval = doc.createElementNS(XSLFAnimation.NS,"p:val");
        Element pstrVal = doc.createElementNS(XSLFAnimation.NS,"p:strVal");
        pstrVal.setAttribute("val", location);
        pval.appendChild(pstrVal);
        ptav.appendChild(pval);
        return ptav;
    }

}
