package com.ashish.datapoem.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ashish.datapoem.service.CustomUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	
	 
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                .antMatchers(
	                        "/",
	                        "/shop/**",
	                        "/register"
	                    
	                ).permitAll()
	                .antMatchers("/admin/**").hasRole("ADMIN")
	                .and()
	                .formLogin()
	                	.permitAll()
	                	.loginPage("/login")
	                	.usernameParameter("email")
	                	.passwordParameter("password")
	                	.loginProcessingUrl("/login")
	                	
	                .and()
	                .oauth2Login()
	                .loginPage("/login")
	                .successHandler(googleOAuth2SuccessHandler)
	                .and()
	                .logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/login")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	                .and()
	                .exceptionHandling()
	                .and()
	                .csrf()
	                .disable();
	        
	        
	                
	 }
	  
	 	
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(customUserDetailService);
	    }
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception{
		 web.ignoring().antMatchers("/resources/**, /static/**","/images/**","/productImages/**","/css/**","/js/**"); 
	 }
	 
	 @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	 }

}
