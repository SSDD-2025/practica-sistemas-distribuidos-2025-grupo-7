package lbj.king.proyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lbj.king.proyecto.security.jwt.JwtRequestFilter;
import lbj.king.proyecto.security.jwt.UnauthorizedHandlerJwt;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Autowired
	private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}


	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.securityMatcher("/api/**")
			.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));
		
		http
			.authorizeHttpRequests(authorize -> authorize
                    // PRIVATE ENDPOINTS
			
					.requestMatchers(HttpMethod.GET,"/api/prizes/**").hasAnyRole("USER","ADMIN")
					.requestMatchers(HttpMethod.POST,"/api/prizes/**").hasAnyRole("USER","ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/prizes/").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/prizes/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/prizes/**").hasRole("ADMIN")

					// .requestMatchers(HttpMethod.GET,"/api/plays/**").hasAnyRole("USER","ADMIN")
					.requestMatchers(HttpMethod.POST,"/api/plays/").hasAnyRole("USER","ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/plays/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/plays/**").hasAnyRole("USER","ADMIN")

					.requestMatchers(HttpMethod.GET, "/api/users/me/**").hasAnyRole("USER","ADMIN")
					.requestMatchers(HttpMethod.GET,"/api/users/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/users/me/**").hasAnyRole("USER","ADMIN")
					.requestMatchers(HttpMethod.POST,"/api/users/").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/users/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/users/me/**").hasAnyRole("USER","ADMIN")
					.requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")

					.requestMatchers(HttpMethod.GET, "/api/games/**").hasAnyRole("USER","ADMIN")

                    .requestMatchers(HttpMethod.POST,"/api/games/").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/games/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/games/**").hasRole("ADMIN")
					// PUBLIC ENDPOINTS
					.anyRequest().permitAll()
			);
		
        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}



    @Bean
	@Order(2)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.authorizeHttpRequests(authorize -> authorize
					// PUBLIC PAGES
					.requestMatchers("/").permitAll()
					.requestMatchers("/login").permitAll()
					.requestMatchers("/loginError").permitAll()
					.requestMatchers("/logout").permitAll()
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/registerProcess").permitAll()
					.requestMatchers("/css/**").permitAll()
					.requestMatchers("/imagenes/**").permitAll()
					.requestMatchers("/js/**").permitAll()	
                    .requestMatchers("/game/watch/{id}").permitAll()
					// PRIVATE PAGES
                    .requestMatchers("/game/form").hasRole("ADMIN")
                    .requestMatchers("/game/save").hasRole("ADMIN")

					.requestMatchers("/prizes").hasAnyRole("ADMIN", "USER")
					.requestMatchers("/prizes/new").hasRole("ADMIN")
					.requestMatchers("/prizes/**").hasRole("ADMIN")



					.anyRequest().authenticated()
					// .anyRequest().permitAll()
					)

                
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.failureUrl("/loginError")
					.defaultSuccessUrl("/profile")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);
		
		// // Disable CSRF at the moment
		// http.csrf(csrf -> csrf.disable());

		// http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		// //Disable Form login Authentication
        // http.formLogin(formLogin -> formLogin.disable());

        // // Disable CSRF protection (it is difficult to implement in REST APIs)
        // http.csrf(csrf -> csrf.disable());

        // // Disable Basic Authentication
        // http.httpBasic(httpBasic -> httpBasic.disable());

        // // Stateless session
        // http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// // Add JWT Token filter
		// http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
