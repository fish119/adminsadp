package site.fish119.adminsadp.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:31
 * @Version V1.0
 */
@Service
public class FilterInvocationSecurityMetadataSourceImple implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> co=new ArrayList<>();
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
