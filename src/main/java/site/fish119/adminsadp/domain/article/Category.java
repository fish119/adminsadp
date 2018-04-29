package site.fish119.adminsadp.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.article
 * @Author fish119
 * @Date 2018/4/26 11:00
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "article_category")
@Data
public class Category extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private Long sort;
    @Transient
    private Long pid;
    private Boolean isDeleted = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Set<Article> articles;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderBy("sort ASC")
    @JoinColumn(name = "parent_id")
    private Set<Category> children = new HashSet<>(0);

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        if (sameParent(parent))
            return;
        Category oldParent = this.parent;
        this.parent = parent;
        if (oldParent != null) {
            oldParent.getChildren().remove(this);
        }
        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    private boolean sameParent(Category newParent) {
        return parent == null ? newParent == null : parent.equals(newParent);
    }

    @Transient
    public Long getPid() {
        if (this.getParent() != null) {
            return this.getParent().getId();
        } else {
            return this.pid;
        }
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPidWithoutParent() {
        return this.pid;
    }
}
