package com.example.awsserver.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtil {
    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";
    private static final String secret="35828f8d3e7edbaa8a18f8d3ea";

    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    /**
     * 加密生成token
     * @param day  有效天数
     * @param <T>
     * @return
     */
    public static <T> String createToken(String userid,  long day) {
        try {
            final Algorithm signer = Algorithm.HMAC256(secret);//生成签名
            String token = JWT.create()
                    .withIssuer("签发者")
                    .withSubject("用户")//主题，科目
                    .withClaim("userid", userid)
                    .withAudience(userid)
                    .withExpiresAt(new Date(System.currentTimeMillis()+day*86400000))
                    .sign(signer);
            if(token.length()>15){
                byte[] bs=token.getBytes();
                byte tmp=bs[13];
                bs[13]=bs[3];
                bs[3]=tmp;
                token=new String(bs);
            }
            return token;
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("生成token异常：",e);
            return null;
        }
    }
    public static <T> String createToken(String userid) {
        return createToken(userid,30);
    }

    /**
     * 解析验证token
     * @param token 加密后的token字符串
     * @return
     */
    public static Boolean verifyToken(String token ) throws Exception{
        if(token.length()>15){
            byte[] bs=token.getBytes();
            byte tmp=bs[13];
            bs[13]=bs[3];
            bs[3]=tmp;
            token=new String(bs);
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }
}
