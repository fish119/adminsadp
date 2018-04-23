package site.fish119.adminsadp.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import site.fish119.adminsadp.domain.sys.Authority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:33
 * @Version V1.0
 */
@Service
public class AccessDecisionManagerImple implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = request.getRequestURI();
        String method;
        AntPathRequestMatcher matcher;
        if (url.contains("/druid")||
                url.contains("/auth") ||
                url.contains("/api/") ||
                url.contains("/logfile") ||
                url.contains(".html") ||
                url.contains(".css") ||
                url.contains(".js") ||
                url.contains(".jpg") ||
                url.contains(".png") ||
                url.contains(".gif") ||
                url.contains(".ico")) {
            return;
        } else {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga instanceof Authority) {
                    Authority urlGrantedAuthority = (Authority) ga;
                    url = urlGrantedAuthority.getUrl();
                    method = urlGrantedAuthority.getMethod();
                    matcher = new AntPathRequestMatcher(url);
                    if (matcher.matches(request)) {
                        if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                            return;
                        }
                    }
                }
            }
        }
        throw new AccessDeniedException("未授权，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
