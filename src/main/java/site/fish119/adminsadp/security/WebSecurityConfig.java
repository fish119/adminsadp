package site.fish119.adminsadp.security;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import site.fish119.adminsadp.service.sys.UserService;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project sss
 * @Package site.fish119.sss.security
 * @Author fish119
 * @Date 2018/7/23 15:59
 * @Version V1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;

    @Bean
    public static SecurityInterceptorImple getSecurityInterceptor() {
        return new SecurityInterceptorImple();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //设置自定义的userDetailsService和密码编码方式
        authenticationManagerBuilder
                .userDetailsService(this.userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //添加跨域filter
                .addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
                // 添加JWT filter，用于登录认证
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.headers().cacheControl().disable();
        //允许iFrame
        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        //客户端必须post上来md5加密的password
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //important 跨域配置，重要！
    @Bean
    public Filter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
            logger.error("401:"+authException.getLocalizedMessage()+"|||Url="+request.getRequestURI());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getLocalizedMessage());
        };
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() {
        return new AuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
