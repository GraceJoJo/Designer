package com.jojo.design.module_test;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/22 10:13 AM
 * desc   : [JWT在Java和Android中的使用](https://blog.csdn.net/mythmayor/article/details/81221642)
 */
public class ACT_TestJwt extends AppCompatActivity {
    private String SECRET_KEY = "mingjiazongxueguanmingjiazongxueguan";
    //    Bearer
    private String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7InVzZXJfaWQiOjYsIm5pY2tuYW1lIjoiXHU2NjBlXHU3NjdkXHU0ZWJhX2htZm90IiwidG91cmlzdCI6MX0sImV4cCI6MTU0ODUyOTIwMCwiaWF0IjoxNTQ4MTM2NjQ4fQ.meU0IxH-fAwae4TwXx3Xpoxgd702Ckf8QF1prv-THrA";
//    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7InVzZXJfaWQiOjk4MTIsIm5pY2tuYW1lIjoiXHU1YzBmXHU1NDY4XHU1NDBjXHU1YjY2XHVkODNkXHVkYzM1XHVkODNlXHVkZDgxIn0sImV4cCI6MTU1MDY4OTIwMCwiaWF0IjoxNTQ4MTIyNjAxfQ.agRgbYWunF_lwITPE4rtlSmzV75epBhL7bM8fxOHEzM";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        jwtParser();

        String[] jwt = token.split(" ");

        JWTParse(jwt[1]);

        JWTDecode(jwt[1]);
    }

    /**
     * compile 'io.jsonwebtoken:jjwt:0.9.1'
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void jwtParser() {
        Jws<Claims> jws = Jwts.parser().setSigningKey(base64Encode(SECRET_KEY)).parseClaimsJws(token);
        String signature = jws.getSignature();
        Map<String, String> header = jws.getHeader();
        Claims claims = jws.getBody();
        Object data = claims.get("data");
        Log.e("TAG", "data=" + data.toString());
    }

    // 编码:把普通字符串转换为base64字符串
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String base64Encode(String token) {
        byte[] encodedBytes = java.util.Base64.getEncoder().encode(token.getBytes());
        return new String(encodedBytes, java.nio.charset.Charset.forName("UTF-8"));
    }

    public void JWTParse(String jwt) {
        String key = Base64.encodeToString(SECRET_KEY.getBytes(), 0);
        //Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        //在解析的时候一定要传key进去，否则无法通过key的认证
        Jwt parse = Jwts.parser().setSigningKey(key).parse(jwt);
        Header header = parse.getHeader();
        Map<String, Object> map = (Map<String, Object>) parse.getBody();
        Object data = map.get("data");
        LinkedHashMap<String, Integer> param = (LinkedHashMap<String, Integer>) map.get("data");
        Log.e("TAG", "param=" + param.get("nickname") + "data.toString()=" + data.toString());
    }

    /**
     * compile 'com.auth0.android:jwtdecode:1.1.1'
     *
     * @param token
     */
    public void JWTDecode(String token) {
        JWT jwt = new JWT(token);
        /**
         * Registered Claims
         */
//Returns the Issuer value or null if it's not defined.
        String issuer = jwt.getIssuer();
//Returns the Subject value or null if it's not defined.
        String subject = jwt.getSubject();
//Returns the Audience value or an empty list if it's not defined.
        List<String> audience = jwt.getAudience();
//Returns the Expiration Time value or null if it's not defined.
        Date expiresAt = jwt.getExpiresAt();
//Returns the Not Before value or null if it's not defined.
        Date notBefore = jwt.getNotBefore();
//Returns the Issued At value or null if it's not defined.
        Date issuedAt = jwt.getIssuedAt();
//Returns the JWT ID value or null if it's not defined.
        String id = jwt.getId();
//Time Validation
        boolean isExpired = jwt.isExpired(10); // 10 seconds leeway

/**
 * Private Claims
 */
        Claim claim = jwt.getClaim("data");
    }


}