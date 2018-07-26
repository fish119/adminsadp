package site.fish119.adminsadp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import site.fish119.adminsadp.domain.sys.User;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project sss
 * @Package site.fish119.sss.security.jwt
 * @Author fish119
 * @Date 2018/7/23 17:24
 * @Version V1.0
 */
@Component
public class JwtTokenUtil implements Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = -1L;

    public String getUsernameFromToken(String token) throws AuthenticationException {
        try {
            final Claims claims = getClaimsFromToken(token);
            return claims.getSubject();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                throw new CredentialsExpiredException("认证超时，请重新登录");
            }
            return null;
        }
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(Constant.SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception err) {
            logger.error(err.getLocalizedMessage());
            claims = null;
        }
        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(Constant.CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(Constant.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, Constant.SECRET)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + Constant.EXPIRATION);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(Constant.CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
}
