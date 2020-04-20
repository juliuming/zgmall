package club.banyuan.demo.authorization.controller;

import club.banyuan.demo.authorization.common.ResponseResult;
import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.service.AdminService;
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
        return ResponseResult.success(adminService.login(adminLoginReq));
    }

    @RequestMapping(value = "/admin/auth", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult auth() {
        return ResponseResult.success("success");
    }
}
