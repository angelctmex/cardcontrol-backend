package com.aiti.preauthorizer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${security.jwt.key-store-name}")
    private String keystoreName;

    @Value("${security.jwt.key-store}")
    private Resource keystore;

    @Value("${security.jwt.key-store-password}")
    private String keystorePass;

    @Value("${security.jwt.key-pair-alias}")
    private String KeyPairAlias;

    @Value("${security.jwt.key-pair-password}")
    private String KeyPairPassword;

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private TokenStore tokenStore;

    public AuthorizationServerConfiguration(final DataSource dataSource, final PasswordEncoder passwordEncoder,
                                            final AuthenticationManager authenticationManager,
                                            final UserDetailsService userDetailsService) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public TokenStore tokenStore() {
        if (tokenStore == null) {
            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        }
        return tokenStore;
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore,
                                              final ClientDetailsService clientDetailsService) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(this.authenticationManager);
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(keystore, keystorePass.toCharArray()).getKeyPair(KeyPairAlias, KeyPairPassword.toCharArray());
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(this.userDetailsService)
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

}
