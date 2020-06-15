package club.banyuan.mall.integration.security;

import club.banyuan.mall.integration.model.UmsResource;
import club.banyuan.mall.integration.service.UmsResourceService;
import cn.hutool.core.util.URLUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DynamicMetaDataSource implements SecurityMetadataSource {
    @Autowired
    UmsResourceService umsResourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        assert (object instanceof FilterInvocation);
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        String path = URLUtil.getPath(url);
        List<UmsResource> umsResources = umsResourceService.getAllResources();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return umsResources.stream().filter(t -> antPathMatcher.match(t.getUrl(), path))
                .map(ResourceConfigAttribute::new).collect(Collectors.toList());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
