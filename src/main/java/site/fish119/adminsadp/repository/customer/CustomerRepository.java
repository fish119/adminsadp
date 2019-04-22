package site.fish119.adminsadp.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import site.fish119.adminsadp.domain.Customer.Customer;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.customer
 * @Author fish119
 * @Date 2019/4/19 13:35
 * @Version V1.0
 */
public interface CustomerRepository extends JpaRepository<Customer,Long>, QuerydslPredicateExecutor<Customer> {
}
