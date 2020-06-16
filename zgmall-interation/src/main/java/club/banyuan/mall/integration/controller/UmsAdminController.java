package club.banyuan.mall.integration.controller;

import club.banyuan.mall.integration.common.FailReason;
import club.banyuan.mall.integration.common.RequestFailException;
import club.banyuan.mall.integration.common.ResponseResult;
import club.banyuan.mall.integration.dto.AdminLoginReq;
import club.banyuan.mall.integration.model.PageInfomations;
import club.banyuan.mall.integration.model.UmsAdmin;
import club.banyuan.mall.integration.service.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UmsAdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestBody AdminLoginReq adminLoginReq) {
        try {
            return ResponseResult.success(adminService.login(adminLoginReq));
        } catch (RuntimeException ex) {
            return ResponseResult.forbidden(ex);
        }
    }

    @PostMapping(value = "/admin/delete/{id}")
    @ResponseBody
    public ResponseResult deleteAdmin(@PathVariable Long id) {
        try {
            int n = adminService.deleteUserById(id);
            if (n != 0) {
                return ResponseResult.success(n);
            } else {
                return ResponseResult.badRequest();
            }
        } catch (RequestFailException ex) {
            return ResponseResult.serverError(FailReason.USERNAMEPASSWORDERROR);
        }
    }

    @GetMapping(value = "/admin/info")
    @ResponseBody
    public ResponseResult listAdminInfo() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        return ResponseResult.success(userDetails);
    }

    @GetMapping(value = "/admin/list")
    @ResponseBody
    public ResponseResult listUsers(@RequestParam String keyWords,
                                    @RequestParam int pageSize,
                                    @RequestParam int pageNum) {
        try {
            Page<UmsAdmin> page = PageHelper.startPage(pageNum, pageSize);
            List<UmsAdmin> list= adminService.getUsersByUsernameOrNickname(keyWords);
            PageInfomations pageInfomations = new PageInfomations(page,list);
            return ResponseResult.success(pageInfomations);
        }catch (RuntimeException ex){
            return ResponseResult.serverError();
        }
    }

    @RequestMapping(value = "/admin/auth", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult auth() {
        try {
            return ResponseResult.success("success");
        } catch (RuntimeException ex) {
            return ResponseResult.unauthorized();
        }
    }
}
