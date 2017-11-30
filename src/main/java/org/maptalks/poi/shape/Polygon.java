package org.maptalks.poi.shape;

import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.xslf.usermodel.*;
import org.maptalks.poi.shape.symbol.StrokeSymbol;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;


public class LineString extends Shape {

    private Double[][] points;

    private StrokeSymbol symbol = new StrokeSymbol();

    public LineString(Double[][] points, StrokeSymbol symbol) {
        this.points = points;
        this.symbol = symbol;
    }

    public XSLFFreeformShape convertTo(XSLFFreeformShape line) {
        if(line == null || this.points == null) return null;
        Rectangle2D textAnchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        line.setAnchor(textAnchor);

        if(this.symbol.getLineOpacity().doubleValue() > 0) {
            line.setLineColor(this.symbol.getLineColor());
        } else {
            line.setLineColor(null);
        }
        line.setLineWidth(this.symbol.getLineWidth());

//        if(this.symbol.getFillOpacity().doubleValue() > 0) {
//            line.setFillColor(this.symbol.getFillColor());
//        } else {
//            line.setFillColor(null);
//        }
        line.setLineDash(this.symbol.getLineDash());
        line.setLineHeadDecoration(this.symbol.getHeadDecoration());
        line.setLineHeadWidth(LineDecoration.DecorationSize.LARGE);
        line.setLineHeadLength(LineDecoration.DecorationSize.LARGE);
        line.setLineTailDecoration(this.symbol.getTailDecoration());
        line.setLineTailWidth(LineDecoration.DecorationSize.LARGE);
        line.setLineTailLength(LineDecoration.DecorationSize.LARGE);
        line.setPath(this.getPath(this.points));
        return line;
    }


    private GeneralPath getPath(Double[][] points) {
        GeneralPath path = new GeneralPath();
        int length = points.length, index = 0;
        if(points != null && length > 0) {
            for (Double[] point : points) {
                if(index == 0) {
                    path.moveTo(point[0], point[1]);
                } else {
                    path.lineTo(point[0], point[1]);
                }
                index ++;
            }
        }
        return path;
    }


}
