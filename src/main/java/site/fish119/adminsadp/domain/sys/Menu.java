package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.sys
 * @Author fish119
 * @Date 2018/4/10 14:13
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "sys_menu")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Menu extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String icon;
    private String name;
    private Long sort;
    private Boolean hideInMenu=false;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    @OrderBy("sort ASC")
    private Set<Menu> children = new HashSet<>(0);

    @ManyToMany(mappedBy="menus",cascade = CascadeType.ALL)
    @OrderBy("sort ASC")
    @Getter(onMethod = @__( @JsonIgnore ))
    private Set<Role> mRoles = new HashSet<>(0);

    @Transient
    private Long pid;

    @Transient
    public Long getPid() {
        if(this.getParent()!=null){
            return this.getParent().getId();
        }else{
            return this.pid;
        }
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPidWithoutParent(){
        return this.pid;
    }

    public void setParent(Menu parent) {
        if (sameParent(parent))
            return;
        Menu oldParent = this.parent;
        this.parent = parent;
        if (oldParent != null) {
            oldParent.getChildren().remove(this);
        }
        if (parent != null) {
            parent.getChildren().add(this);
        }
    }

    public void removeRoles() {
        for (Role role : this.getMRoles()) {
            role.getMenus().remove(this);
        }
        this.setMRoles(null);
        for (Menu child : this.getChildren()) {
            child.removeRoles();
            child.setMRoles(null);
        }
    }

    private boolean sameParent(Menu newParent) {
        return parent == null ? newParent == null : parent.equals(newParent);
    }
}
