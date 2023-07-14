package com.grey.mapper;

import com.grey.pojo.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    // 查询
    // @Select("select * from student where id=#{id}")
    Student selectStu(String id);

    // 插入
    Integer insertStu(Student emp);

    // 更新
    Integer updateStu(Student emp);

    // 删除
    Integer deleteStu(String id);


}
