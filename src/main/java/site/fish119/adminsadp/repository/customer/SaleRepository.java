package site.fish119.adminsadp.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import site.fish119.adminsadp.domain.Customer.Sale;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.customer
 * @Author fish119
 * @Date 2019/4/19 20:35
 * @Version V1.0
 */
public interface SaleRepository extends JpaRepository<Sale,Long> {
}
