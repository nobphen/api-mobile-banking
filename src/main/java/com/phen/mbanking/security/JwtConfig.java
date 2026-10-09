package com.phen.mbanking.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Component
public class JwtConfig {


    /// 1. Key Pair
    @Bean
    KeyPair accessTokenKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


    /// 2. RSA key
    @Bean
    RSAKey accessTokenRSAkey(KeyPair keyPair) {
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }


    /// 3. Jwk Sources
    @Bean
    JWKSource<SecurityContext> accessTokenJWKSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    /// 4. Jwt Decoder
    @Bean
    JwtDecoder accessTokenJwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }


    /// 5. Jwt EndCoder
    @Bean
    JwtEncoder accessTokenJwtEndCoder( JWKSource<SecurityContext> jwkSource){
        return  new NimbusJwtEncoder(jwkSource);
    }

}
