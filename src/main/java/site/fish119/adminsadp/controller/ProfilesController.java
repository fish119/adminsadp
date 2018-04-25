package site.fish119.adminsadp.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.adminsadp.service.sys.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller
 * @Author fish119
 * @Date 2018/4/25 10:58
 * @Version V1.0
 */
@RestController
public class ProfilesController extends BaseController{
    private final UserService userService;
    @Autowired
    public ProfilesController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping(value = "/setting/profile/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody JSONObject reqBody) {
        Map<String, String> result = new HashMap<>();
        String oldPassword = reqBody.getString("oldPassword");
        String newPassword = reqBody.getString("newPassword");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changePassword(username, oldPassword, newPassword);
        result.put("data", "SUCCESS");
        return ResponseEntity.ok(result);
    }
}
