package club.banyuan.mall.integration.service;


import club.banyuan.mall.integration.dto.AdminLoginReq;
import club.banyuan.mall.integration.dto.AdminLoginResp;
import club.banyuan.mall.integration.model.UmsAdmin;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {

    AdminLoginResp login(AdminLoginReq adminLoginReq);

    UserDetails getUserDetailsByToken(String token);

    int deleteUserById(Long id);

    List<UmsAdmin> getUsersByUsernameOrNickname(String keyword);
}
