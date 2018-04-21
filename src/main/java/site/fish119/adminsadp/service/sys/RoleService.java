package site.fish119.adminsadp.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.sys.Menu;
import site.fish119.adminsadp.domain.sys.Role;
import site.fish119.adminsadp.repository.sys.MenuRepository;
import site.fish119.adminsadp.repository.sys.RoleRepository;
import site.fish119.adminsadp.service.BaseService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/20 12:36
 * @Version V1.0
 */
@Service
@Transactional
public class RoleService extends BaseService<Role> {
    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, MenuRepository menuRepository) {
        this.roleRepository = roleRepository;
        this.menuRepository = menuRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    public void save(Role role) {
        if(role.getId()!=null) {
            Set<Menu> parentMenu = new LinkedHashSet<>();
            if (!role.getMenus().isEmpty()) {
                for (Menu menu : role.getMenus()) {
                    if (menu.getPidWithoutParent() != null) {
                        parentMenu.add(menuRepository.getOne(menu.getPidWithoutParent()));
                    }
                }
            }
            role.getMenus().addAll(parentMenu);
            roleRepository.save(role);
        }else{
            Role newRole = getCopyBean(role);
            newRole.setMenus(null);
            newRole.setAuthorities(null);
            newRole = roleRepository.saveAndFlush(newRole);
            newRole.setMenus(role.getMenus());
            newRole.setAuthorities(role.getAuthorities());
            roleRepository.saveAndFlush(newRole);
        }
    }

    @Transactional
    public void delRole(Long id) {
        roleRepository.deleteById(id);
    }
}
