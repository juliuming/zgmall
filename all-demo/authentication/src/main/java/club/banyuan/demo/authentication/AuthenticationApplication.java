package club.banyuan.demo.authentication;

import club.banyuan.demo.authentication.config.IgnoreUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "club.banyuan.demo")
public class AuthenticationApplication {
    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(AuthenticationApplication.class);
        //IgnoreUrlConfig ignoreUrlConfig = context.getBean(IgnoreUrlConfig.class);
        //ignoreUrlConfig.test();
    }
}
