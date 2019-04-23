package site.fish119.adminsadp.domain.Customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.customer
 * @Author fish119
 * @Date 2019/4/19 13:31
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "customer")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Customer extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String shortName;
    private String lvl;
    private String address;
    private String tel;
    private String fax;
    private String legal; //法人
    private String legalCode;//法人身份证号
    private String contact;
    private String mobile;
    private String uxCode; //税号
    private String bankName;//银行名称
    private String bankCode;//银行账号
}
