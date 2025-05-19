package top.ilov.web.kan.common.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.ilov.web.kan.entity.KanUser;
import top.ilov.web.kan.mapper.KanUserMapper;

import java.util.Date;
import java.util.Objects;

@Service
public class JwtService {

    @Autowired
    private KanUserMapper userMapper;

    private static final long EXPIRATION_TIME = 864_000_000;

    private final String secretKey;
    private final JWTVerifier jwtVerifier;

    public JwtService(@Value("${jwt.token.key}") String secretKey) {

        this.secretKey = secretKey;

        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        this.jwtVerifier = JWT.require(algorithm).build();

    }

    public String generateToken(String username, Long userId) {

        if (userId == null || username == null) {
            return null;
        }

        return JWT.create()
                .withSubject(username)
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));

    }

    public boolean isTokenValid(String token) {

        if (token == null) {
            return false;
        }
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public KanUser getUserFromToken(String token) {

        if (!isTokenValid(token)) {
            return null;
        }

        DecodedJWT jwt = JWT.decode(token);
        Long userId = jwt.getClaim("userId").asLong();

        KanUser user = userMapper.selectById(userId);
        if (user == null || !Objects.equals(user.getUsername(), jwt.getSubject())) {
            return null;
        }

        return user;

    }

}
