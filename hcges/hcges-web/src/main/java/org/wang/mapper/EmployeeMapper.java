package org.wang.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wang.model.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wang.model.vo.EmployeeVo;

import java.util.List;

/**
 * @author qwertyuiop
 * @description 针对表【employee】的数据库操作Mapper
 * @createDate 2023-06-08 16:23:37
 * @Entity org.wang.model.Employee
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("<script>" +
            "SELECT * " +
            "FROM employee e " +
            "LEFT JOIN unit u ON e.Unit_ID = u.ID " +
            "<where>" +
            "<if test=\"keyword != null and keyword != ''\">" +
            "AND (u.Unit_name LIKE CONCAT('%', #{keyword}, '%')) " +
            "</if>" +
            "</where>" +
            "LIMIT #{page}, #{size}" +
            "</script>")
    List<EmployeeVo> getEmployee(@Param("page") int page, @Param("size") int size, @Param("keyword") String keyword);


    int countEmployee(@Param(Constants.WRAPPER) QueryWrapper<EmployeeVo> queryWrapper);

    void saveBatch(List<Employee> list);

    @Select("select Unit_ID from employee where ID = #{id}")
    String getUidByEid(@Param("id") String id);
}




