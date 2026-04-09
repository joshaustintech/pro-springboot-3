package com.apress.myretro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({MyretroProperties.class})
@Configuration
public class MyretroConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MyretroConfiguration.class);

    @Bean
    ApplicationListener<ApplicationReadyEvent> init(MyretroProperties properties) {
        return event -> {
            log.info(
                    "\nThe users service properties are:\n- Server: {}\n- Port: {}\n- Username: {}\n- Password: {}",
                    properties.getUsers().getServer(),
                    properties.getUsers().getPort(),
                    properties.getUsers().getUsername(),
                    properties.getUsers().getPassword()
            );
        };
    }
}
