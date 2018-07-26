package site.fish119.adminsadp.security;

/**
 * @Project sss
 * @Package site.fish119.sss.security
 * @Author fish119
 * @Date 2018/7/23 15:21
 * @Version V1.0
 */
public class Constant {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String SECRET = "7945fb2b22546bf6e9290a68312cb033";
    public static final String TOKEN_PREFIX = "fish119";
    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final long EXPIRATION = 30*24*60*60*1000L;
}
