package club.banyuan.mall.integration.model;

import lombok.Data;

@Data
public class User {
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
