package club.banyuan.mall.integration.service.impl;


import club.banyuan.mall.integration.dao.UmsResourceDao;
import club.banyuan.mall.integration.model.UmsResource;
import club.banyuan.mall.integration.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    UmsResourceDao umsResourceDao;
    
    @Override
    public List<UmsResource> getResourcesByAdminId(Long id) {
        return umsResourceDao.selectByAdminId(id);
    }

    @Override
    public List<UmsResource> getAllResources() {
        return umsResourceDao.selectAll();
    }
}
