package org.wang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.wang.model.Package;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qwertyuiop
* @description 针对表【package】的数据库操作Service
* @createDate 2023-06-13 09:52:14
*/
public interface PackageService extends IService<Package> {

    /**
     * 查询套餐
     * @param page
     * @param minPrice
     * @param maxprice
     * @param packageName
     * @return
     */
    IPage<Package> selectPackage(Page<Package> page, Double minPrice, Double maxprice, String packageName);

    /**
     * 新增套餐
     * @param packageName
     * @return
     */
    String addPackage(String packageName);

    /**
     * 删除套餐
     * @param id
     * @return
     */
    int deletePackage(String id);

}
