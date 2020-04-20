package club.banyuan.demo.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "club.banyuan.demo")
public class AuthorizationApplication {
    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(AuthorizationApplication.class);
        //IgnoreUrlConfig ignoreUrlConfig = context.getBean(IgnoreUrlConfig.class);
        //ignoreUrlConfig.test();
    }
}
