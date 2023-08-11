package org.wang.mapper;

import org.apache.ibatis.annotations.Select;
import org.wang.model.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author qwertyuiop
* @description 针对表【department】的数据库操作Mapper
* @createDate 2023-05-16 10:51:46
* @Entity org.wang.model.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("select Department_name from department where ID = #{id}")
    String selectNameById(String id);

}




