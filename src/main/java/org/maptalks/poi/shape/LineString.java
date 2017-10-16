package org.maptalks.poi.shape;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.maptalks.poi.shape.symbol.StrokeSymbol;


public class LineString {

    private Double[] points;

    private StrokeSymbol symbol = new StrokeSymbol();

    public LineString(Double[] points) {
        this.points = points;
    }

    public static void main(String[] args) {
        XMLSlideShow pptx = new XMLSlideShow();
        XSLFSlide slide = pptx.createSlide();
        XSLFConnectorShape lineShape = slide.createConnector();
    }
}
