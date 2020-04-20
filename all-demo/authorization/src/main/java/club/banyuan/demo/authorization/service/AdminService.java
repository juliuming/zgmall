package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;
import club.banyuan.demo.authorization.security.AdminUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {

    AdminLoginResp login(AdminLoginReq adminLoginReq);

    UserDetails getUserDetailsByToken(String token);
}
