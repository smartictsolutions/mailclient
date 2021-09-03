/* SmartICT Bilisim A.S. (C) 2021 */
package com.smartict.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@EnableScheduling
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-dev");
        SpringApplication.run(Application.class, args);
    }
}
