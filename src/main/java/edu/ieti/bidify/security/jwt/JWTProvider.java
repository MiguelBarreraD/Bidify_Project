package edu.ieti.bidify.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import edu.ieti.bidify.security.service.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTProvider {

    private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);

    private String secret;
    private int expitation;
    
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .signWith(getKey(secret))
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expitation * 1000))
                .claim("roles", getRoles(userPrincipal))
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJwt(token).getBody();
            return true;
        } catch (ExpiredJwtException e){
            logger.error("Expired token");
        } catch (UnsupportedJwtException e){
            logger.error("Unsuported token");
        }catch (MalformedJwtException e){
            logger.error("Malfunction token");
        }catch (IllegalArgumentException e){
            logger.error("Illegal token");
        }catch (Exception e){
            logger.error("Fail token");
        }
        return false;
    }

    private List<String> getRoles(UserPrincipal principal){
        return principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private Key getKey(String secret){
        byte [] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

}
