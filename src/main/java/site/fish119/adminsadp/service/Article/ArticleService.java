package site.fish119.adminsadp.service.Article;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.fish119.adminsadp.domain.article.Article;
import site.fish119.adminsadp.domain.article.QArticle;
import site.fish119.adminsadp.repository.article.ArticleRepository;
import site.fish119.adminsadp.repository.article.CategoryRepository;
import site.fish119.adminsadp.service.BaseService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.Article
 * @Author fish119
 * @Date 2018/4/26 17:28
 * @Version V1.0
 */
@Service
public class ArticleService extends BaseService<Article> {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository,CategoryRepository categoryRepository){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Article> findArticles(String searchStr,Long categoryId, Integer page, Integer size, String sortColumn, String direction){
        QArticle qArticle = QArticle.article;
        Predicate predicate = qArticle.id.isNotNull();
        if (!StringUtils.isEmpty(searchStr)) {
            predicate = ((BooleanExpression) predicate).and(qArticle.title.containsIgnoreCase(searchStr.trim())
                    .or(qArticle.description.containsIgnoreCase(searchStr.trim()))
                    .or(qArticle.subTitle.containsIgnoreCase(searchStr.trim())));
        }
        if (categoryId != null) {
            predicate = ((BooleanExpression) predicate).and(qArticle.category.id.eq(categoryId));
        }

        Integer pages = page == null ? 0 : page;
        Integer pageSize = size == null ? Integer.parseInt(defaultPageSize) : size;
        String sortCol = StringUtils.isEmpty(sortColumn) ? "id" : sortColumn;
        String desc = StringUtils.isEmpty(direction) ? "DESC" : direction;
        return articleRepository.findAll(predicate, getPageRequest(pages, pageSize, sortCol, desc));
    }

    public Article getOne(Long id){
        return articleRepository.getOne(id);
    }

    @Transactional
    public Article save(Article article){
        if(article.getCategory()!=null){
            article.setCategory(categoryRepository.getOne(article.getCategory().getId()));
        }
        return articleRepository.save(article);
    }

    @Transactional
    public String uploadPic(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + ".png";
        Files.copy(file.getInputStream(), Paths.get(avatarPath + "/avatar/").resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private Sort getSort(String sortColumn, String direction) {
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order topOrder = new Sort.Order(Sort.Direction.DESC, "isTop");
        orders.add(topOrder);

        Sort.Direction dr = (direction != null && direction.equals("ASC"))?Sort.Direction.ASC:Sort.Direction.DESC;
        if (sortColumn == null) sortColumn = "id";
        Sort.Order anotherOrder = new Sort.Order(dr,sortColumn);
        orders.add(anotherOrder);
        return Sort.by(orders);
    }

    private Pageable getPageRequest(Integer page, Integer size, String sortColumn, String direction) {
        return PageRequest.of(page, size, getSort(sortColumn, direction));
    }
}
