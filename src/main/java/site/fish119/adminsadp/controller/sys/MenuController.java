package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.Menu;
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
public class MenuController extends BaseController {
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

    @RequestMapping(value = "/setting/menus", method = RequestMethod.POST)
    public ResponseEntity<?> saveMenu(@RequestBody Menu reqBody) {
        Map<String, Object> result = new HashMap<>();
        menuService.saveMenu(reqBody);
        //由于下一句的getCurrentUserMenus()方法会更改本具所获得的结果
        //因此调用service的getNewCopyList方法，重新生成结果数组并返回给客户端
        result.put("data", menuService.getNewCopyList(menuService.findAllMenus()));
        result.put("userMenus", menuService.getCurrentUserMenus());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/menu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        menuService.delMenu(id);
        //由于下一句的getCurrentUserMenus()方法会更改本具所获得的结果
        //因此调用service的getNewCopyList方法，重新生成结果数组并返回给客户端
        result.put("data", menuService.getNewCopyList(menuService.findAllMenus()));
        result.put("userMenus", menuService.getCurrentUserMenus());
        return ResponseEntity.ok(result);
    }
}
