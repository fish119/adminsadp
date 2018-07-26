package site.fish119.adminsadp.security;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import site.fish119.adminsadp.domain.sys.Authority;
import site.fish119.adminsadp.domain.sys.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Project sss
 * @Package site.fish119.sss.security
 * @Author fish119
 * @Date 2018/7/24 20:39
 * @Version V1.0
 */
@Component
public class AccessDecisionManagerImple implements AccessDecisionManager {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 判断权限的方法
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = request.getRequestURI();
        String method;
        AntPathRequestMatcher matcher;
        if (collection == null) {
            return;
        }
        //无需授权即可访问的路径
        if (url.contains("/druid")||
                url.contains("/auth") ||
                url.contains("/api/") ||
                url.contains("/article/categories") ||
                url.contains("/article/articles") ||
                url.contains("/article/article")||
                url.contains("/logfile") ||
                url.contains(".html") ||
                url.contains(".css") ||
                url.contains(".js") ||
                url.contains(".jpg") ||
                url.contains(".png") ||
                url.contains(".gif") ||
                url.contains(".ico")) {
            return;
        }else{
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
        logger.error("403:Forbidden"+"|||Url="+url+"|||UserId="+getUserId());
        throw new AccessDeniedException("AccessDeniedException 没有访问权限，未授权，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private Long getUserId(){
        try{
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getId();
        }catch (Exception e){
            return null;
        }
    }
}
