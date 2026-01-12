package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureEventListener {

    private final static Logger logger = LoggerFactory.getLogger(LoginFailureEventListener.class);

    @EventListener
    public void badCredentialListener(AuthenticationFailureBadCredentialsEvent event) {
        var authentication = event.getAuthentication();
        var exception = event.getException();
        logger.warn(
                "send authentication fail message via email for {} ({})",
                authentication.getName(),
                exception.getMessage()
        );
    }

    @EventListener
    public void accessExpiredListener(AuthenticationFailureExpiredEvent event) {
        var authentication = event.getAuthentication();
        var exception = event.getException();
        logger.warn(
                "send authentication fail message via email for {} ({})",
                authentication.getName(),
                exception.getMessage()
        );
    }
}