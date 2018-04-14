package site.fish119.adminsadp.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.fish119.adminsadp.domain.sys.Role;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.repository.sys
 * @Author fish119
 * @Date 2018/4/10 14:27
 * @Version V1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
