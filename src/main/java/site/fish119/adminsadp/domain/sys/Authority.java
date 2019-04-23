package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
@Table(name = "sys_authority")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonTypeName(value = "Authority")
public class Authority extends BaseEntity implements GrantedAuthority {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String description;
    private Long sort;
    private String method;
    private Boolean onlySa = false;
    @Transient
    private Long pid;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Authority parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("sort ASC")
    private Set<Authority> children = new HashSet<>(0);

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    @OrderBy("sort ASC")
    private Set<Role> roles = new HashSet<>(0);

    public void setParent(Authority parent) {
        if (sameParent(parent))
            return;
        Authority oldParent = this.parent;
        this.parent = parent;
        if (oldParent != null) {
            oldParent.getChildren().remove(this);
        }
        if (parent != null) {
            parent.getChildren().add(this);
        }
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

    public String getPermissionUrl() {
        return url;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.url = permissionUrl;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.url + ";" + this.method;
    }

    private boolean sameParent(Authority newParent) {
        return Objects.equals(parent, newParent);
    }

    public void removeRoles() {
        for (Role role : this.getRoles()) {
            role.getAuthorities().remove(this);
        }
        this.setRoles(null);
        for (Authority child : this.getChildren()) {
            child.removeRoles();
            child.setRoles(null);
        }
    }
}
