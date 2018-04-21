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
 * @Date 2018/4/10 14:05
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity()
@Table(name = "sys_role")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Role extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private Long sort;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "Authority_ID"))
    @OrderBy("sort ASC")
    private Set<Authority> authorities = new HashSet<>(0);

    @ManyToMany(targetEntity = Menu.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "sys_role_menus", joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MENU_ID"))
    @OrderBy("sort ASC")
    private Set<Menu> menus = new HashSet<>(0);

    private void removeUsers() {
        for (User user : this.getUsers()) {
            user.getRoles().remove(this);
        }
        this.setUsers(null);
    }
    @PreRemove
    private void preRemove(){
        this.setMenus(null);
        this.setAuthorities(null);
        removeUsers();
    }
}
