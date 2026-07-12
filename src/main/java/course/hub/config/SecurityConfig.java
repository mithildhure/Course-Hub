package course.hub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) {
		
		security.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll()
													.requestMatchers("/admin/**").hasRole("ADMIN")
													.requestMatchers("/instructor/**").hasAnyRole("ADMIN","INSTRUCTOR")
													.requestMatchers("/student/**").hasAnyRole("ADMIN","STUDENT"))
													.formLogin(form -> Customizer.withDefaults());
		
		return security.build();
		
	}
	
}
