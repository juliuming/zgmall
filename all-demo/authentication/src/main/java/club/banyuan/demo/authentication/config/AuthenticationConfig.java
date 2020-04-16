package club.banyuan.demo.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreUrlConfig ignoreUrlConfig;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(WebSecurity web){
        for (String url : ignoreUrlConfig.getUrls()) {
            web.ignoring().antMatchers(url);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
                //add filter before.......
                //anyRequest.authenticated
                //to understand beanfactory.auoWireBean()
    }
}
