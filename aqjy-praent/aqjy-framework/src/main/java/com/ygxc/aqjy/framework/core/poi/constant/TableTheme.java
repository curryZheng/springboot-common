package com.ygxc.aqjy.framework.core.poi.constant;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * excel样式
 * 
 * @author Qiaoxin.Hong
 *
 */
public enum TableTheme {
    /**
     * 默认样式 <br/>
     * 标题字体加粗,背景奶奶灰,细边框,水平居中,垂直居中 <br/>
     * 内容不自动换行,无边框,水平靠左,垂直居中 <br/>
     * 行宽度460px <br/>
     */
    DEFAULT_STYLE(),
    /**
     * 第二套备选样式 <br/>
     * 标题字体加粗,背景奶奶灰,细边框,水平居中,垂直居中 <br/>
     * 内容不自动换行,无边框,水平靠左,垂直居中 <br/>
     */
    STYLE_TWO() {
        @Override
        public void titleRowSettings(Row row) {

        }

        @Override
        public void cellRowSettings(Row row) {

        }

    },
    /**
     * 第三套备选样式 <br/>
     * 标题字体加粗,背景奶奶灰,细边框,水平居中,垂直居中 <br/>
     * 内容自动换行,无边框,水平居中,垂直居中 <br/>
     * 宽度460px <br/>
     */
    STYLE_THREE() {
        @Override
        public void titleRowSettings(Row row) {

        }

        public void cellRowSettings(Row row){

        }

        @Override
        public CellStyle getCellStyle(Workbook workbook) {
            CellStyle style = super.getCellStyle(workbook);
            style.setWrapText(true);//设置自动换行
            style.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直对齐
            return style;
        }

    },
    ;


    /**
     * 列标题设置
     */
    public void titleRowSettings(Row row){
        row.setHeight((short) 460);
    }

    /**
     * 列标题样式
     */
    public CellStyle getColTitleCellStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setFont(getColTitleFont(workbook));  //设置标题字体样式
        style.setAlignment(HorizontalAlignment.CENTER);//设置对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直对齐

        // 背景奶奶灰
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());

        // 细线边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 列标题的字体
     */
    public Font getColTitleFont(Workbook workbook){
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);  //设置字体大小
        font.setFontName("SansSerif");
        font.setBold(true);  //粗体显示
        return font;
    }

    /**
     * 列样式
     */
    public CellStyle getCellStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setFont(getFont(workbook));  //设置内容字体样式
        style.setAlignment(HorizontalAlignment.LEFT);//设置对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直对齐
        style.setWrapText(false);//设置自动换行
        return style;
    }

    /**
     * 列行设置
     */
    public void cellRowSettings(Row row){
        row.setHeight((short) 460);
    }

    /**
     * 列字段字体
     */
    public Font getFont(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("SansSerif");
        font.setFontHeightInPoints((short) 10);  //设置字体大小
        return font;
    }
}
