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
				//.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                	//auth.requestMatchers(AntPathRequestMatcher.antMatcher("**")).permitAll();
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll();
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll();
                	auth.requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll();
                	auth.anyRequest().authenticated();
                })
                //.headers(head-> head.frameOptions(f->f.sameOrigin()))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();		
	}
	
}
