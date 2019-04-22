package site.fish119.adminsadp.domain.Customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish119.adminsadp.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.domain.Customer
 * @Author fish119
 * @Date 2019/4/19 20:32
 * @Version V1.0
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "customer_sale")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Sale extends BaseEntity {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String customerName;
    private String pjdj;
    private String fdj;
    private String gdj;
    private String pdj;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
}
