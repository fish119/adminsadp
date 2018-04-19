package site.fish119.adminsadp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import site.fish119.adminsadp.domain.BaseEntity;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.security.UserDetailsImple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service
 * @Author fish119
 * @Date 2018/4/18 19:19
 * @Version V1.0
 */
public abstract class BaseService<T extends BaseEntity> {
    public List<T> getNewCopyList(Iterable<T> oldList) {
        List<T> data = new ArrayList<>();
        for (T t : oldList) {
            data.add(getCopyBean(t));
        }
        return data;
    }

    public T getCopyBean(T t) {
        try {
            BaseEntity tmp = t.getClass().newInstance();
            BeanUtils.copyProperties(t, tmp);
            return (T) tmp;
        } catch (Exception e) {
            return null;
        }
    }

    User getCurrentUser(UserRepository userRepository) {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            UserDetailsImple userDetails = (UserDetailsImple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findByUsername(userDetails.getUsername());
        } else {
            throw new BadCredentialsException("用户未登录");
        }
    }

    public UserDetailsImple getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            return (UserDetailsImple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            throw new BadCredentialsException("用户未登录");
        }
    }
}
