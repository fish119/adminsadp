package site.fish119.adminsadp.domain.article;

import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.sys.User;

import javax.persistence.*;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.article
 * @Author fish119
 * @Date 2018/4/26 11:04
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "article_article")
@Data
public class Article {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private Boolean isTop = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private User author;
}
