package site.fish119.adminsadp.security;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:41
 * @Version V1.0
 */
public class AuthConstant {
    public static final String tokenHeader = "Authorization";
    static final String secret = "7945fb2b22546bf6e9290a68312cb033";
    public static final String tokenPrefix = "fish119";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "created";
    static final long expiration = 30*24*60*60*1000L;
}
