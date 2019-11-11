package top.liyf.admin.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;



    /**
     * 功能描述: 签发JWT
     *
     * @param userDetails
     * @return java.lang.String
     * @author liyf
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(Instant.now().toEpochMilli() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 功能描述: 验证JWT
     *
     * @param token token
     * @param userDetails
     * @return java.lang.Boolean
     * @author liyf
     */
    public Boolean validateToken(String token, UserDetails userDetails) {

        String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 功能描述: 获取token
     *
     * @param request
     * @return java.lang.String
     * @author liyf
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(tokenHeader);
    }

    /**
     * 功能描述: 解析JWT
     *
     * @param token token
     * @return io.jsonwebtoken.Claims
     * @author liyf
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 功能描述: 根据token获取username
     *
     * @param token token
     * @return java.lang.String
     * @author liyf
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 功能描述: 获取token是否过期
     *
     * @param token token
     * @return java.lang.Boolean
     * @author liyf
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 功能描述: 根据 token 获取过期时间
     *
     * @param token token
     * @return Date
     */
    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }


}
