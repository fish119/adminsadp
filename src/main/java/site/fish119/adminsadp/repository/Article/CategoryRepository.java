package site.fish119.adminsadp.repository.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.article.Category;

import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.Article
 * @Author fish119
 * @Date 2018/4/26 11:56
 * @Version V1.0
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByParentIsNullAndIsDeletedIsFalseOrderBySortAsc();
}
