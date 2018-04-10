package site.fish119.adminsadp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.UserRepository;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:26
 * @Version V1.0
 */
@Service
public class UserDetailsServiceImple implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户不存在 '%s'.", s));
        } else {
            return new UserDetailsImple(user);
        }
    }
}
