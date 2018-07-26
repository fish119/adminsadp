package site.fish119.adminsadp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Project sss
 * @Package site.fish119.sss.security
 * @Author fish119
 * @Date 2018/7/24 20:37
 * @Version V1.0
 */
@Component
@Primary
public class SecurityInterceptorImple extends AbstractSecurityInterceptor implements Filter {
    @Resource
    private FilterInvocationSecurityMetadataSourceImple securityMetadataSource;

    @Autowired
    public void setAccessDecisionManager(AccessDecisionManagerImple accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Component
    public class FilterInvocationSecurityMetadataSourceImple implements FilterInvocationSecurityMetadataSource {
        @Override
        public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
            Collection<ConfigAttribute> co = new ArrayList<>();
            co.add(new SecurityConfig("null"));
            return co;
        }

        @Override
        public Collection<ConfigAttribute> getAllConfigAttributes() {
            return null;
        }

        @Override
        public boolean supports(Class<?> clazz) {
            return true;
        }
    }
}
