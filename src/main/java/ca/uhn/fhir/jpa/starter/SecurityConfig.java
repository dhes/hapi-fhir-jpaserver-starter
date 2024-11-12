package ca.uhn.fhir.jpa.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Step 1: Define a basic user with username and password
    @Bean
    public UserDetailsService userDetailsService() {
			String username = System.getenv("HAPI_FHIR_USERNAME");
			String password = System.getenv("HAPI_FHIR_PASSWORD");

			if (username == null || password == null) {
					throw new IllegalStateException("HAPI_FHIR_USERNAME or HAPI_FHIR_PASSWORD environment variables are not set");
			}

			UserDetails user = User.withDefaultPasswordEncoder()
							.username(username)
							.password(password)
							.roles("USER")
							.build();

			return new InMemoryUserDetailsManager(user);
	    }

    // Step 2: Set up the security filter chain for basic authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated() // Ensure any request requires authentication
                .and()
            .httpBasic()  // Use basic authentication
                .and()
            .csrf().disable();  // Disable CSRF for API requests
        
        return http.build();
    }
}
