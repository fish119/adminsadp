package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.sys
 * @Author fish119
 * @Date 2018/4/6 16:45
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "sys_department")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Department extends BaseEntity {
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private Long sort;
    @Transient
    private Long pid;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Department parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "parent")
    @OrderBy("sort ASC")
    private Set<Department> children = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "dept_id")
    private Set<User> users;

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        if (sameParent(parent))
            return;
        Department oldParent = this.parent;
        this.parent = parent;
        if (oldParent != null) {
            oldParent.getChildren().remove(this);
        }
        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    private boolean sameParent(Department newParent) {
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

    public Long getPidWithoutParent(){
        return this.pid;
    }
}
