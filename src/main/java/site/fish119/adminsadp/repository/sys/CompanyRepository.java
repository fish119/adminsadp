package site.fish119.adminsadp.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.sys.Company;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.sys
 * @Author fish119
 * @Date 2018/4/6 16:43
 * @Version V1.0
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
