package source.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import source.entity.User;
import source.entity.enumeration.Role;

import java.util.Date;

// https://www.bezkoder.com/spring-boot-refresh-token-jwt/
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final String jwtSecret = "LearnE";

    private static final int jwtExpirationMs = 14400000;

    public String generateJwtToken(User user) {
        return generateTokenFromUserInfo(user);
    }

    public String generateTokenFromUserInfo(User user) {
        return Jwts.builder().setSubject(user.getId() + " " + user.getRole().getValue()).setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public User getUserFromJwtToken(String token) {
        try {
            String data = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
            String[] value = data.split(" ");
            return User.builder().id(value[0]).role(Role.valueOf(value[1])).build();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
