package org.karasuba.jwtc;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.karasuba.reflect.UClass;
import org.karasuba.time.Chrono;
import org.karasuba.utils.Assert;
import org.karasuba.utils.Capturer;

import static org.karasuba.utils.BasicConverter.atob;
import static org.karasuba.utils.Generator.*;

/**
 * @author Red Gogh
 */
public class JWTGrantor {

    private final String subject;

    private final String issue;

    private final String audience;

    private final long expiration;

    private final JWSAlgorithm algorithm;

    public JWTGrantor(long duration, String algorithm) {
       this(null, null, null, duration, algorithm);
    }

    public JWTGrantor(String subject, String issue, String audience, long duration, String algorithm) {
        this.subject = subject;
        this.issue = issue;
        this.expiration = duration;
        this.audience = audience;
        this.algorithm = UClass.getConstant(JWSAlgorithm.class, algorithm);
        Assert.notNull(this.algorithm, "无效加密算法：%s", algorithm);
    }

    public String signWith(String secret) {
        return Capturer.call(() -> {
            JWSSigner signer = new MACSigner(atob(secret));

            JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                    .subject(subject)
                    .issueTime(Chrono.now())
                    .issuer(issue)
                    .expirationTime(Chrono.futureMoments((int) expiration * 60))
                    .jwtID(uuid())
                    .audience(audience);

            JWTClaimsSet claimsSet = claimsBuilder.build();

            SignedJWT signed = new SignedJWT(new JWSHeader(algorithm), claimsSet);

            signed.sign(signer);

            return signed.serialize();
        });
    }



    public static void main(String[] args) {
        JWTGrantor hs256Grantor = new JWTGrantor("karasuba-io", "Red Gogh", "Karasuba", 30, "HS256");
        System.out.println(hs256Grantor.signWith(b256md5()));
    }

}
