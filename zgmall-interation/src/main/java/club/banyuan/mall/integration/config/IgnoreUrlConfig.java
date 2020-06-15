package club.banyuan.mall.integration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "security.ignored")
public class IgnoreUrlConfig {
    public List<String> urls = new ArrayList<>();
    public void test(){
        System.out.println(urls);
    }
}
