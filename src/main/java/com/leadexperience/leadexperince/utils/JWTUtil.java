package com.leadexperience.leadexperince.utils;

import com.leadexperience.leadexperince.service.ApiService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class JWTUtil {

    private String SECRET_KEY = "AllThisFor4Hearts";

    private String DUMMY_PLACE_HOLDER = "";



    @Autowired
    private ApiService apiService;

    public String extractUserName(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username",userDetails.getUsername());
        claims.put("RedEyesNCode","AllThisFor4Hearts");
//        FlorifyUserModel florifyUserModel = (FlorifyUserModel) apiService.loginUserJWT(userDetails.getUsername(),userDetails.getPassword()).getBody();
//        ;
//        claims.put("USER_DATA",florifyUserModel);

        claims.put("password",userDetails.getPassword());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Date expiryDate = claims.getExpiration();
        String username = claims.getSubject();
        if (userDetails.getUsername().equals(username) && (new Date().before(expiryDate))) {
            return true;
        }
        return false;
    }
}
