package org.maptalks.poi.shape;

import org.apache.poi.sl.draw.geom.Guide;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xslf.usermodel.*;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import java.awt.geom.Rectangle2D;

/**
 * Created by wangjun on 2017/7/20.
 */
public class Table {

    private double left = 0.0;

    private double top = 0.0;

    private double width = 0.0;

    private double height = 0.0;

    private String[][] rows = null;

    private TextBoxSymbol[][] symbols = null;

    private double[] rowHeights = null;

    public Table(double left, double top, double width, double height, String[][] rows, TextBoxSymbol[][] symbols, double[] rowHeights) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.symbols = symbols;
        this.rowHeights = rowHeights;
    }

    public XSLFTable convertTo(XSLFTable table) {
        if(table == null || this.rows == null) return null;
//        XSLFTable table = slide.createTable();
        Rectangle2D anchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        table.setAnchor(anchor);
        for (int i = 0; i < this.rows.length; i++) {
            String[] row = this.rows[i];
            TextBoxSymbol[] rowSymbols = this.symbols[i];
            double rowHeight = this.rowHeights[i];
            this.addRow(table, row, rowSymbols, rowHeight);
        }
        return table;
    }

    private void addRow(XSLFTable table, String[] array, TextBoxSymbol[] rowSymbols, double rowHeight) {
        XSLFTableRow row = table.addRow();
        row.setHeight(rowHeight);
        for (int i = 0; i < array.length; i++) {
            String content = array[i];
            TextBoxSymbol symbol = rowSymbols[i];
            XSLFTableCell cell = row.addCell();
            cell.setBorderColor(TableCell.BorderEdge.top, symbol.getLineColor());
            cell.setBorderColor(TableCell.BorderEdge.left, symbol.getLineColor());
            cell.setBorderColor(TableCell.BorderEdge.bottom, symbol.getLineColor());
            cell.setBorderColor(TableCell.BorderEdge.right, symbol.getLineColor());
            cell.setFillColor(symbol.getFillColor());
            cell.clearText();
            cell.setWordWrap(symbol.isWordWrap());
            cell.setInsets(symbol.getInsetPadding());

            cell.setVerticalAlignment(symbol.getVerticalAlignment());
            TextParagraph textParagraph = cell.addNewTextParagraph();
            textParagraph.setTextAlign(symbol.getHorizontalAlignment());
            textParagraph.setLineSpacing(symbol.getLineSpacing());

            XSLFTextRun text = cell.setText(content);
            text.setFontColor(symbol.getFontColor());
            text.setFontSize(symbol.getFontSize());
            text.setFontFamily(symbol.getFontFamily());
            text.setBold(symbol.isBold());
            text.setItalic(symbol.isItalic());
        }
    }
}
