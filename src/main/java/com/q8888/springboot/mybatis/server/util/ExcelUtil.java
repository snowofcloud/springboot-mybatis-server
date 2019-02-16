package com.q8888.springboot.mybatis.server.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * excel文件工具
 * </p>
 *
 * @author xuxq
 * @since 2018-10-13
 */
@Component
public class ExcelUtil {

    private final static String excel2003 = ".xls";
    private final static String excel2007 = ".xlsx";

    /**
     * @param in
     * @param fileName 处理上传的excel文件
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String cellValue = getCellValue(cell);
                    li.add(cellValue);
                }


                list.add(li);
            }

        }
        work.close();
        return list;
    }

    //判断excel文件的格式
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);
        } else if (excel2007.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 判断单元格的类型
     *
     * @param cell
     * @return
     */
    public String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = dateFormat.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    cellValue = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case _NONE:
                cellValue = "未知的类型";
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
        }
        return cellValue;
    }

    /**
     * 创建表头----年报表头
     *
     * @param workbook
     * @param sheet
     */
    public void createTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] tableHeader) {
        //创建工作簿
        HSSFRow row = sheet.createRow(0);
        //设置列宽
        for (int i = 0; i < tableHeader.length; i++) {
            if (tableHeader[i] == "通讯地址") {
                sheet.setColumnWidth(i, 40 * 256);
            } else {
                sheet.setColumnWidth(i, 27 * 256);
                //sheet.setDefaultColumnWidth(15*256);
            }
        }
        //设置单元格格式
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        HSSFFont font = workbook.createFont();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        //将元素保存至单元格内
        for (int i = 0; i < tableHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(tableHeader[i]);
            cell.setCellValue(textString);
            cell.setCellStyle(style);
        }
    }

    /**
     * 创建表头------月报的表头
     *
     * @param workbook
     * @param sheet
     * @param tableHeader
     */
    public void createTitlee(HSSFWorkbook workbook, HSSFSheet sheet, String tableHeader1, String[] tableHeader2, String[] tableHeader) {
        //创建工作簿的第一行
        HSSFRow row = sheet.createRow(0);//第一行
        //设置列宽
        sheet.setColumnWidth(0, 240 * 256);

        //创建工作簿的第二行
        HSSFRow row1 = sheet.createRow(1);//第二行
        //设置列宽
        for (int i = 0; i < tableHeader2.length; i++) {
            if (i == 0) {//0
                sheet.setColumnWidth(i + 0, 80 * 256);
            } else if (i == 1) {//1
                sheet.setColumnWidth(i + 1, 80 * 256);
            } else {//2
                sheet.setColumnWidth(i + 2, 80 * 256);
            }

        }

        //创建工作簿的第三行
        HSSFRow row2 = sheet.createRow(2);//第三行
        //设置列宽
        for (int i = 0; i < tableHeader.length; i++) {
            if ((tableHeader[i] == "主要项目") || (tableHeader[i] == "备注") || (tableHeader[i] == "全市与去年同期相比（±%）")) {
                sheet.setColumnWidth(i, 30 * 256);
            } else {
                sheet.setColumnWidth(i, 11 * 256);
            }
        }

        //设置单元格格式
        CellStyle style = workbook.createCellStyle();

        HSSFFont font = workbook.createFont();// 字体样式
        font.setBold(true); // 加粗
        font.setFontName("黑体"); // 字体
        font.setFontHeightInPoints((short) 11); // 大小

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setFont(font);// 将字体样式添加到单元格样式中

        //保存第一行的数据
        HSSFCell cell = row.createCell(0);
        HSSFRichTextString textString1 = new HSSFRichTextString(tableHeader1);
        cell.setCellValue(textString1);
        cell.setCellStyle(style);

        // 合并第一行的单元格
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 15); // 起始行, 终止行, 起始列, 终止列
        sheet.addMergedRegion(cra);
        //设置边框线条
        for (int i = 1; i < 16; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
        }

        //保存第二行的数据
        for (int i = 0; i < tableHeader2.length; i++) {
            if (i == 0) {//0
                HSSFCell cell1 = row1.createCell(i + 0);
                HSSFRichTextString textString2 = new HSSFRichTextString(tableHeader2[i]);
                cell1.setCellValue(textString2);
                cell1.setCellStyle(style);
                cra = new CellRangeAddress(1, 1, 0, 2); // 起始行, 终止行, 起始列, 终止列
                sheet.addMergedRegion(cra);
            } else if (i == 1) {//1
                HSSFCell cell1 = row1.createCell(i + 3);
                HSSFRichTextString textString2 = new HSSFRichTextString(tableHeader2[i]);
                cell1.setCellValue(textString2);
                cell1.setCellStyle(style);
                cra = new CellRangeAddress(1, 1, 4, 7); // 起始行, 终止行, 起始列, 终止列
                sheet.addMergedRegion(cra);
            } else {//2
                HSSFCell cell1 = row1.createCell(i + 7);
                HSSFRichTextString textString2 = new HSSFRichTextString(tableHeader2[i]);
                cell1.setCellValue(textString2);
                cell1.setCellStyle(style);
                cra = new CellRangeAddress(1, 1, 9, 12); // 起始行, 终止行, 起始列, 终止列
                sheet.addMergedRegion(cra);
            }
        }

        //将元素保存至单元格内  保存第三行的数据
        for (int i = 0; i < tableHeader.length; i++) {
            HSSFCell cell2 = row2.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(tableHeader[i]);
            cell2.setCellValue(textString);
            cell2.setCellStyle(style);
        }

    }



}