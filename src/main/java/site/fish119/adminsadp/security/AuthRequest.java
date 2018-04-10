package site.fish119.adminsadp.security;

import lombok.Data;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.security
 * @Author fish119
 * @Date 2018/4/10 17:51
 * @Version V1.0
 */
@Data
public class AuthRequest {
    private static final long serialVersionUID = -1L;

    private String username;
    private String password;

    public AuthRequest() {
        super();
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
