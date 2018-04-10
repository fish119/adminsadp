package site.fish119.adminsadp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:30
 * @Version V1.0
 */
@Service
public class SecurityInterceptorImple extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    public SecurityInterceptorImple(FilterInvocationSecurityMetadataSourceImple securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    private final FilterInvocationSecurityMetadataSourceImple securityMetadataSource;

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
        try {
            invoke(fi);
        } catch (AuthenticationCredentialsNotFoundException exception) {
            chain.doFilter(request, response);
        }
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, fi.getResponse());
        }
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
}
