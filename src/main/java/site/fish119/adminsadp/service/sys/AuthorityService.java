package site.fish119.adminsadp.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.sys.Authority;
import site.fish119.adminsadp.repository.sys.AuthorityRepository;
import site.fish119.adminsadp.service.BaseService;
import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/18 19:19
 * @Version V1.0
 */
@Service
public class AuthorityService extends BaseService<Authority> {
    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    private final AuthorityRepository authorityRepository;

    public List<Authority> findAllAuthorities() {
        return authorityRepository.findByParentIsNullOrderBySortAsc();
    }

    @Transactional
    public void saveAuthority(Authority authority) {
        Authority dbAuthority = authority.getId() == null ? authority : authorityRepository.getOne(authority.getId());
        if (dbAuthority.getId() != null) {
            dbAuthority.setName(authority.getName());
            dbAuthority.setUrl(authority.getUrl());
            dbAuthority.setDescription(authority.getDescription());
            dbAuthority.setSort(authority.getSort());
            dbAuthority.setMethod(authority.getMethod());
            dbAuthority.setOnlySa(authority.getOnlySa());
        }
        dbAuthority.setParent(authority.getPidWithoutParent() == null || authority.getPidWithoutParent() == 0 ? null : authorityRepository.getOne(authority.getPidWithoutParent()));
        authorityRepository.save(dbAuthority);
    }

    @Transactional()
    public void delAuthority(Long id) {
        Authority authority = authorityRepository.getOne(id);
        if (authority.getParent() == null) {
            authorityRepository.deleteById(id);
        } else {
            Authority parentAuthority = authorityRepository.getOne(authority.getPid());
            authority.setParent(null);
            parentAuthority.getChildren().remove(authority);
            authorityRepository.delete(authority);
        }
    }
}
