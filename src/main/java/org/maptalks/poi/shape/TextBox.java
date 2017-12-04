package org.maptalks.poi.shape;

import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 2017/7/20.
 */
public class TextBox extends Shape {

    private String text;

    private List<String> rows = new ArrayList<String>();

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

    public TextBox(List<String> rows, Double left, Double top, Double width, Double height, TextBoxSymbol symbol) {
        this.rows = rows;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.textBoxSymbol = symbol;
    }

    public String getText() {
        return this.whenTextIsNull(this.text);
    }

    public String whenTextIsNull(String text) {
        if(text == null || text.toLowerCase().equals("null")) {
            text = "";
        }
        return text;
    }

    public XSLFTextBox convertTo(XSLFTextBox textBox) {
        if(textBox == null) return null;
        Rectangle2D textAnchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        textBox.setAnchor(textAnchor);

        textBox.setShapeType(this.textBoxSymbol.getBoxType());
        textBox.setLineColor(this.textBoxSymbol.getLineColor());
        textBox.setLineWidth(this.textBoxSymbol.getLineWidth());

        textBox.setFillColor(this.textBoxSymbol.getFillColor());
        textBox.setWordWrap(this.textBoxSymbol.isWordWrap());
        textBox.setInsets(this.textBoxSymbol.getInsetPadding());
        textBox.setVerticalAlignment(this.textBoxSymbol.getVerticalAlignment());
        textBox.clearText();
        XSLFTextParagraph textParagraph = textBox.addNewTextParagraph();
        textParagraph.setTextAlign(this.textBoxSymbol.getTextAlign());
        this.appendTexts(textParagraph);
        return textBox;
    }

    private void appendTexts(XSLFTextParagraph textParagraph) {
        if(this.rows.size() > 0) {
            for (int i = 0; i < this.rows.size(); i++) {
                String text = this.rows.get(i);
                this.appendText(textParagraph, this.whenTextIsNull(text));
                textParagraph.addLineBreak();
            }
        } else {
            this.appendText(textParagraph, this.getText());
        }
    }

    private void appendText(XSLFTextParagraph textParagraph, String text) {
        XSLFTextRun textRun = textParagraph.addNewTextRun();
        textRun.setText(text);
        textRun.setFontColor(this.textBoxSymbol.getFontColor());
        textRun.setFontSize(this.textBoxSymbol.getFontSize());
        textRun.setFontFamily(this.textBoxSymbol.getFontFamily());
        textRun.setBold(this.textBoxSymbol.isBold());
        textRun.setItalic(this.textBoxSymbol.isItalic());
    }


}
