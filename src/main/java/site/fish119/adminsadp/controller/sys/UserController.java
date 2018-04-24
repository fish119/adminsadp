package site.fish119.adminsadp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.User;
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
            @RequestParam(name = "department", required = false) Long departId,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortColumn", required = false) String sortColumn,
            @RequestParam(name = "direction", required = false) String direction) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.findUsers(searchStr, departId, currentPage, pageSize, sortColumn, direction));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/users", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody User reqBody) {
        Map<String, Object> result = new HashMap<>();
        userService.save(reqBody);
        result.put("data", userService.findUsers(null, null, 0, 10, "id", "DESC"));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/api/user/checkUsernameUnique", method = RequestMethod.GET)
    public ResponseEntity<?> checkUsernameUnique(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "id", required = false) Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.checkUsernameUnique(username,id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/api/user/checkNicknameUnique", method = RequestMethod.GET)
    public ResponseEntity<?> checkNicknameUnique(
            @RequestParam(name = "nickname") String nickname,
            @RequestParam(name = "id", required = false) Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.checkNicknameUnique(nickname,id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/api/user/checkPhoneUnique", method = RequestMethod.GET)
    public ResponseEntity<?> checkPhoneUnique(
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "id", required = false) Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.checkPhoneUnique(phone, id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/api/user/checkEmailUnique", method = RequestMethod.GET)
    public ResponseEntity<?> checkEmailUnique(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "id", required = false) Long id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.checkEmailUnique(email,id));
        return ResponseEntity.ok(result);
    }
}
