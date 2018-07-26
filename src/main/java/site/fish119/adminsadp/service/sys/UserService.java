package site.fish119.adminsadp.service.sys;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.fish119.adminsadp.domain.sys.QUser;
import site.fish119.adminsadp.domain.sys.Role;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.DepartmentRepository;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.service.BaseService;
import site.fish119.adminsadp.utils.MainUtil;
import site.fish119.adminsadp.utils.Md5Util;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/22 18:30
 * @Version V1.0
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户不存在 '%s'.", s));
        } else {
            return user;
        }
    }

    public Iterable<User> findUsers(String searchStr, Long departId, Integer page, Integer size, String sortColumn, String direction) {
        QUser qUser = QUser.user;
        Predicate predicate = qUser.id.isNotNull();
        if (!StringUtils.isEmpty(searchStr)) {
            predicate = ((BooleanExpression) predicate).and(qUser.username.containsIgnoreCase(searchStr.trim())
                    .or(qUser.nickname.containsIgnoreCase(searchStr.trim()))
                    .or(qUser.phone.containsIgnoreCase(searchStr.trim()))
                    .or(qUser.email.containsIgnoreCase(searchStr.trim())));
        }
        if (departId != null) {
            predicate = ((BooleanExpression) predicate).and(qUser.department.id.eq(departId));
        }
        Integer pages = page == null ? 0 : page;
        Integer pageSize = size == null ? Integer.parseInt(defaultPageSize) : size;
        String sortCol = StringUtils.isEmpty(sortColumn) ? "id" : sortColumn;
        String desc = StringUtils.isEmpty(direction) ? "DESC" : direction;
        return userRepository.findAll(predicate, MainUtil.getPageRequest(pages, pageSize, sortCol, desc));
    }

    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(encoder.encode(Md5Util.encode(defaultPassword)));
            user.setLastPasswordResetDate(new Date());
            if (user.getDepartment() != null) {
                user.setDepartment(departmentRepository.getOne(user.getDepartment().getId()));
            }
            Set<Role> roles = user.getRoles();
            user.setRoles(null);
            user = userRepository.saveAndFlush(user);
            user.setRoles(roles);
            return userRepository.saveAndFlush(user);
        } else {
            User dbUser = userRepository.getOne(user.getId());
            user.setPassword(dbUser.getPassword());
            BeanUtils.copyProperties(user, dbUser);
            if (user.getDepartment() != null) {
                dbUser.setDepartment(departmentRepository.getOne(user.getDepartment().getId()));
            }
            return userRepository.save(dbUser);
        }
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, oldPassword);
        authenticationManager.authenticate(upToken);
        User user = userRepository.findByUsername(username);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);
    }

    @Transactional
    public void setDefaultPassword(Long id) {
        User user = userRepository.getOne(id);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(Md5Util.encode(defaultPassword)));
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);
    }

    public boolean checkUsernameUnique(String username, Long id) {
        Long count = userRepository.countByUsernameAndIdNot(username, id);
        return count == null || count == 0;
    }

    public boolean checkNicknameUnique(String nickName, Long id) {
        Long count = userRepository.countByNicknameAndIdNot(nickName, id);
        return count == null || count == 0;
    }

    public boolean checkPhoneUnique(String phone, Long id) {
        Long count = userRepository.countByPhoneAndIdNot(phone, id);
        return count == null || count == 0;
    }

    public boolean checkEmailUnique(String email, Long id) {
        Long count = userRepository.countByEmailAndIdNot(email, id);
        return count == null || count == 0;
    }

    @Transactional
    public String changeAvatar(MultipartFile file) throws IOException {
        User user = findCurrentUser();
        String filename = user.getId() + ".png";
        Files.copy(file.getInputStream(), Paths.get(avatarPath + "/avatar/").resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        user.setAvatar(filename);
        return filename;
    }

    private User findCurrentUser() {
        return userRepository.getOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
