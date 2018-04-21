package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.Role;
import site.fish119.adminsadp.service.sys.RoleService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.sys
 * @Author fish119
 * @Date 2018/4/20 12:38
 * @Version V1.0
 */
@RestController
public class RoleController extends BaseController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @RequestMapping(value = "/setting/roles", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMenus() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", roleService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/roles", method = RequestMethod.POST)
    public ResponseEntity<?> saveMenu(@RequestBody Role reqBody) {
        Map<String, Object> result = new HashMap<>();
        roleService.save(reqBody);
        result.put("data", roleService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        roleService.delRole(id);
        result.put("data", roleService.findAll());
        return ResponseEntity.ok(result);
    }
}
