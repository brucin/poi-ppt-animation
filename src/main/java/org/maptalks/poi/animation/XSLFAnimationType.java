package org.maptalks.poi.animation;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by wangjun on 16/2/18.
 */
public class XSLFAnimationType {

    protected XSLFSimpleShape shape;

    protected String moveDirection;

    protected String nodeType;

    private List<XSLFAnimationType> children = new ArrayList<XSLFAnimationType>();

    public XSLFAnimationType() {

    }

    public Element toXml(Document document, XSLFAnimation animation) throws XmlException, ParserConfigurationException{
        return null;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public List<XSLFAnimationType> getChildren() {
        return children;
    }

    public void setChildren(List<XSLFAnimationType> children) {
        this.children = children;
    }

    public void addChild(XSLFAnimationType child) {
        this.children.add(child);
    }

}
