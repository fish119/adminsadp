package site.fish119.adminsadp.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.controller
 * @Author fish119
 * @Date 2018/4/18 13:37
 * @Version V1.0
 */
public class BaseController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception e) {
        e.printStackTrace();
//        LoggerFactory.getLogger(this.getClass()).error(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = {BadCredentialsException.class, UsernameNotFoundException.class,
            AccessDeniedException.class, InsufficientAuthenticationException.class,
            ExpiredJwtException.class, CredentialsExpiredException.class,
            AuthenticationCredentialsNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<Exception> handle401Exception(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
