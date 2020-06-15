package club.banyuan.mall.integration.service.impl;

import club.banyuan.mall.integration.dao.UmsAdminDao;
import club.banyuan.mall.integration.dto.AdminLoginReq;
import club.banyuan.mall.integration.dto.AdminLoginResp;
import club.banyuan.mall.integration.model.UmsAdmin;
import club.banyuan.mall.integration.security.AdminUserDetails;
import club.banyuan.mall.integration.security.ResourceConfigAttribute;
import club.banyuan.mall.integration.service.AdminService;
import club.banyuan.mall.integration.service.JwtService;
import club.banyuan.mall.integration.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {

    private static final String SCHEMA = "Bearer";
    private static final String TOKEN_HEAD_KEY = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UmsAdminDao umsAdminDao;

    @Autowired
    private UmsResourceService umsResourceService;

    @Override
    public AdminLoginResp login(AdminLoginReq adminLoginReq) {
        AdminLoginResp adminLoginResp = new AdminLoginResp();
        String subject = adminLoginReq.getUsername();
        adminLoginResp.setToken(jwtService.generate(subject));
        adminLoginResp.setTokenHead(SCHEMA);

        return adminLoginResp;
    }

    @Override
    public UserDetails getUserDetailsByToken(String token) {
        String userName = jwtService.getClaims(token).getSubject();
        UmsAdmin umsAdmin = umsAdminDao.selectUmsAdminByUsername(userName);
        List<ResourceConfigAttribute> resources =
                umsResourceService.getResourcesByAdminId(umsAdmin.getId())
                        .stream().map(ResourceConfigAttribute::new).collect(Collectors.toList());
        return new AdminUserDetails(umsAdmin,resources);
    }
}
