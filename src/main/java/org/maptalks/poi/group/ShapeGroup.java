package org.maptalks.poi.group;

import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.maptalks.poi.shape.Shape;
import org.maptalks.poi.shape.Table;
import org.maptalks.poi.shape.TextBox;

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
        for (int i = 0; i < this.shapeList.size(); i++) {
            Shape shape = this.shapeList.get(i);
            if(shape instanceof TextBox) {
                ((TextBox) shape).convertTo(groupShape.createTextBox());
            } else if(shape instanceof Table) {
                ((Table) shape).convertTo(groupShape.createTable());
            }
        }
        return groupShape;
    }

}
