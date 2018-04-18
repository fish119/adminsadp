package site.fish119.adminsadp.controller.sys;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.sys.Authority;
import site.fish119.adminsadp.service.sys.AuthorityService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.sys
 * @Author fish119
 * @Date 2018/4/18 20:58
 * @Version V1.0
 */
@RestController
public class AuthorityController extends BaseController {
    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping(value = "/setting/authorities", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMenus() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", authorityService.findAllAuthorities());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/authorities", method = RequestMethod.POST)
    public ResponseEntity<?> saveMenu(@RequestBody Authority reqBody) {
        Map<String, Object> result = new HashMap<>();
        authorityService.saveAuthority(reqBody);
        result.put("data", authorityService.getNewCopyList(authorityService.findAllAuthorities()));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/setting/authority/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        authorityService.delAuthority(id);
        result.put("data", authorityService.getNewCopyList(authorityService.findAllAuthorities()));
        return ResponseEntity.ok(result);
    }
}
