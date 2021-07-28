package com.example.springsecuritykeycloak.web;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.authorization.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2021/7/27 16:43
 */
public class Identity {

    private final KeycloakSecurityContext keycloakSecurityContext;

    public Identity(KeycloakSecurityContext keycloakSecurityContext) {
        this.keycloakSecurityContext = keycloakSecurityContext;
    }

    public boolean hasResourcePermission(String name){
        return getAuthorizationContext().hasResourcePermission(name);
    }

    public List<Permission> getPermissionList(){
        return getAuthorizationContext().getPermissions();
    }

    public AuthorizationContext getAuthorizationContext(){
        return keycloakSecurityContext.getAuthorizationContext();
    }
}
