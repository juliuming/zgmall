package club.banyuan.demo.authorization.service.impl;

import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;
import club.banyuan.demo.authorization.service.AdminService;
import club.banyuan.demo.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private static final String SCHEMA = "Bearer";
    private static final String TOKEN_HEAD_KEY = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public AdminLoginResp login(AdminLoginReq adminLoginReq) {
        AdminLoginResp adminLoginResp = new AdminLoginResp();
        String subject = adminLoginReq.getUsername();
        adminLoginResp.setToken(jwtService.generate(subject));
        adminLoginResp.setTokenHead(SCHEMA);

        return adminLoginResp;
    }
}
