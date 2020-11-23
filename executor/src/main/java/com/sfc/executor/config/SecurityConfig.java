package com.sfc.executor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置类
     * 通过AuthenticationManagerBuilder创建一个用户认证信息
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("forezp").password("123456").roles("USER");
    }

    /**
     * 以“css”“index”的资源不需要验证，外部请求可以直接访问
     * 以“user”“blogs”开头的资源需要验证，并且用户角色“Role”
     * 表单登录地址是“login”，登录失败地址“login-error”
     * 异常处理重定向到401界面
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user.**").hasRole("USER")
                .antMatchers("/blogs/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").failureForwardUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401");
                http.logout().logoutSuccessUrl("/");
    }
}
