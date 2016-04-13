package org.example.rest.config;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final Log log = LogFactory.getLog(SecurityConfig.class);
    private static final int i = new Random().nextInt(10);
    private static final String PASSWD = "" + i + i + i;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin.html**").hasRole("admin")
            .and()
            .httpBasic().realmName("Login: admin, Password: " + PASSWD);

        /*http.antMatcher("/api/**").authorizeRequests().anyRequest().hasRole("admin").and()
                .httpBasic().realmName("Login: admin, Password: " + PASSWD);*/
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.inMemoryAuthentication().withUser("admin").password(PASSWD).roles("admin");
        
        log.info(PASSWD);
        System.err.println(PASSWD);
    }
}
