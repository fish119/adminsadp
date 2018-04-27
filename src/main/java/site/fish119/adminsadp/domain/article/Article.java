package site.fish119.adminsadp.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;
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
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Article extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String subTitle;
    private String description;
//    private List<String> attachment;
    private String thumbnail;
    private String content;
    private Boolean isTop = false;
    private String status;
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
}
