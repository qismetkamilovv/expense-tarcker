package az.keytd.expensetracker.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final static String SECRET_KEY = "abcdbabssssgggdgdgdgdgdhdhdhdhdhhdhdhd";

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(getSignIn(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignIn() {
        byte[] abc = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(abc);
    }
}
