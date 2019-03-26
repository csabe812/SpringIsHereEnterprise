package com.spring.is.here.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		auth.inMemoryAuthentication()
			/*.withUser("user").password("{noop}user").roles("USER")
			.and()
			.withUser("shopowner").password("{noop}shopowner").roles("SHOP_OWNER")
			.and()*/
			.withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/plugins/**").permitAll();
		
		http
		.authorizeRequests()
			.antMatchers("/tesztoldal").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/shop/**").hasRole("ADMIN")
			.antMatchers("/shopowner/**").hasRole("ADMIN")
			.antMatchers("/regist").permitAll()
			.antMatchers("/reg").permitAll()
			.antMatchers("/console/**").permitAll()
			.antMatchers("/activation/**").permitAll()
			// DELETE THIS
			.antMatchers("/**").permitAll()
			// DELETE THIS
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
		
		//This is for only H2 embedded database
		//TODO: comment this if you connect to the postresql
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder());
    }
	
}
