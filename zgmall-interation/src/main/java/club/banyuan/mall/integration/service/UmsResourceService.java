package club.banyuan.mall.integration.service;

import club.banyuan.mall.integration.model.UmsResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UmsResourceService {
    List<UmsResource> getResourcesByAdminId(Long id);
    List<UmsResource> getAllResources();
}
