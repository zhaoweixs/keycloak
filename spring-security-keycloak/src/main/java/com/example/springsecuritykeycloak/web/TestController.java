package com.example.springsecuritykeycloak.web;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ToDO
 *
 * @author zhaowei
 * @date 2021/7/27 16:40
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("enter1")
    public Object enter1(){
        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext)request.getAttribute(KeycloakSecurityContext.class.getName());
        Identity identity = new Identity(keycloakSecurityContext);
        return "success";
    }

    @GetMapping("enter2")
    public Object enter2(){

        KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext)request.getAttribute(KeycloakSecurityContext.class.getName());
        Identity identity = new Identity(keycloakSecurityContext);
        return "success";
    }

    @GetMapping("enter3")
    public Object enter3(){
        return "success";
    }

    @GetMapping("enter4")
    public Object enter4(){
        return "success";
    }

}
