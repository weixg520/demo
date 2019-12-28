package com.king.utils.export3.exceldate;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.king.utils.export3.domian.CourseEntity;
import com.king.utils.export3.domian.StudentEntity;
import com.king.utils.export3.domian.TeacherEntity;
import com.king.utils.export3.domian.TemplatEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luheng
 * @create 2019/6/12
 */
@Component
public class ExcelData {

    /**
     * 简单版数据
     * @return
     */
    public List<StudentEntity> getEasyList(){
        List<StudentEntity> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StudentEntity studentEntity =
                    new StudentEntity(""+i,"name"+i, getSex(i),new Date(),new Date());
            list.add(studentEntity);
        }
        return list;
    }
    /**
     * excel导出数据生成
     * @return
     */
    public List<CourseEntity> getList(){
        List<CourseEntity> listCourse = new ArrayList<>();
        CourseEntity courseEntity =
                new CourseEntity("1","物理",new TeacherEntity("1","张三"),getEasyList());
        CourseEntity courseEntity1 =
                new CourseEntity("2","化学",new TeacherEntity("2","李四"),getEasyList());
        listCourse.add(courseEntity);
        listCourse.add(courseEntity1);
        return listCourse;
    }

    public int getSex(int i){
        int sex = 1;
        if (i % 2 == 0) {
            sex = 2;
        }
        return sex;
    }

    /**
     * 生成多Sheet页数据
     * @return
     */
    public List<Map<String, Object>> getMoreSheetData(){
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        map.put("title", new ExportParams("Sheet页one", "sheet1"));
        map.put("entity", StudentEntity.class);
        map.put("data",getEasyList());
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("title", new ExportParams("Sheet页two", "sheet2"));
        map1.put("entity", StudentEntity.class);
        map1.put("data",getEasyList());
        list.add(map);
        list.add(map1);
        return list;
    }

    public Map<String, Object> getExcelDataByTemplate() {
        Map<String, Object> map = Maps.newHashMap();
        List<TemplatEntity> list = Lists.newArrayList();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (int i = 0; i < 4; i++) {
            list.add(new TemplatEntity("" + i, "Lad" + i, date));
        }
        map.put("maplist",list);
        return map;
    }
}
