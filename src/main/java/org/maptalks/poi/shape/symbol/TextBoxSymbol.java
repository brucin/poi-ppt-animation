package org.maptalks.poi.shape.symbol;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.awt.*;

/**
 * Created by wangjun on 2017/7/20.
 */
public class TextBoxSymbol {

    public static final TextBoxSymbol DEFAULT_SYMBOL = new TextBoxSymbol(

    );

    private Color lineColor = new Color(205,205,205);

    private Color fillColor = new Color(105,155,252);

    private Color fontColor = new Color(0,0,0);

    private String vertical = new String("middle");

    private String horizontal = new String("center");

    private String fontFamily = new String("microsoft yahei");

    private Double fontSize = 12.0;

    private boolean wordWrap = true;

    public TextBoxSymbol() {
    }

    public TextBoxSymbol(Color lineColor, Color fillColor, Color fontColor, String vertical, String horizontal, String fontFamily, Double fontSize, boolean wordWrap) {
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.fontColor = fontColor;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.wordWrap = wordWrap;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public void setFontSize(Double fontSize) {
        this.fontSize = fontSize;
    }

    public void setWordWrap(boolean wordWrap) {
        this.wordWrap = wordWrap;
    }

    public static TextBoxSymbol getDefaultSymbol() {
        return DEFAULT_SYMBOL;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public String getVertical() {
        return vertical;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public Double getFontSize() {
        return fontSize;
    }

    public boolean isWordWrap() {
        return wordWrap;
    }

    public VerticalAlignment getVerticalAlignment() {
        if(this.getVertical().equals("top")) {
            return VerticalAlignment.TOP;
        } else if(this.getVertical().equals("bottom")) {
            return VerticalAlignment.BOTTOM;
        } else {
            return VerticalAlignment.MIDDLE;
        }
    }

    public HorizontalAlignment getHorizontalAlignment() {
        if(this.getHorizontal().equals("left")) {
            return HorizontalAlignment.LEFT;
        } else if(this.getHorizontal().equals("right")) {
            return HorizontalAlignment.RIGHT;
        } else {
            return HorizontalAlignment.CENTER;
        }
    }
}
