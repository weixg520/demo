package com.king.testexport;

import com.king.utils.IdUtils;
import com.king.utils.export.ExportUtil;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weixiaogang
 * @date 2019-12-28 7:52
 * <p>
 * 类说明：
 */
public class TestExportUtil {
    public static void main(String[] args) throws FileNotFoundException {
        String excelName = IdUtils.getRandomCode() + ".xls";
        String exportPath = ResourceUtils.getURL("classpath:").getPath() + "/" + excelName;
        ExportUtil e = new ExportUtil(exportPath);
        // 数据
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        exportExcel(e, list);
        if (e.export()) {
            System.out.println("导出成功!!!");
        }
    }

    private static void exportExcel(ExportUtil e, List<Integer> list) {
        e.createRow(0);
        String[] nameList = new String[]{"编号", "测试1", "测试2", "测试3"};
        for (int i = 0; i < nameList.length; i++) {
            e.setCell(i, nameList[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            e.createRow(i + 1);
            // 设置每一列的值
            e.setCell(0, list.get(i));
            e.setCell(1, list.get(i));
            e.setCell(2, list.get(i));
            e.setCell(3, list.get(i));
        }
    }
}
