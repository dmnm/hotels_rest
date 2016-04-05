package org.example.rest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.example.rest")
public class App {
    public static void main(final String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
    }
}
