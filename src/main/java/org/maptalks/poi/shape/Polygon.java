package org.maptalks.poi.shape;

import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.maptalks.poi.shape.symbol.StrokeSymbol;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;


public class Polygon extends Shape {

    private Double[][] points;

    private StrokeSymbol symbol = new StrokeSymbol();

    public Polygon(Double[][] points, StrokeSymbol symbol) {
        this.points = points;
        this.symbol = symbol;
    }

    public XSLFFreeformShape convertTo(XSLFFreeformShape polygon) {
        if(polygon == null || this.points == null) return null;
        Rectangle2D textAnchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        polygon.setAnchor(textAnchor);
        polygon.setLineColor(this.symbol.getLineColor());
        polygon.setLineWidth(this.symbol.getLineWidth());
        polygon.setFillColor(this.symbol.getFillColor());
        polygon.setLineDash(this.symbol.getLineDash());
        polygon.setPath(this.getPath(this.points));
        return polygon;
    }

    private Path2D.Double getPath(Double[][] points) {
        Path2D.Double path = new Path2D.Double();
        int length = points.length, index = 0;
        if(points != null && length > 0) {
            for (Double[] point : points) {
                if(index == 0) {
                    path.moveTo(point[0], point[1]);
                } else {
                    path.lineTo(point[0], point[1]);
                }
                if (index == length -1) {
                    path.closePath();
                }
                index ++;
            }
        }
        return path;
    }


//    public GeneralPath getPath(Double[][] points) {
//        GeneralPath path = new GeneralPath();
//        int length = points.length, index = 0;
//        if(points != null && length > 0) {
//            for (Double[] point : points) {
//                if(index == 0) {
//                    path.moveTo(point[0], point[1]);
//                } else {
//                    path.lineTo(point[0], point[1]);
//                }
//                if (index == length -1) {
//                    path.closePath();
//                }
//                index ++;
//            }
//        }
//        return path;
//    }


}
