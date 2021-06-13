package org.serratec.viroumemeapi.config;

import org.serratec.viroumemeapi.security.AuthService;
import org.serratec.viroumemeapi.security.JWTAutheticationFilter;
import org.serratec.viroumemeapi.security.JWTAuthorizationFilter;
import org.serratec.viroumemeapi.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthService service;

	@Autowired
	JWTUtil jwtUtil;

	private static final String[] AUTH_WHITLIST = { "/swagger-ui/**", "/create", "/categoria/**", "/produto/**",
			"/v3/api-docs/**" };

	private static final String[] AUTH_WHITLIST2 = { "/pedido/**" };

	private static final String[] AUTH_WHITLIST3 = { "/endereco/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers(AUTH_WHITLIST).permitAll().antMatchers(HttpMethod.GET, AUTH_WHITLIST2)
				.permitAll().antMatchers(HttpMethod.DELETE, AUTH_WHITLIST2).permitAll()
				.antMatchers(HttpMethod.GET, AUTH_WHITLIST3).permitAll().antMatchers(HttpMethod.DELETE, AUTH_WHITLIST3)
				.permitAll().antMatchers(HttpMethod.POST, AUTH_WHITLIST3).permitAll().anyRequest().authenticated();
		http.addFilterBefore(new JWTAutheticationFilter(authenticationManager(), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder());
	}
}