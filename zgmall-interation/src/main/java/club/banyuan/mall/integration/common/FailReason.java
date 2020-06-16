package club.banyuan.mall.integration.common;

public enum FailReason {
    USERNAMEPASSWORDERROR("用户名或密码错误"),
    USERNAMENOTEXSIST("用户名不存在"),
    USERROLEEMPTY("角色列表为空"),
    USERROLENOTEXIST("角色不存在"),
    USERNAMEDUPLICATE("角色名冲突"),
    USERROLEMENURELILLEGAL("角色菜单关系不合法");


    private String message;

    FailReason(String reason) {
        this.message = reason;
    }

    public String getMessage() {
        return this.message;
    }
}
