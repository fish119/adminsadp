package site.fish119.adminsadp.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.fish119.adminsadp.domain.sys.Menu;
import site.fish119.adminsadp.repository.sys.MenuRepository;

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
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
//        this.userRepository = userRepository;
    }

    private final MenuRepository menuRepository;

    public List<Menu> findAllMenus() {
        return menuRepository.findByParentIsNullOrderBySortAsc();
    }
}
