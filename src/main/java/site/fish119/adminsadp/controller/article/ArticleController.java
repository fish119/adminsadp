package site.fish119.adminsadp.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish119.adminsadp.controller.BaseController;
import site.fish119.adminsadp.domain.article.Article;
import site.fish119.adminsadp.service.Article.ArticleService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller.article
 * @Author fish119
 * @Date 2018/4/26 17:30
 * @Version V1.0
 */
@RestController
public class ArticleController extends BaseController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/article/articles", method = RequestMethod.GET)
    public ResponseEntity<?> getArticles(
            @RequestParam(name = "searchStr", required = false) String searchStr,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortColumn", required = false) String sortColumn,
            @RequestParam(name = "direction", required = false) String direction) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", articleService.findArticles(searchStr, categoryId, currentPage, pageSize, sortColumn, direction));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/article/article/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id){
        Map<String, Object> result = new HashMap<>();
        result.put("data", articleService.getOne(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/article/articles", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Article reqBody) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", articleService.save(reqBody));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/article/article/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delMenu(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        articleService.deleteArticle(id);
        result.put("data", articleService.findArticles(null,null,0,15,null,null));
        return ResponseEntity.ok(result);
    }
}
