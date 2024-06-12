package az.keytd.expensetracker.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // extract it to application properties, and you can get it from there using
    // @Value
    // TODO: study @Value
    private static final String SECRET_KEY = "abcdbabssssgggdgdgdgdgdhdhdsdfdgrtyhutgbfvdcerftgyrhgtvfvfvgvgfvggvhdhdhhdhdhd";

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSignIn(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignIn() {
        byte[] abc = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(abc);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignIn()).build().parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
