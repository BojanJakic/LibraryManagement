package com.example.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository getPersistentTokenRepository(){
		JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
		token.setDataSource(dataSource);
		
		return token;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().passwordEncoder(getEncoder()).dataSource(dataSource)
		    .usersByUsernameQuery("SELECT username,password,enabled FROM user Where username = ?")
		    .authoritiesByUsernameQuery("SELECT username,role FROM user WHERE username = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/checkUsername").permitAll()
		    .antMatchers("/checkPassword").permitAll()
		    .antMatchers("/register_new_user").permitAll()
		    .antMatchers("/register_new_user_ajax").permitAll()
		    .antMatchers("/get_registration").hasRole("ADMIN")
		    .antMatchers("/include_fragment").authenticated()
		    .antMatchers("/addBook").hasRole("ADMIN")
		    .antMatchers("/book_registration").hasRole("ADMIN")
		    .antMatchers("/find_by_author").authenticated()
		    .antMatchers("/available_books").authenticated()
		    .antMatchers("/all_books").authenticated()
		    .antMatchers("/find_by_author").authenticated()
		    .antMatchers("/find_by_book_title").authenticated()
		    .antMatchers("/find_by_username").hasRole("ADMIN")
		    .antMatchers("/find_by_fullname").hasRole("ADMIN")
		    .antMatchers("/find_by_id").hasRole("ADMIN")
		    .antMatchers("/reserve_book").hasRole("USER")
		    .antMatchers("/show_available_books").hasRole("USER")
		    .antMatchers("/show_reserved_books").hasRole("USER")
		    .antMatchers("/username_reservation").hasRole("ADMIN")
		    .antMatchers("/author_reservation").hasRole("ADMIN")
		    .antMatchers("/title_reservation").hasRole("ADMIN")
		    .antMatchers("/fullname_reservation").hasRole("ADMIN")
		    .antMatchers("/delete_reservation").hasRole("ADMIN")
		    .antMatchers("/remove_user").hasRole("ADMIN")
		    .antMatchers("/all_users").hasRole("ADMIN")
		    .antMatchers("/custom").permitAll()
		    .antMatchers("/account_activation").permitAll()
		    .and()
		    .formLogin()
		    .loginPage("/log").permitAll()
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .defaultSuccessUrl("/logged")
		    .failureUrl("/errorLogin")
		    .and()
		    .logout()
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .logoutSuccessUrl("/").permitAll()
		    .and()
			.rememberMe()
			.tokenRepository(getPersistentTokenRepository())
			.tokenValiditySeconds(3600);
		    
		    //.and()
		    //.csrf()
		    //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

}
