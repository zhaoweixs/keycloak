package com.example.springsecuritykeycloak.web;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.io.InputStream;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2021/7/27 16:50
 */
@EnableWebSecurity(debug = true)
@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.logout().logoutSuccessUrl("/home")
                .and()
                .authorizeRequests().antMatchers("/**").authenticated();
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakConfigResolver() {

            private KeycloakDeployment keycloakDeployment;

            @Override
            public KeycloakDeployment resolve(HttpFacade.Request request) {
                if (keycloakDeployment != null){
                    return keycloakDeployment;
                }
                String path = "/keycloak.json";
                InputStream inputStream = this.getClass().getResourceAsStream(path);
                if (inputStream == null){
                    throw new RuntimeException("file is not exists");
                }else{
                    keycloakDeployment = KeycloakDeploymentBuilder.build(inputStream);
                }
                return keycloakDeployment;
            }
        };
    }

}
