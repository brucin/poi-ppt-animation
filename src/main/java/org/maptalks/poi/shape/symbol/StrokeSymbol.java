package org.maptalks.poi.shape.symbol;

import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.StrokeStyle;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * Created by wangjun on 2017/7/28.
 */
public class StrokeSymbol {

    private Color lineColor = new Color(205,205,205);

    private Double lineOpacity = 1.0;

    private Double lineWidth = 1.0;

    private StrokeStyle.LineDash lineDash = null;

    private StrokeStyle.LineCap lineCap = StrokeStyle.LineCap.SQUARE;

    private LineDecoration.DecorationShape headDecoration = LineDecoration.DecorationShape.NONE;

    private LineDecoration.DecorationSize headWidth = LineDecoration.DecorationSize.LARGE;

    private LineDecoration.DecorationSize headLength = LineDecoration.DecorationSize.LARGE;

    private LineDecoration.DecorationShape tailDecoration = LineDecoration.DecorationShape.NONE;

    private LineDecoration.DecorationSize tailWidth = LineDecoration.DecorationSize.LARGE;

    private LineDecoration.DecorationSize tailLength = LineDecoration.DecorationSize.LARGE;

    private Color fillColor = new Color(105,155,252);

    private Double fillOpacity = 1.0;

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setLineOpacity(Double lineOpacity) {
        this.lineOpacity = lineOpacity;
    }

    public void setLineWidth(Double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setLineDash(StrokeStyle.LineDash lineDash) {
        this.lineDash = lineDash;
    }

    public void setLineCap(StrokeStyle.LineCap lineCap) {
        this.lineCap = lineCap;
    }

    public void setHeadDecoration(LineDecoration.DecorationShape headDecoration) {
        this.headDecoration = headDecoration;
    }

    public void setHeadWidth(LineDecoration.DecorationSize headWidth) {
        this.headWidth = headWidth;
    }

    public void setHeadLength(LineDecoration.DecorationSize headLength) {
        this.headLength = headLength;
    }

    public void setTailDecoration(LineDecoration.DecorationShape tailDecoration) {
        this.tailDecoration = tailDecoration;
    }

    public void setTailWidth(LineDecoration.DecorationSize tailWidth) {
        this.tailWidth = tailWidth;
    }

    public void setTailLength(LineDecoration.DecorationSize tailLength) {
        this.tailLength = tailLength;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public Color getLineColor() {
        if(this.lineColor == null) return null;
        float[] rgbs = this.lineColor.getRGBColorComponents(null);
        if(this.lineOpacity > 0) {
            return new Color(rgbs[0], rgbs[1], rgbs[2], this.lineOpacity.floatValue());
        }
        return null;
    }

    public Double getLineOpacity() {
        return lineOpacity;
    }

    public Double getLineWidth() {
        return lineWidth;
    }

    public StrokeStyle.LineDash getLineDash() {
        return lineDash;
    }

    public StrokeStyle.LineCap getLineCap() {
        return lineCap;
    }

    public LineDecoration.DecorationShape getHeadDecoration() {
        return headDecoration;
    }

    public LineDecoration.DecorationSize getHeadWidth() {
        return headWidth;
    }

    public LineDecoration.DecorationSize getHeadLength() {
        return headLength;
    }

    public LineDecoration.DecorationShape getTailDecoration() {
        return tailDecoration;
    }

    public LineDecoration.DecorationSize getTailWidth() {
        return tailWidth;
    }

    public LineDecoration.DecorationSize getTailLength() {
        return tailLength;
    }

    public Color getFillColor() {
        if(this.fillColor == null) return null;
        float[] rgbs = this.fillColor.getRGBColorComponents(null);
        if(this.fillOpacity > 0) {
            return new Color(rgbs[0], rgbs[1], rgbs[2], this.fillOpacity.floatValue());
        }
        return null;
    }

    public Double getFillOpacity() {
        return fillOpacity;
    }

    public StrokeSymbol(Color lineColor, Double lineOpacity, Double lineWidth, StrokeStyle.LineDash lineDash, StrokeStyle.LineCap lineCap, LineDecoration.DecorationShape headDecoration, LineDecoration.DecorationShape tailDecoration, Color fillColor, Double fillOpacity) {
        this.lineColor = lineColor;
        this.lineOpacity = lineOpacity;
        this.lineWidth = lineWidth;
        this.lineDash = lineDash;
        this.lineCap = lineCap;
        this.headDecoration = headDecoration;
        this.tailDecoration = tailDecoration;
        this.fillColor = fillColor;
        this.fillOpacity = fillOpacity;
    }

    public StrokeSymbol() {
    }



}


