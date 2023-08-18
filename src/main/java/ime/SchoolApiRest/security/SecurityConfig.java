package ime.SchoolApiRest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
                .csrf(AbstractHttpConfigurer::disable) 
                .authorizeHttpRequests(auth -> {
                	//auth.requestMatchers(AntPathRequestMatcher.antMatcher("**")).permitAll();
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll(); // API REST
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll(); // H2 database
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll(); // Swagger open api
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll(); // Swagger open api
                	auth.anyRequest().authenticated();
                })
                .headers(head-> head.frameOptions(f->f.sameOrigin())) // H2 database
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();		
	}
	
}
