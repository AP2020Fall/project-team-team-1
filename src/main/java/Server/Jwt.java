package Server;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Jwt {

    public String generateToken(String id , String subject , String key){
        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("ata")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(key.getBytes()))
                .compact();
    }

    public Claims getClaims (String key , String token){
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(key.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername (String key , String token){
        return getClaims(key,token).getSubject();
    }

    public boolean validToken(String key , String token){
        return getClaims(key,token)
                .getExpiration()
                .after(new Date(System.currentTimeMillis()));
    }

    public boolean isInteger(String input){
        boolean isInteger = false ;
        try{
            int validInt = Integer.parseInt(input);
            isInteger = true;
        }catch (NumberFormatException numberFormatException){
            System.out.println("This is not a valid number! ");
        }
        return isInteger;
    }

    public boolean isString(String input){
        boolean isString = false ;
        try{
            String validString = String.valueOf(input);
            isString = true;
        }catch (Exception numberFormatException){
            System.out.println("This is not a String! ");
        }
        return isString;
    }

    public boolean isDouble(String input){
        boolean isDouble = false ;
        try{
            double validDouble = Double.parseDouble(input);
            isDouble = true;
        }catch (NumberFormatException numberFormatException){
            System.out.println("This is not a valid number! ");
        }
        return isDouble;
    }




}
