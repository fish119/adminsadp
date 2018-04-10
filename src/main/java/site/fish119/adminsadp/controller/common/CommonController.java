package site.fish119.adminsadp.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.fish119.adminsadp.service.common.CommonService;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.common
 * @Author fish119
 * @Date 2018/4/10 19:39
 * @Version V1.0
 */
@RestController
public class CommonController {
    private final CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(commonService.getGlobalData());
    }
}