package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component // 1
public class LoginSuccessEventListener {

    private final static Logger logger = LoggerFactory.getLogger(LoginSuccessEventListener.class);

    @EventListener // 2
    public void loginSuccessListener(AuthenticationSuccessEvent event) { // 3
        var authentication = event.getAuthentication();
        logger.info(
                "send login success via email for {}",
                authentication.getName()
        ); // 4
    }
}