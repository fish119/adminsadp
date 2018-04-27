package site.fish119.adminsadp.service.Article;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.article.Category;
import site.fish119.adminsadp.repository.article.CategoryRepository;
import site.fish119.adminsadp.service.BaseService;

import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.Article
 * @Author fish119
 * @Date 2018/4/26 11:58
 * @Version V1.0
 */
@Service
public class CategoryService extends BaseService<Category> {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findByParentIsNullAndIsDeletedIsFalseOrderBySortAsc();
    }

    @Transactional
    public void save(Category category){
        Category dbCategory = category.getId() == null ? category : categoryRepository.getOne(category.getId());
        if (dbCategory.getId() != null) {
            dbCategory.setName(category.getName());
            dbCategory.setSort(category.getSort());
        }
        dbCategory.setParent(category.getPidWithoutParent() == null || category.getPidWithoutParent() == 0 ? null : categoryRepository.getOne(category.getPidWithoutParent()));
        categoryRepository.save(dbCategory);
    }

    public Boolean hasArticle(Long id){
        Category category = categoryRepository.getOne(id);
        return category.getArticles().isEmpty();
    }

    @Transactional
    public void del(Long id){
        Category category = categoryRepository.getOne(id);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }
}
