package site.fish119.adminsadp.domain.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    @Column(nullable = false)
    private String name;
    private String shortName;
    private String phoneNum;
    private String address;
}
