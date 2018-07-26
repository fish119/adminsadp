package site.fish119.adminsadp.service.auth;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.fish119.adminsadp.domain.sys.User;
import site.fish119.adminsadp.repository.sys.RoleRepository;
import site.fish119.adminsadp.repository.sys.UserRepository;
import site.fish119.adminsadp.security.Constant;
import site.fish119.adminsadp.security.JwtTokenUtil;
import site.fish119.adminsadp.service.BaseService;

import java.util.Date;
import java.util.HashSet;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.auth
 * @Author fish119
 * @Date 2018/4/10 19:31
 * @Version V1.0
 */
@Service
public class AuthService  extends BaseService<User> {
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       UserDetailsService userDetailsService, JwtTokenUtil tokenUtil, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.tokenUtil = tokenUtil;
        this.roleRepository = roleRepository;
    }

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil tokenUtil;
    private final RoleRepository roleRepository;

    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return tokenUtil.generateToken(userDetails);
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(Constant.TOKEN_PREFIX.length());
        String username = tokenUtil.getUsernameFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(username);
        if (tokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return tokenUtil.refreshToken(token);
        }
        return null;
    }

    public User register(JSONObject requestUser) {
        final String username = requestUser.getString("username");
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = requestUser.getString("password");
        User userToAdd = new User();
        userToAdd.setUsername(username);
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setNickname("nickName");
        userToAdd.setPhone("18888888881");
        return userRepository.save(userToAdd);
    }

    public User registerAdmin(JSONObject requestUser) {
        final String username = requestUser.getString("username");
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = requestUser.getString("password");
        User userToAdd = new User();
        userToAdd.setUsername(username);
        userToAdd.setNickname("nickName");
        userToAdd.setPhone("18888888888");
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(userToAdd);
    }
}
