package com.phen.mbanking.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
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

    /// ACCESS TOKEN -----------------------------------------------

    /// 1. Key Pair
    @Bean("accessTokenKeyPair")
    KeyPair accessTokenKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


    /// 2. RSA key
    @Bean("accessTokenRSAkey")
    RSAKey accessTokenRSAkey(@Qualifier("accessTokenKeyPair") KeyPair keyPair) {
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }


    /// 3. Jwk Sources
    @Bean("accessTokenJWKSource")
    JWKSource<SecurityContext> accessTokenJWKSource(@Qualifier("accessTokenRSAkey") RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    /// 4. Jwt Decoder
    @Bean("accessTokenJwtDecoder")
    JwtDecoder accessTokenJwtDecoder(@Qualifier("accessTokenRSAkey") RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }


    /// 5. Jwt EndCoder
    @Bean("accessTokenJwtEndCoder")
    JwtEncoder accessTokenJwtEndCoder(@Qualifier("accessTokenJWKSource") JWKSource<SecurityContext> jwkSource){
        return  new NimbusJwtEncoder(jwkSource);
    }


    /// REFRESH TOKEN -----------------------------------------------

    /// 1. Key Pair
    @Bean("refreshTokenKeyPair")
    KeyPair refreshTokenKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


    /// 2. RSA key
    @Bean("refreshTokenRSAkey")
    RSAKey refreshTokenRSAkey(@Qualifier("refreshTokenKeyPair") KeyPair keyPair) {
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }


    /// 3. Jwk Sources
    @Bean("refreshTokenJWKSource")
    JWKSource<SecurityContext> refreshTokenJWKSource(@Qualifier("refreshTokenRSAkey") RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    /// 4. Jwt Decoder
    @Bean("refreshTokenJwtDecoder")
    JwtDecoder refreshTokenJwtDecoder(@Qualifier("refreshTokenRSAkey") RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }


    /// 5. Jwt EndCoder
    @Bean("refreshTokenJwtEndCoder")
    JwtEncoder refreshTokenJwtEndCoder(@Qualifier("refreshTokenJWKSource") JWKSource<SecurityContext> jwkSource){
        return  new NimbusJwtEncoder(jwkSource);
    }


}
