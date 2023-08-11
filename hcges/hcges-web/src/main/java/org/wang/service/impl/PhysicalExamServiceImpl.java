package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.wang.model.PhysicalExam;
import org.wang.model.vo.PhysicalExamVo;
import org.wang.service.PhysicalExamService;
import org.wang.mapper.PhysicalExamMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【physical_exam】的数据库操作Service实现
* @createDate 2023-06-13 23:23:29
*/
@Service
public class PhysicalExamServiceImpl extends ServiceImpl<PhysicalExamMapper, PhysicalExam>
    implements PhysicalExamService{

    @Resource
    private PhysicalExamMapper physicalExamMapper;

    // 新增体检单
    @Override
    public String addPhysicalExam(PhysicalExam physicalExam) {
        PhysicalExam physicalExam1 = new PhysicalExam();
        physicalExam1.setID(physicalExam.getID());
        physicalExam1.setUnit_ID(physicalExam.getUnit_ID());
        physicalExam1.setEmployee_ID(physicalExam.getEmployee_ID());
        physicalExam1.setExam_time(physicalExam.getExam_time());
        physicalExam1.setPackage_ID(physicalExam.getPackage_ID());
        physicalExam1.setPayment_method("0");

        boolean save = this.save(physicalExam1);

        if (save){
            return physicalExam1.getID();
        }

        return null;
    }

    @Override
    public IPage<PhysicalExamVo> getPhysicalExamList(Page<PhysicalExamVo> page, String unitName, String employeeName,String state) {
        return physicalExamMapper.getPhysicalExamList(page,unitName,employeeName,state);
    }
}




