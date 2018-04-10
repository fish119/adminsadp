package site.fish119.adminsadp.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import site.fish119.adminsadp.domain.sys.Authority;

import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.sys
 * @Author fish119
 * @Date 2018/4/10 14:28
 * @Version V1.0
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    List<Authority> findByParentIsNullOrderBySortAsc();
}
