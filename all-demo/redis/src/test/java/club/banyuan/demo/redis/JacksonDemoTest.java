package club.banyuan.demo.redis;

import club.banyuan.demo.redis.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JacksonDemoTest {

    @Test
    public void jacksonSerialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writer().writeValueAsString(new User("admin", "123456"));
        System.out.println(value);
    }

    @Test
    public void jacksonDeserialize() throws Exception {
        String str = "{\"username\":\"admin\",\"password\":\"123456\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(str, User.class);
        System.out.println(user);

        // 反射反序列化的演示
        // Class cls = User.class;
        // Object o = cls.newInstance();
        // Method method = cls.getMethod("setUsername", String.class);
        // method.invoke(o,"admin");
    }


}