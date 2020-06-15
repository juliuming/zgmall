package club.banyuan.mall.integration.service;


import club.banyuan.mall.integration.dto.AdminLoginReq;
import club.banyuan.mall.integration.dto.AdminLoginResp;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {

    AdminLoginResp login(AdminLoginReq adminLoginReq);

    UserDetails getUserDetailsByToken(String token);
}
