package lara.carmen.PokeAPI.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lara.carmen.PokeAPI.dto.UserPermission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
public class JwtService {
    //PAYLOAD
    //sub : subject
    //iat : issued at
    //exp : cuando expira

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String user, String role){
        return Jwts.builder()
                .subject(user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigningKey())
                .claim("role", role)
                .compact();
    }

    public UserPermission extractUser(String token){

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();

        UserPermission user = new UserPermission();
        user.setMail(claims.getSubject());
        user.setRole(claims.get("role").toString());

        return user;
    }

    public boolean isTokenValid(String token){

       try{
           Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token);

           return true;
       }catch (Exception e){
           return false;
       }
    }
}
