package org.maptalks.poi.animation;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by wangjun on 16/2/18.
 */
public class XSLFAnimation {
    
    private int elementId = 1;

    public final static String NS = new String("http://schemas.openxmlformats.org/presentationml/2006/main");
    
    public XSLFAnimation() {
        elementId = 1;
    }

    public XSLFSlide addAnimationToSlide(XSLFSlide slide, List<XSLFAnimationType> animationTypes) throws ParserConfigurationException, DOMException, XmlException {
        XmlObject xmlObject = slide.getXmlObject();
        Node node = xmlObject.getDomNode();
        Document document = node.getOwnerDocument();
        Element root = this.createAnimationXml(document, animationTypes);
        node.appendChild(root);
        return slide;
    }
    
    public Element createAnimationXml(Document document, List<XSLFAnimationType> animationTypes) throws ParserConfigurationException, DOMException, XmlException {
        Element timing = document.createElementNS(NS,"p:timing");
        Element tnLst = document.createElementNS(NS,"p:tnLst");
        Element par = document.createElementNS(NS,"p:par");
        Element cTn = document.createElementNS(NS,"p:cTn");
        cTn.setAttribute("id", this.getElementIdStr());
        cTn.setAttribute("dur", "indefinite");
        cTn.setAttribute("restart", "never");
        cTn.setAttribute("nodeType", "tmRoot");
        Element childTnLst = document.createElementNS(NS,"p:childTnLst");

        Element seq = document.createElementNS(NS,"p:seq");
        seq.setAttribute("concurrent", "1");
        seq.setAttribute("nextAc", "seek");
        seq.appendChild(this.createAnimationListXml(document, animationTypes));
        seq.appendChild(this.createCondLst(document, "prevCondLst", "onPrev"));
        seq.appendChild(this.createCondLst(document, "nextCondLst", "onNext"));
        
        childTnLst.appendChild(seq);
        cTn.appendChild(childTnLst);
        par.appendChild(cTn);
        tnLst.appendChild(par);
        timing.appendChild(tnLst);
        return timing;
    }
    
    private Element createCondLst(Document document, String tagName, String evtStr) {
        Element condLst = document.createElementNS(NS,"p:"+tagName);
        Element cond = document.createElementNS(NS,"p:cond");
        cond.setAttribute("evt", evtStr);
        cond.setAttribute("delay", "0");
        Element tgtEl = document.createElementNS(NS,"p:tgtEl");
        Element sldTgt = document.createElementNS(NS,"p:sldTgt");
        tgtEl.appendChild(sldTgt);
        cond.appendChild(tgtEl);
        condLst.appendChild(cond);
        return condLst;
    }
    
    private Element createAnimationListXml(Document document, List<XSLFAnimationType> animationTypes) throws DOMException, XmlException, ParserConfigurationException {
        Element cTn = document.createElementNS(NS,"p:cTn");
        cTn.setAttribute("id", this.getElementIdStr());
        cTn.setAttribute("dur", "indefinite");
        cTn.setAttribute("nodeType", "mainSeq");
        Element animationList = document.createElementNS(NS,"p:childTnLst");
        for (XSLFAnimationType xslfAnimationType : animationTypes) {
            animationList = this.createAnimationXml(document, xslfAnimationType, animationList);
        }
        cTn.appendChild(animationList);
        return cTn;
    }

    private Element createAnimationXml(Document document, XSLFAnimationType xslfAnimationType, Element childrenElement) throws DOMException, XmlException, ParserConfigurationException{
        Element firstElement = document.createElementNS(XSLFAnimation.NS,"p:par");
        Element element1 = document.createElementNS(XSLFAnimation.NS,"p:cTn");
        element1.setAttribute("id", this.getElementIdStr());
        element1.setAttribute("fill", "hold");

        Element element2 = document.createElementNS(XSLFAnimation.NS,"p:stCondLst");
        Element element3 = document.createElementNS(XSLFAnimation.NS,"p:cond");
        element3.setAttribute("delay", "indefinite");
        element2.appendChild(element3);
        element1.appendChild(element2);
        Element childList = document.createElementNS(XSLFAnimation.NS,"p:childTnLst");

        List<XSLFAnimationType> animations = xslfAnimationType.getChildren();
        if(animations != null && animations.size()>0) {
            childList.appendChild(xslfAnimationType.toXml(document, this));
            for (XSLFAnimationType animation : animations) {
                childList.appendChild(animation.toXml(document, this));
            }
        } else {
            childList.appendChild(xslfAnimationType.toXml(document, this));
        }
        element1.appendChild(childList);
        firstElement.appendChild(element1);
        childrenElement.appendChild(firstElement);
        return childrenElement;
    }
    
    public int getElementId() {
        return elementId++;
    }
    
    public String getElementIdStr() {
        return Integer.toString(this.getElementId());
    }
    
    public XmlObject toXml() {
        
        XmlObject xml = null;
        return xml;
    }

}