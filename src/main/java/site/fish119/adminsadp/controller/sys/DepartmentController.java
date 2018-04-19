package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.Department;
import site.fish119.adminsadp.service.sys.DepartmentService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.sys
 * @Author fish119
 * @Date 2018/4/19 10:31
 * @Version V1.0
 */
@RestController
public class DepartmentController extends BaseController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/setting/departments", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMenus() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", departmentService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/departments", method = RequestMethod.POST)
    public ResponseEntity<?> saveMenu(@RequestBody Department reqBody) {
        Map<String, Object> result = new HashMap<>();
        departmentService.save(reqBody);
        result.put("data", departmentService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/department/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        departmentService.del(id);
        result.put("data", departmentService.findAll());
        return ResponseEntity.ok(result);
    }
}
