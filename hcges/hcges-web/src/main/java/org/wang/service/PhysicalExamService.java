package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.PhysicalExam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wang.model.vo.PhysicalExamVo;

/**
* @author qwertyuiop
* @description 针对表【physical_exam】的数据库操作Service
* @createDate 2023-06-13 23:23:29
*/
public interface PhysicalExamService extends IService<PhysicalExam> {

    /**
     * 新增体检单
     * @param physicalExam
     * @return
     */
    String addPhysicalExam(PhysicalExam physicalExam);

    /**
     * 分页查询体检单
     * @param page
     * @param unitName
     * @param employeeName
     * @return
     */
    IPage<PhysicalExamVo> getPhysicalExamList(Page<PhysicalExamVo> page, String unitName, String employeeName,String state);

}
