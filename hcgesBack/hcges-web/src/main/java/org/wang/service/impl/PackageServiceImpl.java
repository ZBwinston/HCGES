package org.wang.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wang.model.Package;
import org.wang.service.PackageService;
import org.wang.mapper.PackageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author qwertyuiop
* @description 针对表【package】的数据库操作Service实现
* @createDate 2023-06-13 09:52:14
*/
@Service
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package>
    implements PackageService{

    @Resource
    private PackageMapper packageMapper;

    @Override
    public IPage<Package> selectPackage(Page<Package> page, Double minPrice, Double maxprice, String packageName) {
        return packageMapper.selectPackage(page,minPrice,maxprice,packageName);
    }

    @Override
    public String addPackage(String packageName) {
        Package p = new Package();
        p.setPackage_name(packageName);
        p.setPrice(0.00);

        boolean save = this.save(p);
        if (save){
            return p.getID();
        }
        return null;
    }

    // 删除套餐
    @Override
    public int deletePackage(String id) {
        return packageMapper.deleteById(id);
    }
}




