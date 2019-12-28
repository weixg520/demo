package com.king.utils.export3.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author luheng
 * @create 2019/6/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplatEntity {
    private String id;
    private String name;
    private String birthday;
}
