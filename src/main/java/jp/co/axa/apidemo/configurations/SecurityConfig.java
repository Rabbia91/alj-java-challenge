package jp.co.axa.apidemo.configurations;

/**
 * This class is a Spring configuration class that enables web security for the API endpoints.
 * It allows access to the endpoints with basic authentication and requires authentication for the "/api/v1/**" endpoint path.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
/**
 * Configures HTTP security for the API endpoints.
 * 
 * @param http The HttpSecurity object to be configured
 * @throws Exception If there is an error during configuration
 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/v1/**").authenticated()
            .and()
            .httpBasic();
    }
}
