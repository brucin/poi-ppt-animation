package org.maptalks.poi.shape;

import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;
import org.maptalks.poi.shape.symbol.TextBoxSymbol;

import java.awt.geom.Rectangle2D;

/**
 * Created by wangjun on 2017/7/20.
 */
public class Table extends Shape {

    private String[][] rows = null;

    private TextBoxSymbol[][] symbols = null;

    private Double[] rowHeights = null;

    private Double[] colWidths = null;

    public Table(Double left, Double top, Double width, Double height, String[][] rows, TextBoxSymbol[][] symbols, Double[] rowHeights, Double[] colWidths) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.symbols = symbols;
        this.rowHeights = rowHeights;
        this.colWidths = colWidths;
    }

    public XSLFTable convertTo(XSLFTable table) {
        if(table == null || this.rows == null) return null;
        Rectangle2D anchor = new Rectangle2D.Double(this.left, this.top, this.width, this.height);
        table.setAnchor(anchor);
        for (int i = 0; i < this.rows.length; i++) {
            String[] row = this.rows[i];
            TextBoxSymbol[] rowSymbols = this.symbols[i];
            double rowHeight = this.rowHeights[i];
            this.addRow(table, row, rowSymbols, rowHeight);
        }
        for (int j = 0; j < colWidths.length; j++) {
            table.setColumnWidth(j, colWidths[j]);
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
            textParagraph.setTextAlign(symbol.getTextAlign());
            //@Todo 猜测powerpoint将该值理解为1倍行高
//            textParagraph.setLineSpacing(symbol.getLineSpacing());

            XSLFTextRun text = cell.setText(content);
            text.setFontColor(symbol.getFontColor());
            text.setFontSize(symbol.getFontSize());
            text.setFontFamily(symbol.getFontFamily());
            text.setBold(symbol.isBold());
            text.setItalic(symbol.isItalic());
        }
    }
}
