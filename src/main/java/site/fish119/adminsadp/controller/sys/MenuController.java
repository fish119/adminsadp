package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.adminsadp.service.sys.MenuService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.sys
 * @Author fish119
 * @Date 2018/4/14 20:35
 * @Version V1.0
 */
@RestController
public class MenuController {
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    private final MenuService menuService;

    @RequestMapping(value = "/setting/menus", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMenus() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", menuService.findAllMenus());
        return ResponseEntity.ok(result);
    }
}
