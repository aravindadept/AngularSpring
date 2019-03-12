package org.pace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig  extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UserCheckService userService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder( NoOpPasswordEncoder.getInstance());
		
		return provider;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	 
	        http.csrf().disable();
	 
	        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll()
	        .antMatchers("/home").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	        .antMatchers("/api/*").access("hasRole('ROLE_ADMIN')");
	        
	        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

	        http.authorizeRequests().and().formLogin()//
	                .loginProcessingUrl("/logincheck") 
	                .loginPage("/login")//
	                .defaultSuccessUrl("/home")//
	                .failureUrl("/login?error=true")//
	                .usernameParameter("username")//
	                .passwordParameter("password")
	                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	 
	    }
	

	
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("root")
          .password("root")
          .roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          .antMatchers("/home").permitAll()
          .antMatchers("/api/**").hasRole("ADMIN")
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic();
    }*/
}