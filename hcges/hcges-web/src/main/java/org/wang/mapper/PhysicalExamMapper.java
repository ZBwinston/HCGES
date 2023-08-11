package org.wang.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.PhysicalExam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wang.model.vo.PhysicalExamVo;

/**
* @author qwertyuiop
* @description 针对表【physical_exam】的数据库操作Mapper
* @createDate 2023-06-13 23:23:28
* @Entity org.wang.model.PhysicalExam
*/
public interface PhysicalExamMapper extends BaseMapper<PhysicalExam> {

    IPage<PhysicalExamVo> getPhysicalExamList(Page<PhysicalExamVo> page, @Param("unitName") String unitName, @Param("employeeName") String employeeName,@Param("state") String state);

}




