package club.banyuan.mall.integration.controller;

import club.banyuan.mall.integration.common.ResponseResult;
import club.banyuan.mall.integration.dto.AdminLoginReq;
import club.banyuan.mall.integration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestBody AdminLoginReq adminLoginReq) {
        try {
            return ResponseResult.success(adminService.login(adminLoginReq));
        }catch (RuntimeException e){
            return ResponseResult.forbidden();
        }
    }

    @RequestMapping(value = "/admin/auth", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult auth() {
        try {
            return ResponseResult.success("success");
        }catch (RuntimeException e){
            return  ResponseResult.unauthorized();
        }

    }
}
