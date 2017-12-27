package org.maptalks.poi.group;

import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.maptalks.poi.shape.Shape;
import org.maptalks.poi.shape.Table;
import org.maptalks.poi.shape.TextBox;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup {

    private List<Shape> shapeList = new ArrayList<Shape>();

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public void addToList(Shape shape) {
        this.shapeList.add(shape);
    }

    public XSLFGroupShape convertTo(XSLFGroupShape groupShape) {
        if(this.shapeList == null && this.shapeList.size() == 0) return groupShape;
        Double left = 0.0, top = 0.0, width = 0.0, height = 0.0, maxLeft = 0.0, maxTop = 0.0;
        Rectangle2D anchor = new Rectangle2D.Double(left, top, width, height);;
        for (int i = 0; i < this.shapeList.size(); i++) {
            Shape shape = this.shapeList.get(i);
            if(shape instanceof TextBox) {
                XSLFTextBox text = ((TextBox) shape).convertTo(groupShape.createTextBox());
                anchor = text.getAnchor();
            } else if(shape instanceof Table) {
                XSLFTable table = ((Table) shape).convertTo(groupShape.createTable());
                anchor = table.getAnchor();
            }
            if(i == 0) {
                left = anchor.getX();
                top = anchor.getY();
                width = anchor.getWidth();
                height = anchor.getHeight();
                maxLeft = left + width;
                maxTop = top + height;
            } else {
                if(anchor.getX() < left) {
                    left = anchor.getX();
                }
                if(anchor.getY() < top) {
                    top = anchor.getY();
                }
                if(anchor.getX() + anchor.getWidth() > maxLeft) {
                    maxLeft = anchor.getX() + anchor.getWidth();
                }
                if(anchor.getY() + anchor.getHeight() > maxTop) {
                    maxTop = anchor.getY() + anchor.getHeight();
                }

            }
        }
        anchor = new Rectangle2D.Double(left, top, maxLeft - left, maxTop - top);
        groupShape.setAnchor(anchor);
        groupShape.setInteriorAnchor(anchor);
        return groupShape;
    }

}
