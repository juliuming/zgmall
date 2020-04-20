package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.model.UmsResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UmsResourceService {
    List<UmsResource> getResourcesByAdminId(Long id);
    List<UmsResource> getAllResources();
}
