package com.king.utils.export;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author weixg_p
 * <p>
 * 导出Excel工具类
 */
public class ExportUtil {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;
    private String fileName;

    /**
     * 初始化Excel
     *
     * @param fileName 文件名称
     */
    public ExportUtil(String fileName) {
        this.fileName = fileName;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
    }

    /**
     * 导出Excel文件
     *
     * @return 输出
     */
    public boolean export() {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 增加一行
     *
     * @param index 下标
     */
    public void createRow(int index) {
        this.row = this.sheet.createRow(index);
    }

    /**
     * 设置单元格
     *
     * @param index 行号
     * @param value 值
     */
    @SuppressWarnings("deprecation")
    public void setCell(int index, String value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
    }

    /**
     * 设置带样式的单元格
     *
     * @param index 行号
     * @param value 值
     */
    @SuppressWarnings("deprecation")
    public void setCell(int index, Calendar value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellValue(value.getTime());
        // 建立新的cell样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置cell样式为定制的日期格式
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyyMMddHHmmssssssssssssss"));
        // 设置该cell日期的显示格式
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置单元格
     *
     * @param index 行号
     * @param value 值
     */
    @SuppressWarnings("deprecation")
    public void setCell(int index, int value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格
     *
     * @param index 行号
     * @param value 值
     */
    @SuppressWarnings("deprecation")
    public void setCell(int index, double value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
        // 建立新的cell样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        // 设置cell样式为定制的浮点数格式
        cellStyle.setDataFormat(format.getFormat(" #,##0.00 "));
        // 设置该cell浮点数的显示格式
        cell.setCellStyle(cellStyle);
    }

    /**
     * 合并单元格传参（List<Map> map的key为leftRow,leftCell,rightRow,rightCell）
     *
     * @param list
     */
    @SuppressWarnings("all")
    public void region(List<Map<String, Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            this.sheet.addMergedRegion(new CellRangeAddress(list.get(i).get("leftRow"),
                    Short.parseShort(list.get(i).get("leftCell") + ""),
                    list.get(i).get("rightRow"),
                    Short.parseShort(list.get(i).get("rightCell") + "")));
        }
    }

    public static void main(String[] args) {
        ExportUtil x = new ExportUtil("E:");
        x.createRow(0);
        x.setCell(0, "a");
        x.export();
    }
}
