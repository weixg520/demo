package com.king.utils.export2;

import com.king.utils.export2.domain.ExportDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weixiaogang
 * @date 2019-12-28 8:56
 * <p>
 * 类说明：测试导出
 */
public class TestExport {
    public static void main(String[] args) {
        List<ExportDTO> list = new ArrayList<>();
        ExportDTO e1 = ExportDTO.builder()
                .dictCode(1L)
                .dictSort(1L)
                .dictLabel("测试")
                .dictValue("111")
                .dictType("222")
                .cssClass("233")
                .listClass("88")
                .isDefault("Y")
                .status("0")
                .build();
        ExportDTO e2 = ExportDTO.builder()
                .dictCode(1L)
                .dictSort(1L)
                .dictLabel("测试")
                .dictValue("33")
                .dictType("22")
                .cssClass("fff")
                .listClass("kkk")
                .isDefault("Y")
                .status("0")
                .build();
        ExportDTO e3 = ExportDTO.builder()
                .dictCode(1L)
                .dictSort(1L)
                .dictLabel("测试")
                .dictValue("rf")
                .dictType("df")
                .cssClass("cf")
                .listClass("hh")
                .isDefault("Y")
                .status("0")
                .build();
        ExportDTO e4 = ExportDTO.builder()
                .dictCode(1L)
                .dictSort(1L)
                .dictLabel("测试")
                .dictValue("kkk")
                .dictType("hh")
                .cssClass("yhn")
                .listClass("rfv")
                .isDefault("Y")
                .status("0")
                .build();
        ExportDTO e5 = ExportDTO.builder()
                .dictCode(1L)
                .dictSort(1L)
                .dictLabel("测试")
                .dictValue("l;fg")
                .dictType("frg")
                .cssClass("rfs")
                .listClass("5tg")
                .isDefault("N")
                .status("1")
                .build();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        ExcelUtil2<ExportDTO> util = new ExcelUtil2<>(ExportDTO.class);
        util.exportExcel(list, "数据字典");
    }
}
