package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.service.sys.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.sys
 * @Author fish119
 * @Date 2018/4/22 18:47
 * @Version V1.0
 */
@RestController
public class UserController extends BaseController {
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @RequestMapping(value = "/setting/users", method = RequestMethod.GET)
    public ResponseEntity<?> getPageAndSortUsers(
            @RequestParam(name = "searchStr", required = false) String searchStr,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortColumn", required = false) String sortColumn,
            @RequestParam(name = "direction", required = false) String direction) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.findUsers(searchStr, currentPage, pageSize, sortColumn, direction));
        return ResponseEntity.ok(result);
    }
}
