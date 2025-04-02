package lbj.king.proyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
	@Autowired
	private RepositoryUserDetailsService userDetailService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;	
	}

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.authorizeHttpRequests(authorize -> authorize
					// PUBLIC PAGES
					.requestMatchers("/").permitAll()
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/registerProcess").permitAll()
                    .requestMatchers("/loginProcess").permitAll()
					.requestMatchers("/css/**").permitAll()
					.requestMatchers("/imagenes/**").permitAll()
					.requestMatchers("/js/**").permitAll()	
                    .requestMatchers("/game/watch/{id}").permitAll()
					.requestMatchers("/h2-console/**").permitAll()	//FALLA IGUAL
					// PRIVATE PAGES
					.requestMatchers("/balanceProcess").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/profile/**").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/game/form").hasRole("ADMIN")
                    .requestMatchers("/game/save").hasRole("ADMIN")


					.anyRequest().authenticated())
                
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.failureUrl("/error")
					.defaultSuccessUrl("/profile")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);
		
		// Disable CSRF at the moment
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
