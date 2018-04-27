package site.fish119.adminsadp.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.article.Article;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.Article
 * @Author fish119
 * @Date 2018/4/26 17:27
 * @Version V1.0
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> ,QuerydslPredicateExecutor<Article>{
}
