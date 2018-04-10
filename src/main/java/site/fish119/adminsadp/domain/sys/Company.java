package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.sys
 * @Author fish119
 * @Date 2018/4/6 16:40
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Entity
@Table(name = "sys_company")
@Data
public class Company extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Column(nullable = false)
    private String name;
    private String shortName;
    private String phoneNum;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Set<Department> departments;
}
