package site.fish119.adminsadp.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.sys.Menu;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.MenuRepository;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.security.UserDetailsImple;
import site.fish119.adminsadp.utils.MainUtil;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/14 20:45
 * @Version V1.0
 */
@Service
public class MenuService {
    @Autowired
    public MenuService(MenuRepository menuRepository,UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public List<Menu> findAllMenus() {
        return menuRepository.findByParentIsNullOrderBySortAsc();
    }

    public List<Menu> getNewCopyMenuList(Iterable<Menu> oldList){
        List<Menu> data= new ArrayList<>();
        for(Menu menu : oldList){
            Menu tmp = new Menu();
            tmp.setPath(menu.getPath());
            tmp.setIcon(menu.getIcon());
            tmp.setName(menu.getName());
            tmp.setSort(menu.getSort());
            tmp.setId(menu.getId());
            tmp.setOnlySa(menu.getOnlySa());
            tmp.setPid(menu.getPid());
            tmp.setChildren(new LinkedHashSet<>(getNewCopyMenuList(menu.getChildren())));
            data.add(tmp);
        }
        return data;
    }

    @Transactional
    public void saveMenu(Menu menu) {
        Menu dbMenu = menu.getId() == null ? menu : menuRepository.getOne(menu.getId());
        if(dbMenu.getId()!=null) {
            dbMenu.setIcon(menu.getIcon());
            dbMenu.setName(menu.getName());
            dbMenu.setSort(menu.getSort());
            dbMenu.setPath(menu.getPath());
            dbMenu.setOnlySa(menu.getOnlySa());
        }
        dbMenu.setParent(menu.getPidWithoutParent()==null||menu.getPidWithoutParent()==0?null:menuRepository.getOne(menu.getPidWithoutParent()));
        menuRepository.save(dbMenu);
    }

    public List<Menu> getCurrentUserMenus() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            UserDetailsImple userDetails = (UserDetailsImple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername());
            return MainUtil.cleanChildrenMenu(menuRepository.findByMRolesAndParentIsNullOrderBySortAsc(user.getRoles()), user.getRoles());
        } else {
            throw new BadCredentialsException("用户未登录");
        }
    }
}
