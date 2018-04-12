package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
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
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Entity()
@Table(name = "sys_authority")
@Data
public class Authority extends BaseEntity implements GrantedAuthority {
    private static final long serialVersionUID = -1L;

    private String name;

    private String url;

    private String description;

    private Long sort;

    private String method;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Authority parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("sort ASC")
    private Set<Authority> children = new HashSet<>(0);

    @JsonIgnore
    @ManyToMany(mappedBy="authorities",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OrderBy("sort ASC")
    private Set<Role> roles = new HashSet<>(0);

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
}
