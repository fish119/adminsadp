package site.fish119.adminsadp.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.MenuRepository;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.security.UserDetailsImple;
import site.fish119.adminsadp.utils.MainUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.common
 * @Author fish119
 * @Date 2018/4/10 19:36
 * @Version V1.0
 */
@Service
public class CommonService {
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    @Autowired
    public CommonService(UserRepository userRepository,MenuRepository menuRepository){
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    public Map<String, Object> getGlobalData() {
        Map<String, Object> result = new HashMap<>();
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            UserDetailsImple userDetails = (UserDetailsImple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername());
            result.put("user", user);
            result.put("menus", MainUtil.cleanChildrenMenu(menuRepository.findByMRolesAndParentIsNullOrderBySortAsc(user.getRoles()), user.getRoles()));
        } else {
            throw new BadCredentialsException("用户未登录");
        }
        return result;
    }
}
