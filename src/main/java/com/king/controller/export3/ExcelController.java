package com.king.controller.export3;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.king.export3service.ExcelService;
import com.king.utils.export3.domian.CourseEntity;
import com.king.utils.export3.domian.StudentEntity;
import com.king.utils.export3.exceldate.ExcelData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author weixiaogang
 * @create 2019/6/11
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExcelController {
    private final ExcelData excelData;

    /**
     * 简单对象
     * @param response
     */
    @RequestMapping("/easyExport")
    public void loadEasyExcel(HttpServletResponse response) {

        ExcelService.exportExcel(excelData.getEasyList(),"计算机一班",
                "学生统计", StudentEntity.class,"学生统计.xlsx",response);

    }

    /**
     * 集合对象
     * @param response
     */
    @RequestMapping("/export")
    public void loadExcel(HttpServletResponse response) {

        ExcelService.exportExcel(excelData.getList(),"计算机一班",
                "学生课程统计", CourseEntity.class,"学生课程统计.xls",response);

    }

    /**
     * Excel多Sheet导出
     * @param response
     */
    @RequestMapping("/sheetExport")
    public void loadSheetExcel(HttpServletResponse response) {

        ExcelService.exportExcel(excelData.getMoreSheetData(),
                "计算机系学生.xls",response);

    }

    @RequestMapping("/templateExport")
    public void loadTemplateExcel(HttpServletResponse response) {
        TemplateExportParams templateExportParams =
                new TemplateExportParams("templates/template.xlsx");
        templateExportParams.setHeadingStartRow(0);
        templateExportParams.setHeadingRows(2);
        //template.xlsx
        ExcelService.exportTemplateExcel(excelData.getExcelDataByTemplate(),
                templateExportParams,"学生信息.xlsx",response);
    }

    /**
     * 导入
     * @param file
     */
    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("excelFile") MultipartFile file){
        //  String filePath = "F:\\海贼王.xls";
        //解析excel，
        //   List<Person> personList = excelService.importExcel(filePath,1,1,Person.class);
        List<StudentEntity> personList =
                ExcelService.importExcel(file, 1, 1, StudentEntity.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");
    }
}
