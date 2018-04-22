package site.fish119.adminsadp.service.sys;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import site.fish119.adminsadp.domain.sys.QUser;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.service.BaseService;
import site.fish119.adminsadp.utils.MainUtil;

import java.util.Date;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/22 18:30
 * @Version V1.0
 */
@Service
public class UserService extends BaseService<User> {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Value("${web.upload-path}")
    private String avatarPath;

    private Iterable<User> findAll(Integer page, Integer size, String sortColumn, String direction) {
        return userRepository.findAll(MainUtil.getPageRequest(page, size, sortColumn, direction));
    }

    public Iterable<User> findUsers(String searchStr, Integer page, Integer size, String sortColumn, String direction) {
        if (StringUtils.isEmpty(searchStr)) {
            return this.findAll(page, size, sortColumn, direction);
        } else {
            QUser qUser = QUser.user;
            Predicate predicate = qUser.username.containsIgnoreCase(searchStr.trim())
                    .or(qUser.nickname.containsIgnoreCase(searchStr.trim()))
                    .or(qUser.phone.containsIgnoreCase(searchStr.trim()))
                    .or(qUser.email.containsIgnoreCase(searchStr.trim()));
            return userRepository.findAll(predicate, MainUtil.getPageRequest(page, size, sortColumn, direction));
        }
    }

    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, oldPassword);
        authenticationManager.authenticate(upToken);
        User user = userRepository.findByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);
    }
}
