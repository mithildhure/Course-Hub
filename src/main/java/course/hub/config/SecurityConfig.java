package course.hub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import course.hub.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) {
		
		security.csrf(customizer -> customizer.disable())
									.authorizeHttpRequests(auth -> auth
									.requestMatchers("/auth/**").permitAll()
									.requestMatchers("/admin/**").hasRole("ADMIN")
									.requestMatchers("/instructor/**").hasAnyRole("ADMIN","INSTRUCTOR")
									.requestMatchers("/student/**").hasAnyRole("ADMIN","STUDENT"))
									.formLogin(form -> Customizer.withDefaults());
		
		return security.build();
		
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authProvider(MyUserDetailsService servce, PasswordEncoder encode) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(servce);
		provider.setPasswordEncoder(encode);
		return provider;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}
	
}
