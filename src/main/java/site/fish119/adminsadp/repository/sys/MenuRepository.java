package site.fish119.adminsadp.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.sys.Menu;
import site.fish119.adminsadp.domain.sys.Role;

import java.util.List;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.sys
 * @Author fish119
 * @Date 2018/4/10 14:29
 * @Version V1.0
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByMRolesAndParentIsNullOrderBySortAsc(final Set<Role> roles);
    List<Menu> findByParentIsNullOrderBySortAsc();
    Set<Menu> findByIdIn(final Long[] ids);
}
