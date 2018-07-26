package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;
import site.fish119.adminsadp.domain.article.Article;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.sys
 * @Author fish119
 * @Date 2018/4/6 16:50
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "sys_user")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 20)
    private String username;

    @JsonIgnore
    @Column(nullable = false,length = 20)
    private String password;

    private Date lastPasswordResetDate;

    private String avatar;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String phone;

    private String email;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="dept_id")
    private Department department;

    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    @JsonIgnore
    private Set<Article> articles;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @OrderBy("sort ASC")
    private Set<Role> roles = new HashSet<>(0);
    @PreRemove
    private void preRemove(){
        this.setRoles(null);
        this.setDepartment(null);
    }
}
