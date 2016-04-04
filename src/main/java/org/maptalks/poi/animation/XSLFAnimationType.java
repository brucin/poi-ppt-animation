package org.maptalks.poi.animation;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by wangjun on 16/2/18.
 */
public interface XSLFAnimationType {
    
    Element toXml(Document document, XSLFAnimation animation) throws XmlException, ParserConfigurationException;

}
