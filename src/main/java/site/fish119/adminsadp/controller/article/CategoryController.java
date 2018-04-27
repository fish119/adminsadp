package site.fish119.adminsadp.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.article.Category;
import site.fish119.adminsadp.service.Article.CategoryService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.article
 * @Author fish119
 * @Date 2018/4/26 12:06
 * @Version V1.0
 */
@RestController
public class CategoryController extends BaseController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/article/categories", method = RequestMethod.GET)
    public ResponseEntity<?> getArticleCategories() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", categoryService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/article/category", method = RequestMethod.POST)
    public ResponseEntity<?> saveMenu(@RequestBody Category reqBody) {
        Map<String, Object> result = new HashMap<>();
        categoryService.save(reqBody);
        result.put("data", categoryService.findAll());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/article/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        categoryService.del(id);
        result.put("data", categoryService.findAll());
        return ResponseEntity.ok(result);
    }
}
