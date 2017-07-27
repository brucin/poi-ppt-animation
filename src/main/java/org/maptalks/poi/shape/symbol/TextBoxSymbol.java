package org.maptalks.poi.shape.symbol;

import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.TextParagraph;
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

    private Double[] padding = new Double[]{8.0, 2.0};

    private Double lineSpacing = 1.0;

    private String textWeight = "normal";

    private String textStyle = "normal";

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

    public void setPadding(Double[] padding) {
        this.padding = padding;
    }

    public void setLineSpacing(Double lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setTextWeight(String textWeight) {
        this.textWeight = textWeight;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
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

    public Double[] getPadding() {
        return padding;
    }

    public Double getLineSpacing() {
        return lineSpacing;
    }

    public String getTextWeight() {
        return textWeight;
    }

    public String getTextStyle() {
        return textStyle;
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

    public TextParagraph.TextAlign getHorizontalAlignment() {
        if(this.getHorizontal().equals("left")) {
            return TextParagraph.TextAlign.LEFT;
        } else if(this.getHorizontal().equals("right")) {
            return TextParagraph.TextAlign.RIGHT;
        } else {
            return TextParagraph.TextAlign.CENTER;
        }
    }

    public Insets2D getInsetPadding() {
        double top = 0.0, left = 0.0, bottom = 0.0, right = 0.0;
        Double[] padding = this.getPadding();
        int length = padding.length;
        if(length == 1) {
            top = left = bottom = right = padding[0];
        } else if(length == 2) {
            top = bottom = padding[0];
            left = right = padding[1];
        } else if(length == 3) {
            top = padding[0];
            left = right = padding[1];
            bottom = padding[2];
        } else if(length == 4) {
            top = padding[0];
            left = padding[1];
            bottom = padding[2];
            right = padding[3];
        }
        return new Insets2D(top, left, bottom, right);
    }

    public boolean isBold() {
        if(this.getTextWeight().equals("normal")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isItalic() {
        if(this.getTextStyle().equals("normal")) {
            return false;
        } else {
            return true;
        }
    }
}
