package org.sebas.securitydemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet initializer for WAR deployment
 * Required when deploying as WAR file to external application server
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configure Spring Boot application for WAR deployment
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SecurityDemoApplication.class);
    }

}
