package org.maptalks.poi.shape;

import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.XSLFShapeContainer;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import java.awt.geom.Rectangle2D;

/**
 * Created by wangjun on 2017/7/20.
 */
public class TextBox extends Shape {

    private String text;

    private TextBoxSymbol textBoxSymbol = new TextBoxSymbol();

    public TextBox(String text) {
        this.text = text;
        this.width = 100.0;
        this.height = 16.0;
        this.textBoxSymbol = TextBoxSymbol.DEFAULT_SYMBOL;
    }

    public TextBox(String text, Double left, Double top, Double width, Double height, TextBoxSymbol symbol) {
        this.text = text;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.textBoxSymbol = symbol;
    }

    public XSLFTextBox convertTo(XSLFTextBox textBox) {
        if(textBox == null) return null;
        Rectangle2D textAnchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        textBox.setAnchor(textAnchor);

        textBox.setShapeType(this.textBoxSymbol.getBoxType());
        textBox.setLineColor(this.textBoxSymbol.getLineColor());
        textBox.setFillColor(this.textBoxSymbol.getFillColor());
        textBox.setWordWrap(this.textBoxSymbol.isWordWrap());
        textBox.setInsets(this.textBoxSymbol.getInsetPadding());
        textBox.setVerticalAlignment(this.textBoxSymbol.getVerticalAlignment());
        TextParagraph textParagraph = textBox.addNewTextParagraph();
        textParagraph.setTextAlign(this.textBoxSymbol.getHorizontalAlignment());
        textParagraph.setLineSpacing(this.textBoxSymbol.getLineSpacing());

        XSLFTextRun text = textBox.setText(this.text);
        text.setFontColor(this.textBoxSymbol.getFontColor());
        text.setFontSize(this.textBoxSymbol.getFontSize());
        text.setFontFamily(this.textBoxSymbol.getFontFamily());
        text.setBold(this.textBoxSymbol.isBold());
        text.setItalic(this.textBoxSymbol.isItalic());
        return textBox;
    }
}
