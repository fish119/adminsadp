package site.fish119.adminsadp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@Entity
@Table(name = "sys_department")
@Data
public class Department extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    private Long sort;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    private Department parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("sort ASC")
    @JoinColumn(name = "parent_id")
    private Set<Department> children = new HashSet<>(0);
}
