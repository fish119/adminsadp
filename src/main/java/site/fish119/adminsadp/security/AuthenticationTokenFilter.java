package site.fish119.adminsadp.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.fish119.adminsadp.service.sys.UserService;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project sss
 * @Package site.fish119.sss.security.jwt
 * @Author fish119
 * @Date 2018/7/23 17:22
 * @Version V1.0
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "jwtTokenUtil")
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(Constant.TOKEN_HEADER);
        if (authHeader != null && authHeader.startsWith(Constant.TOKEN_PREFIX)) {
            try {
                final String authToken = authHeader.substring(Constant.TOKEN_PREFIX.length());
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                logger.debug("checking authentication {} from {}", username, request.getRequestURI());
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                                request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (ExpiredJwtException | CredentialsExpiredException e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired 认证超时，请重新登录。");
            }
        }
        filterChain.doFilter(request, response);
    }
}
